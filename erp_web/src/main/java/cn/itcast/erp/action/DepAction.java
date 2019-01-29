package cn.itcast.erp.action;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.entity.Dep;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepAction extends ActionSupport {
    /**
     * @Param depBiz: Spring IOC && DI
     */
    private IDepBiz depBiz;
    /**
     * @Param dep: struts ognl
     */
    private Dep dep;

    /**
     * for pagination: current page
     */
    private int page;
    /**
     * for pagination: page size;
     */
    private int rows;
    /**
     * dep.uuid, for delete and edit
     */
    private long id;

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
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

    public void setDepBiz(IDepBiz depBiz) {
        this.depBiz = depBiz;
    }

    public void list() {
        page = page > 0 ? page : 1;
        int firstResult = (page - 1) * rows;
        rows = rows > 0 ? rows : 10;
        List<Dep> list = depBiz.getList(dep, firstResult, rows);
        long total = depBiz.getCount(dep);
        Map<String, Object> depMap = new HashMap<>();
        depMap.put("total", total);
        depMap.put("rows", list);
        String jsonString = JSON.toJSONString(depMap);
        write(jsonString);
    }

    /**
     * 新增
     */
    public void add() {
        Map<String, Object> rtn = new HashMap<>();
        if (dep != null) {
            depBiz.add(dep);
            rtn.put("success", true);
            rtn.put("message", "新增成功");
        } else {
            rtn.put("success", false);
            rtn.put("message", "failed");
        }
        write(JSON.toJSONString(rtn));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void delete() {
        Map<String, Object> rtn = new HashMap<>();
        if (id >= 0) {
            depBiz.delete(id);
            rtn.put("success", true);
            rtn.put("message", "deleted");
        } else {
            rtn.put("success", false);
            rtn.put("message", "failed");
        }
        write(JSON.toJSONString(rtn));
    }

    public void get() {
        Map<String, Object> rtn = new HashMap<>();
        if (id >= 0) {
            Dep dep = depBiz.get(id);
            write(mapData(JSON.toJSONString(dep), "dep"));
        }
    }

    /**
     * //{"name":"管理员组","tele":"000011","uuid":1}
     *
     * @param jsonString JSON数据字符串
     * @param prefix     要加上的前缀
     * @return {"dep.name":"管理员组","dep.tele":"000011","dep.uuid":1}
     */
    private String mapData(String jsonString, String prefix) {
        Map<String, Object> map = JSON.parseObject(jsonString);

        //存储key加上前缀后的值
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //给每key值加上前缀
        for (String key : map.keySet()) {
            dataMap.put(prefix + "." + key, map.get(key));
        }
        return JSON.toJSONString(dataMap);
    }


    public void update() {
        Map<String, Object> rtn = new HashMap<>();
        if (id >= 0 && dep != null) {
            depBiz.update(dep);
            rtn.put("success", true);
            rtn.put("message", "updated");
        } else {
            rtn.put("success", false);
            rtn.put("message", "failed");
        }
        write(JSON.toJSONString(rtn));
    }

    private void write(String jsonString) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
