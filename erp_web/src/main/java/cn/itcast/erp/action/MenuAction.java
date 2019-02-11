package cn.itcast.erp.action;

import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.entity.Menu;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * 菜单Action
 *
 * @author Administrator
 */
public class MenuAction extends BaseAction<Menu> {

    private IMenuBiz menuBiz;
    private Long empUuid;

    public void setMenuBiz(IMenuBiz menuBiz) {
        this.menuBiz = menuBiz;
        super.setBaseBiz(this.menuBiz);
    }

    public Long getEmpUuid() {
        return empUuid;
    }

    public void setEmpUuid(Long empUuid) {
        this.empUuid = empUuid;
    }

    /**
     * 获取菜单数据
     */
    public void getMenuTree() {
        //通过获取主菜单，自关联就会带其下所有的菜单
        Menu menu = menuBiz.readMenuByEmpuuid(getLoginUser().getUuid());
        write(JSON.toJSONString(menu));
    }

    public void getMenusByEmpuuid() {
        List<Menu> menus = menuBiz.getMenusByEmpuuid(empUuid);
        String json = JSON.toJSONString(menus, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);
        write(json);
    }
}
