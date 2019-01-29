package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

import java.util.List;

public class DepBiz implements IDepBiz {

    private IDepDao depDao;

    public void setDepDao(IDepDao depDao) {
        this.depDao = depDao;
    }

    @Override
    public List<Dep> getList() {
        return depDao.getList();
    }

    @Override
    public List<Dep> getList(Dep dep) {
        return depDao.getList(dep);
    }

    /**
     * get dep with pagination
     *
     * @param dep
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<Dep> getList(Dep dep, int firstResult, int maxResults) {
        return depDao.getList(dep, firstResult, maxResults);
    }

    /**
     * get total count of dep table with criteria
     *
     * @param dep
     */
    @Override
    public long getCount(Dep dep) {
        return depDao.getCount(dep);
    }

    /**
     * 新增
     *
     * @param dep
     */
    @Override
    public void add(Dep dep) {
        depDao.add(dep);
    }

    /**
     * 删除
     *
     * @param uuid
     */
    @Override
    public void delete(Long uuid) {
        depDao.delete(uuid);
    }

    /**
     * 通过编号查询对象
     *
     * @param uuid
     * @return
     */
    @Override
    public Dep get(Long uuid) {
        return depDao.get(uuid);
    }

    /**
     * 更新
     *
     * @param dep
     */
    @Override
    public void update(Dep dep) {
        depDao.update(dep);
    }

}
