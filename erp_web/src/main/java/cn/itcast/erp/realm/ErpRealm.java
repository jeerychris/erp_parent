package cn.itcast.erp.realm;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Menu;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class ErpRealm extends AuthorizingRealm {

    private IEmpBiz empBiz;//员工业务逻辑

    private IMenuBiz menuBiz;//菜单业务逻辑

    public void setMenuBiz(IMenuBiz menuBiz) {
        this.menuBiz = menuBiz;
    }

    public void setEmpBiz(IEmpBiz empBiz) {
        this.empBiz = empBiz;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行了授权的方法");
        //通过主角得到当前登录的用户对象
        Emp emp = (Emp) arg0.getPrimaryPrincipal();
        //实例化授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Menu> menus = menuBiz.getMenusByEmpuuid(emp.getUuid());
        for (Menu menu : menus) {
            info.addStringPermission(menu.getMenuname());
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // 得到令牌
        System.out.println("执行了认证的方法");
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        String username = token.getUsername();//得到用户名
        String pwd = new String(token.getPassword());//密码
        Emp emp = empBiz.findByUsernameAndPwd(username, pwd);

        if (emp == null) {
            System.out.println("realm--用户名密码错误");
            return null;
        }
        //参数1：principal 主角对象
        //参数2：credentials 密码
        //参数3：realmName realm的名字
        System.out.println("realm--认证成功");
        return new SimpleAuthenticationInfo(emp, pwd, getName());

    }

}
