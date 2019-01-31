package cn.itcast.erp.biz;

import java.util.List;

public interface IBaseBiz<T> {

    /**
     * 条件查询列表
     *
     * @param t1
     * @return
     */
    List<T> getList(T t1, Object... params);

    /**
     * 条件查询列表(分页)
     *
     * @param t1
     * @return
     */
    List<T> getListByPage(T t1, int firstResult, int maxResults, Object... params);

    /**
     * 统计记录个数
     *
     * @param t1
     * @param params
     * @return
     */
    Long getCount(T t1, Object... params);


    /**
     * 增加
     *
     * @param t
     */
    void add(T t);


    /**
     * 修改
     *
     * @param t
     */
    void update(T t);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    T get(Long id);

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    T get(String id);
}
