package cn.itcast.erp.action;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.util.AjaxReturn;
import cn.itcast.erp.util.Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用Action, 封装通用的功能
 *
 * @param <T> 实体类
 * @author Administrator
 */
@SuppressWarnings("unused")
public class BaseAction<T> {

    /**
     * 主键uuid，主要用于查询和删除
     */
    private String uuid;
    /**
     * 主键id，主要用于查询和删除
     */
    private Long id;
    /**
     * 分页功能中的查询页码
     */
    private int page;

    /**
     * 分页功能中的每页显示的数量
     */
    private int rows;

    /**
     * 主要用于新增和修改的传参
     */
    private T t;
    /**
     * 扩展查询条件，当t1, t2不够用时使用
     */
    private Object[] params;

    /**
     * 基本业务层
     */
    private IBaseBiz<T> baseBiz;

    public void setBaseBiz(IBaseBiz<T> baseBiz) {
        this.baseBiz = baseBiz;
    }

    public void list() {
        // 获取所有的商品类型数据
        List<T> allT = baseBiz.getList(t, params);

        // 转换成JSON格式字符串
        String allTJsonString = JSON.toJSONString(allT, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(allTJsonString);
        // 输出到页面
        Util.write2UI(allTJsonString, ServletActionContext.getResponse());
    }

    /**
     * 分页查询
     */
    public void listPage() {
        // 获取总记录数
        Long total = baseBiz.getCount(t, params);

        // 分页后结果集
        List<T> tList = baseBiz.getListByPage(t, getStart(), rows, params);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", tList);
        // 输出到页面
        Util.write2UI(JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect), ServletActionContext.getResponse());
    }

    public void add() {
        try {
            baseBiz.add(t);
            write(ajaxReturn(true, "新增成功"));
        } catch (Exception e) {
            e.printStackTrace();
            write(ajaxReturn(false, "新增失败"));
        }
    }

    public void edit() {
        try {
            baseBiz.update(t);
            write(ajaxReturn(true, "更新成功"));
        } catch (Exception e) {
            e.printStackTrace();
            write(ajaxReturn(false, "更新失败"));
        }
    }

    public void get() {
        // {t.id:23}
        T _t = baseBiz.get(id);
        String json = JSON.toJSONStringWithDateFormat(_t, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        Util.write2UI(Util.addPrefix2JsonString(json, "t"), ServletActionContext.getResponse());
        // {t.id:1,t.name:''}
    }

    public void delete() {
        AjaxReturn resunt = null;
        try {
            // 调用业务删除商品类型
            baseBiz.delete(uuid);
            resunt = new AjaxReturn(true);
        } catch (Exception e) {
            e.printStackTrace();
            resunt = new AjaxReturn(e.getMessage());
        }
        Util.write2UI(resunt.toString(), ServletActionContext.getResponse());
    }

    public void write(String json) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ajaxReturn(boolean success, String message) {

        Map map = new HashMap();
        map.put("success", success);
        map.put("message", message);
        String json = JSON.toJSONString(map);

        return json;


    }

    private int getStart() {
        // page=2, rows=10, start=10
        return (this.page - 1) * this.rows;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
