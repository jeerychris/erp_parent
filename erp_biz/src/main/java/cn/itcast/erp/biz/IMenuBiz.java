package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Menu;

import java.util.List;

/**
 * 菜单业务逻辑层接口
 *
 * @author Administrator
 */
public interface IMenuBiz extends IBaseBiz<Menu> {
    /**
     * 根据员工ID获取菜单集合
     *
     * @param empUuid
     * @return
     */
    public List<Menu> getMenusByEmpuuid(Long empUuid);

    /**
     * 根据员工ID查询菜单树
     *
     * @param empUuid
     * @return
     */
    public Menu readMenuByEmpuuid(Long empUuid);
}

