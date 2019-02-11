package cn.itcast.erp.action;
import cn.itcast.erp.biz.IRoleBiz;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 角色Action 
 * @author Administrator
 *
 */
public class RoleAction extends BaseAction<Role> {

	private IRoleBiz roleBiz;
    private String checkedStr;//勾选中菜单的ID字符串，以逗号分割

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		super.setBaseBiz(this.roleBiz);
	}

    public String getCheckedStr() {
        return checkedStr;
    }

    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    /**
     * 获取角色菜单权限
     */
    public void readRoleMenus() {
        List<Tree> menus = roleBiz.readRoleMenus(getId());
        write(JSON.toJSONString(menus));
    }

    /**
     * 更新角色权限
     */
    public void updateRoleMenus() {
        try {
            roleBiz.updateRoleMenus(getId(), checkedStr);
            ajaxReturn(true, "更新角色菜单成功");
        } catch (Exception e) {
            ajaxReturn(false, "更新角色菜单失败");
            e.printStackTrace();
        }
    }
}
