package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IRoleDao;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
import cn.itcast.erp.exception.ErpException;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工业务逻辑类
 *
 * @author Administrator
 */
public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {

    private final int hashIterations = 2;

    private IEmpDao empDao;
    private IRoleDao roleDao;

    public void setEmpDao(IEmpDao empDao) {
        this.empDao = empDao;
        super.setBaseDao(this.empDao);
    }

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * 用户登陆
     *
     * @param username
     * @param pwd
     * @return
     */
    public Emp findByUsernameAndPwd(String username, String pwd) {
        //查询前先加密
        pwd = encrypt(pwd, username);
        System.out.println(pwd);
        return empDao.findByUsernameAndPwd(username, pwd);
    }

    /**
     * 修改密码
     */
    public void updatePwd(Long uuid, String oldPwd, String newPwd) {
        //取出员工信息
        Emp emp = empDao.get(uuid);
        //加密旧密码
        String encrypted = encrypt(oldPwd, emp.getUsername());
        //旧密码是否正确的匹配
        if (!encrypted.equals(emp.getPwd())) {
            //抛出 自定义异常
            throw new ErpException("旧密码不正确");
        }
        empDao.updatePwd(uuid, encrypt(newPwd, emp.getUsername()));
    }

    /**
     * 新增员工
     */
    @Override
    public void add(Emp emp) {
        //String pwd = emp.getPwd();
        // source: 原密码
        // salt:   盐 =》扰乱码
        // hashIterations: 散列次数，加密次数
        //Md5Hash md5 = new Md5Hash(pwd, emp.getUsername(), hashIterations);
        //取出加密后的密码
        //设置初始密码
        String newPwd = encrypt(emp.getUsername(), emp.getUsername());
        //System.out.println(newPwd);
        //设置成加密后的密码
        emp.setPwd(newPwd);
        //保存到数据库中
        super.add(emp);
    }

    /**
     * 重置密码
     */
    public void updatePwd_reset(Long uuid, String newPwd) {
        //取出员工信息
        Emp emp = empDao.get(uuid);
        empDao.updatePwd(uuid, encrypt(newPwd, emp.getUsername()));
    }

    /**
     * 获取用户角色
     *
     * @param uuid
     * @return
     */
    @Override
    public List<Tree> readEmpRoles(Long uuid) {
        List<Tree> treeList = new ArrayList<Tree>();
        //获取用户信息
        Emp emp = empDao.get(uuid);
        //获取用户下的角色列表
        List<Role> empRoles = emp.getRoles();
        //获取所有角色列表
        List<Role> rolesList = roleDao.getList(null, null, null);
        Tree t1 = null;
        for (Role role : rolesList) {
            t1 = new Tree();
            //转换成string类型
            t1.setId(String.valueOf(role.getUuid()));
            t1.setText(role.getName());
            //判断是否需要勾选中,用户是否拥有这个角色
            if (empRoles.contains(role)) {
                t1.setChecked(true);
            }
            treeList.add(t1);
        }
        return treeList;
    }

    /**
     * 更新用户的角色
     *
     * @param uuid
     * @param checkedStr
     */
    @Override
    public void updateEmpRoles(Long uuid, String checkedStr) {
        //获取用户信息
        Emp emp = empDao.get(uuid);
        //清空该用户下的所有角色
        emp.setRoles(new ArrayList<Role>());

        String[] ids = checkedStr.split(",");
        Role role = null;
        for (String id : ids) {
            role = roleDao.get(Long.valueOf(id));
            //设置用户的角色
            emp.getRoles().add(role);
        }
    }


    /**
     * 加密
     *
     * @param source
     * @param salt
     * @return
     */
    private String encrypt(String source, String salt) {
        Md5Hash md5 = new Md5Hash(source, salt, hashIterations);
        return md5.toString();
    }

}
