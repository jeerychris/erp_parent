package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Orders;
import com.redsum.bos.ws.Waybilldetail;

import java.io.OutputStream;
import java.util.List;

/**
 * 订单业务逻辑层接口
 *
 * @author Administrator
 */
public interface IOrdersBiz extends IBaseBiz<Orders> {

    /**
     * 审核
     *
     * @param uuid    订单编号
     * @param empUuid 审核员
     */
    public void doCheck(Long uuid, Long empUuid);

    /**
     * 确认
     *
     * @param uuid    订单编号
     * @param empUuid 采购员
     */
    public void doStart(Long uuid, Long empUuid);

    /**
     * 导出订单
     *
     * @param os
     * @param uuid 订单编号
     */
    void export(OutputStream os, Long uuid);

    /**
     * 根据运单号查询运单详情
     */
    public List<Waybilldetail> waybilldetailList(Long sn);
}

