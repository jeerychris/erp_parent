package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IStoredetailDao;
import cn.itcast.erp.entity.Storedetail;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
/**
 * 仓库库存数据访问类
 * @author Administrator
 *
 */
public class StoredetailDao extends BaseDao<Storedetail> implements IStoredetailDao {

	/**
	 * 构建查询条件
	 * @param storedetail1
	 * @param storedetail2
	 * @param param
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Storedetail storedetail1, Storedetail storedetail2, Object param){

		DetachedCriteria dc=DetachedCriteria.forClass(Storedetail.class);
		if(storedetail1!=null){
			//根据商品编号查询
			if (null != storedetail1.getGoodsuuid()) {
				dc.add(Restrictions.eq("goodsuuid", storedetail1.getGoodsuuid()));
			}
			//根据仓库编号查询
			if (null != storedetail1.getStoreuuid()) {
				dc.add(Restrictions.eq("storeuuid", storedetail1.getStoreuuid()));
			}
		}
		return dc;
	}

}
