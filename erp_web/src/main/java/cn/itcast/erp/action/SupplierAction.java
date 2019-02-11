package cn.itcast.erp.action;
import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.entity.Supplier;
import cn.itcast.erp.exception.ErpException;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 供应商Action 
 * @author Administrator
 *
 */
public class SupplierAction extends BaseAction<Supplier> {

	private ISupplierBiz supplierBiz;
	private String q;
	private File file;//上传的文件
	private String fileFileName;//上传的文件名称
	private String fileContentType;//上传的文件类型

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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

	/**
	 * 导出数据
	 */
	public void export() {
		String filename = "";
		if (Supplier.TYPE_SUPPLIER.equals(getT1().getType())) {
			filename = "供应商";
		}
		if (Supplier.TYPE_CUSTOMER.equals(getT1().getType())) {
			filename = "客户";
		}
		filename += ".xls";
		//响应对象
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//设置输出流,实现下载文件
			response.setHeader("Content-Disposition", "attachment;filename=" +
					new String(filename.getBytes(), "ISO-8859-1"));
			supplierBiz.export(response.getOutputStream(), getT1());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入数据
	 */
	public void doImport() {
		//文件类型判断
		if (!"application/vnd.ms-excel".equals(fileContentType)) {
			ajaxReturn(false, "上传的文件必须为excel文件");
			return;
		}
		try {
			supplierBiz.doImport(new FileInputStream(file));
			ajaxReturn(true, "上传的文件成功");
		} catch (ErpException e) {
			ajaxReturn(false, e.getMessage());
		} catch (IOException e) {
			ajaxReturn(false, "上传的文件失败");
			e.printStackTrace();
		}
	}

}
