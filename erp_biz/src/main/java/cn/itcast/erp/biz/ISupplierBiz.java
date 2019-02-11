package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Supplier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 供应商业务逻辑层接口
 *
 * @author Administrator
 */
public interface ISupplierBiz extends IBaseBiz<Supplier> {

    /**
     * 导出数据
     */
    public void export(OutputStream os, Supplier t1);

    /**
     * 数据导入
     *
     * @param is
     */
    void doImport(InputStream is) throws IOException;
}

