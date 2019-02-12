package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.entity.Menu;
import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单业务逻辑类
 * @author Administrator
 *
 */
public class MenuBiz extends BaseBiz<Menu> implements IMenuBiz {

	private IMenuDao menuDao;
	private Jedis jedis;

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(this.menuDao);
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	/**
	 * 根据员工ID获取菜单集合
	 *
	 * @param empUuid
	 * @return
	 */
	@Override
	public List<Menu> getMenusByEmpuuid(Long empUuid) {

		//尝试从redisz中提取缓存
		String menuListJson = jedis.get("menuList_" + empUuid);
		if (menuListJson != null) {
			List<Menu> menus = JSON.parseArray(menuListJson, Menu.class);
			System.out.println("从redis中提取菜单记录。。。。。");
			return menus;
		} else {
			//如果redis中没有则提取数据库 中的记录
			List<Menu> menus = menuDao.getMenusByEmpuuid(empUuid);
			jedis.set("menuList_" + empUuid, JSON.toJSONString(menus));
			System.out.println("从数据库中提取菜单记录。。。。。");
			return menus;
		}

	}

	//克隆菜单
	private Menu createMenu(Menu menu) {
		Menu m = new Menu();
		m.setMenuid(menu.getMenuid());
		m.setMenuname(menu.getMenuname());
		m.setIcon(menu.getIcon());
		m.setUrl(menu.getUrl());
		m.setMenus(new ArrayList<Menu>());
		return m;
	}

	/**
	 * 根据员工ID查询菜单树
	 *
	 * @param empUuid
	 * @return
	 */
	public Menu readMenuByEmpuuid(Long empUuid) {
		//当前用户的菜单集合
		List<Menu> menus = menuDao.getMenusByEmpuuid(empUuid);
		//得到全部的菜单
		Menu menu0 = menuDao.get("0");
		//克隆菜单
		Menu m0 = createMenu(menu0);
		//循环一级菜单
		for (Menu menu1 : menu0.getMenus()) {
			Menu m1 = createMenu(menu1);//克隆一级菜单
			for (Menu menu2 : menu1.getMenus()) {
				if (menus.contains(menu2)) {//如果当前的菜单在用户菜单集合中
					Menu m2 = createMenu(menu2);//克隆二级菜单
					m1.getMenus().add(m2);//将二级菜单挂到一级菜单下
				}
			}
			if (m1.getMenus().size() > 0) {//判断当前一级菜单下有没有二级菜单
				m0.getMenus().add(m1);//将一级菜单挂到根菜单下
			}
		}

		return m0;
	}
}
