package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Dep;

import java.util.List;

public interface IDepDao {

    /**
     * get all departments
     */
    List<Dep> getList();

    /**
     * get dep with criteria
     *
     * @param dep
     * @return
     */
    List<Dep> getList(Dep dep);

    /**
     * get dep with pagination
     *
     * @param dep
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<Dep> getList(Dep dep, int firstResult, int maxResults);

    /**
     * get total count of dep table with criteria
     */
    long getCount(Dep dep);

    /**
     * 新增
     *
     * @param dep
     */
    void add(Dep dep);

    /**
     * 删除
     */
    void delete(Long uuid);

    /**
     * 通过编号查询对象
     *
     * @param uuid
     * @return
     */
    Dep get(Long uuid);

    /**
     * 更新
     */
    void update(Dep dep);
}
