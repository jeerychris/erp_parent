package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.entity.Store;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
/**
 * 仓库数据访问类
 * @author Administrator
 *
 */
public class StoreDao extends BaseDao<Store> implements IStoreDao {

	/**
	 * 构建查询条件
	 * @param store1
	 * @param store2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Store store1,Store store2,Object param){

		DetachedCriteria dc=DetachedCriteria.forClass(Store.class);
		if(store1!=null){
			if(null != store1.getName() && store1.getName().trim().length()>0){
				dc.add(Restrictions.like("name", store1.getName(), MatchMode.ANYWHERE));
			}
			//根据员工编号查询
			if (null != store1.getEmpuuid()) {
				dc.add(Restrictions.eq("empuuid", store1.getEmpuuid()));
			}

		}
		return dc;
	}

}
