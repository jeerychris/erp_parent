package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;

import java.util.List;

/**
 * 仓库库存数据访问接口
 *
 * @author Administrator
 */
public interface IStoredetailDao extends IBaseDao<Storedetail> {

    /**
     * 获取仓库预警列表
     *
     * @return
     */
    public List<Storealert> getStorealertList();

}
