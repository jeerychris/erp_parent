package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IBaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;
        Type[] types = ptype.getActualTypeArguments();
        entityClass = (Class<T>) types[0];
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
        DetachedCriteria dc = getDetachedCriteria(t1, params);
        return (List<T>) getHibernateTemplate().findByCriteria(dc);
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
        DetachedCriteria dc = getDetachedCriteria(t1, params);
        return (List<T>) getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
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
        DetachedCriteria dc = getDetachedCriteria(t1, params);
        dc.setProjection(Projections.rowCount());
        return (Long) getHibernateTemplate().findByCriteria(dc).get(0);
    }

    /**
     * 增加
     *
     * @param t
     */
    @Override
    public void add(T t) {
        getHibernateTemplate().save(t);
    }

    /**
     * 修改
     *
     * @param t
     */
    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        getHibernateTemplate().delete(
                getHibernateTemplate().get(entityClass, id));
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        getHibernateTemplate().delete(
                getHibernateTemplate().get(entityClass, id));
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @Override
    public T get(Long id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @Override
    public T get(String id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    /**
     * must be override
     *
     * @param t
     * @param params
     * @return
     */
    protected DetachedCriteria getDetachedCriteria(T t, Object... params) {
        return null;
    }
}

