package cn.itcast.erp.action;
import cn.itcast.erp.biz.IStoredetailBiz;
import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 仓库库存Action 
 * @author Administrator
 *
 */
public class StoredetailAction extends BaseAction<Storedetail> {

	private IStoredetailBiz storedetailBiz;

	public void setStoredetailBiz(IStoredetailBiz storedetailBiz) {
		this.storedetailBiz = storedetailBiz;
		super.setBaseBiz(this.storedetailBiz);
	}

    /**
     * 获取仓库预警列表
     *
     * @return
     */
    public void getStorealertList() {
        List<Storealert> list = storedetailBiz.getStorealertList();
        write(JSON.toJSONString(list));
    }

}
