package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IStoreoperBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.dao.IStoreoperDao;
import cn.itcast.erp.entity.Storeoper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库操作记录业务逻辑类
 *
 * @author Administrator
 */
public class StoreoperBiz extends BaseBiz<Storeoper> implements IStoreoperBiz {

    private IStoreoperDao storeoperDao;
    private IEmpDao empDao;
    private IGoodsDao goodsDao;
    private IStoreDao storeDao;

    public void setEmpDao(IEmpDao empDao) {
        this.empDao = empDao;
    }

    public void setGoodsDao(IGoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void setStoreDao(IStoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public void setStoreoperDao(IStoreoperDao storeoperDao) {
        this.storeoperDao = storeoperDao;
        super.setBaseDao(this.storeoperDao);
    }

    public List<Storeoper> getListByPage(Storeoper t1, Storeoper t2, Object param, int firstResult, int maxResults) {

        List<Storeoper> list = super.getListByPage(t1, t2, param, firstResult, maxResults);
        Map<Long, String> goodsNameMap = new HashMap<Long, String>();
        Map<Long, String> storeNameMap = new HashMap<Long, String>();
        Map<Long, String> empNameMap = new HashMap<Long, String>();

        for (Storeoper o : list) {
            //从缓存中取出员工名称
            o.setGoodsName(getGoodsName(o.getGoodsuuid(), goodsNameMap));
            o.setEmpName(getEmpName(o.getEmpuuid(), empNameMap));
            o.setStoreName(getStoreName(o.getStoreuuid(), storeNameMap));
        }

        return list;

    }


    private String getEmpName(Long uuid, Map<Long, String> empNameMap) {
        if (uuid == null) {
            return null;
        }
        String empName = empNameMap.get(uuid);
        if (empName == null) {
            empName = empDao.get(uuid).getName();
            empNameMap.put(uuid, empName);
        }

        return empName;

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
