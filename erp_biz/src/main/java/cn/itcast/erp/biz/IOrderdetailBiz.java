package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Orderdetail;

/**
 * 订单明细业务逻辑层接口
 *
 * @author Administrator
 */
public interface IOrderdetailBiz extends IBaseBiz<Orderdetail> {
    /**
     * 入库
     */
    public void doInStore(Long uuid, Long storeuuid, Long empuuid);

    /**
     * 出库
     */
    public void doOutStore(Long uuid, Long storeuuid, Long empuuid);

}

