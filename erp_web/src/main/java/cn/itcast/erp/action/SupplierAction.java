package cn.itcast.erp.action;
import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.entity.Supplier;

/**
 * 供应商Action 
 * @author Administrator
 *
 */
public class SupplierAction extends BaseAction<Supplier> {

	private ISupplierBiz supplierBiz;
	private String q;

	public void setSupplierBiz(ISupplierBiz supplierBiz) {
		this.supplierBiz = supplierBiz;
		super.setBaseBiz(this.supplierBiz);
	}

	public String getQ() {
		if (q == null) {
			q = "";
		}
		return q.trim();
	}

	public void setQ(String q) {
		this.q = q;
	}

	@Override
	public void list() {
		if (getQ().length() > 0) {
			if (getT1() == null) {
				setT1(new Supplier());
			}
			getT1().setName(getQ());
		}
		super.list();
	}
}
