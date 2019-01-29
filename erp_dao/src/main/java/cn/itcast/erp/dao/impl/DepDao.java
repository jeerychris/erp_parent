package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class DepDao extends HibernateDaoSupport implements IDepDao {

    @Override
    public List<Dep> getList() {
        return getDepList();
    }

    @Override
    public List<Dep> getList(Dep dep) {
        return getDepList(dep);
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
        return getDepList(dep, firstResult, maxResults);
    }

    /**
     * get total count of dep table
     */
    @Override
    public long getCount(Dep dep) {
        DetachedCriteria dc = getDetachedCriteria(dep);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        return list.get(0);
    }

    /**
     * 新增
     *
     * @param dep
     */
    @Override
    public void add(Dep dep) {
        this.getHibernateTemplate().save(dep);
    }

    /**
     * 删除
     *
     * @param uuid
     */
    @Override
    public void delete(Long uuid) {
        Dep dep = get(uuid);
        getHibernateTemplate().delete(dep);
    }

    /**
     * 通过编号查询对象
     *
     * @param uuid
     * @return
     */
    @Override
    public Dep get(Long uuid) {
        return getHibernateTemplate().get(Dep.class, uuid);
    }

    /**
     * 更新
     *
     * @param dep
     */
    @Override
    public void update(Dep dep) {
        getHibernateTemplate().update(dep);
    }

    private List<Dep> getDepList(Object... args) {
        int firstResult = 0;
        int maxResults = 0;
        DetachedCriteria dc = getDetachedCriteria(args[0]);

        if (args.length > 1) {
            firstResult = (int) args[1] > 0 ? (int) args[1] : 0;
            maxResults = (int) args[2] > 0 ? (int) args[2] : 0;
        }

        return (List<Dep>) getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
    }

    private DetachedCriteria getDetachedCriteria(Object obj) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dep.class);
        if (obj instanceof Dep) {
            Dep dep = (Dep) obj;
            if (dep.getName() != null && dep.getName().trim().length() > 0) {
                detachedCriteria.add(Restrictions.like("name", dep.getName(), MatchMode.ANYWHERE));
            }
            if (dep.getTele() != null && dep.getTele().trim().length() > 0) {
                detachedCriteria.add(Restrictions.like("tele", dep.getTele(), MatchMode.ANYWHERE));
            }
        }
        return detachedCriteria;
    }
}
