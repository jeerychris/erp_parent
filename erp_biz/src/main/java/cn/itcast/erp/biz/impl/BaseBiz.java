package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.dao.IBaseDao;

import java.util.List;

public class BaseBiz<T> implements IBaseBiz<T> {
    /**
     * spring IOC && DI
     */
    private IBaseDao<T> baseDao;

    public void setBaseDao(IBaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * 条件查询列表
     *
     * @param t1
     * @param params
     * @return
     */
    @Override
    public List<T> getList(T t1, Object... params) {
        return baseDao.getList(t1, params);
    }

    /**
     * 条件查询列表(分页)
     *
     * @param t1
     * @param firstResult
     * @param maxResults
     * @param params
     * @return
     */
    @Override
    public List<T> getListByPage(T t1, int firstResult, int maxResults, Object... params) {
        return baseDao.getListByPage(t1, firstResult, maxResults, params);
    }

    /**
     * 统计记录个数
     *
     * @param t1
     * @param params
     * @return
     */
    @Override
    public Long getCount(T t1, Object... params) {
        return baseDao.getCount(t1, params);
    }

    /**
     * 增加
     *
     * @param t
     */
    @Override
    public void add(T t) {
        baseDao.add(t);
    }

    /**
     * 修改
     *
     * @param t
     */
    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        baseDao.delete(id);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        baseDao.delete(id);
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @Override
    public T get(Long id) {
        return baseDao.get(id);
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @Override
    public T get(String id) {
        return baseDao.get(id);
    }
}
