package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;

import java.util.List;

/**
 * 仓库库存业务逻辑层接口
 *
 * @author Administrator
 */
public interface IStoredetailBiz extends IBaseBiz<Storedetail> {

    /**
     * 获取仓库预警列表
     *
     * @return
     */
    public List<Storealert> getStorealertList();

}

