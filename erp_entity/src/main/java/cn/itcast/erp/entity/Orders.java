package cn.itcast.erp.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 *
 * @author Administrator *
 */
public class Orders {
    /**
     * 未审核
     */
    public static final String STATE_CREATE = "0";
    /**
     * 已审核
     */
    public static final String STATE_CHECK = "1";
    /**
     * 已确认
     */
    public static final String STATE_START = "2";
    /**
     * 已入库
     */
    public static final String STATE_END = "3";

    /**
     * 采购订单
     */
    public static final String TYPE_IN = "1";
    /**
     * 销售订单
     */
    public static final String TYPE_OUT = "2";

    private Long uuid;//编号
    private Date createtime;//生成日期
    private Date checktime;//审核日期
    private Date starttime;//确认日期
    private Date endtime;//入库或出库日期
    private String type;//1:采购 2:销售
    private Long creater;//下单员
    private Long checker;//审核员
    private Long starter;//采购员
    private Long ender;//库管员
    private Long supplieruuid;//供应商或客户
    private Double totalmoney;//合计金额
    private String state;//采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
    private Long waybillsn;//运单号
    //订单明细
    private List<Orderdetail> orderDetails;

    public List<Orderdetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Orderdetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Long getStarter() {
        return starter;
    }

    public void setStarter(Long starter) {
        this.starter = starter;
    }

    public Long getEnder() {
        return ender;
    }

    public void setEnder(Long ender) {
        this.ender = ender;
    }

    public Long getSupplieruuid() {
        return supplieruuid;
    }

    public void setSupplieruuid(Long supplieruuid) {
        this.supplieruuid = supplieruuid;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getWaybillsn() {
        return waybillsn;
    }

    public void setWaybillsn(Long waybillsn) {
        this.waybillsn = waybillsn;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "uuid=" + uuid +
                ", createtime=" + createtime +
                ", checktime=" + checktime +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", type='" + type + '\'' +
                ", creater=" + creater +
                ", checker=" + checker +
                ", starter=" + starter +
                ", ender=" + ender +
                ", supplieruuid=" + supplieruuid +
                ", totalmoney=" + totalmoney +
                ", state='" + state + '\'' +
                ", waybillsn=" + waybillsn +
                '}';
    }
}
