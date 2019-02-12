package cn.itcast.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 角色实体类
 *
 * @author Administrator *
 */
public class Role {
    private Long uuid;//编号
    private String name;//名称
    @JSONField(serialize = false)
    private List<Menu> menus;//角色下的菜单权限
    @JSONField(serialize = false)
    private List<Emp> emps;

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Role{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
