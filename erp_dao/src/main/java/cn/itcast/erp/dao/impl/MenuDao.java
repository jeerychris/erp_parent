package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.entity.Menu;
import org.hibernate.criterion.DetachedCriteria;
/**
 * 菜单数据访问类
 * @author Administrator
 *
 */
public class MenuDao extends BaseDao<Menu> implements IMenuDao {

	/**
	 * 构建查询条件
	 * @param menu1
	 * @param menu2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Menu menu1,Menu menu2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Menu.class);
		return dc;
	}

}
