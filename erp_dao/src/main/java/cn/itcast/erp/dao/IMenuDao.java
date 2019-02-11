package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Menu;

import java.util.List;

/**
 * 菜单数据访问接口
 *
 * @author Administrator
 */
public interface IMenuDao extends IBaseDao<Menu> {

    /**
     * 根据员工ID获取菜单集合
     *
     * @param empUuid
     * @return
     */
    public List<Menu> getMenusByEmpuuid(Long empUuid);
}
