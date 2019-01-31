package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class DepDao extends BaseDao<Dep> implements IDepDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(Dep dep, Object... params) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dep.class);
        if (dep != null && dep.getName() != null && dep.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", dep.getName(), MatchMode.ANYWHERE));
        }
        if (dep != null && dep.getTele() != null && dep.getTele().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("tele", dep.getTele(), MatchMode.ANYWHERE));
        }
        return detachedCriteria;
    }
}
