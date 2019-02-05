package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IStoredetailBiz;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.dao.IStoredetailDao;
import cn.itcast.erp.entity.Storedetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库库存业务逻辑类
 * @author Administrator
 *
 */
public class StoredetailBiz extends BaseBiz<Storedetail> implements IStoredetailBiz {

	private IStoredetailDao storedetailDao;
	private IGoodsDao goodsDao;
	private IStoreDao storeDao;

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
		super.setBaseDao(this.storedetailDao);
	}

	@Override
	public List<Storedetail> getListByPage(Storedetail t1, Storedetail t2, Object param, int firstResult, int maxResults) {
		List<Storedetail> list = super.getListByPage(t1, t2, param, firstResult, maxResults);
		Map<Long, String> goodsNameMap = new HashMap<Long, String>();
		Map<Long, String> storeNameMap = new HashMap<Long, String>();

		for (Storedetail o : list) {

			//从缓存中取出员工名称
			o.setGoodsName(getGoodsName(o.getGoodsuuid(), goodsNameMap));
			o.setStoreName(getStoreName(o.getStoreuuid(), storeNameMap));
		}

		return list;
	}

	private String getGoodsName(Long uuid, Map<Long, String> goodsNameMap) {
		if (uuid == null) {
			return null;
		}
		String goodsName = goodsNameMap.get(uuid);
		if (goodsName == null) {
			goodsName = goodsDao.get(uuid).getName();
			goodsNameMap.put(uuid, goodsName);
		}

		return goodsName;

	}

	private String getStoreName(Long uuid, Map<Long, String> storeNameMap) {
		if (uuid == null) {
			return null;
		}
		String storeName = storeNameMap.get(uuid);
		if (storeName == null) {
			storeName = storeDao.get(uuid).getName();
			storeNameMap.put(uuid, storeName);
		}

		return storeName;
	}
}
