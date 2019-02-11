package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Tree;

import java.util.List;

/**
 * 员工业务逻辑层接口
 *
 * @author Administrator
 */
public interface IEmpBiz extends IBaseBiz<Emp> {

    /**
     * 用户登陆
     *
     * @param username
     * @param pwd
     * @return
     */
    Emp findByUsernameAndPwd(String username, String pwd);

    /**
     * 修改密码
     */
    void updatePwd(Long uuid, String oldPwd, String newPwd);

    /**
     * 重置密码
     */
    void updatePwd_reset(Long uuid, String newPwd);

    /**
     * 获取用户角色
     *
     * @param uuid
     * @return
     */
    List<Tree> readEmpRoles(Long uuid);

    /**
     * 更新用户的角色
     *
     * @param uuid
     * @param checkedStr
     */
    void updateEmpRoles(Long uuid, String checkedStr);
}

