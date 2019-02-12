package cn.itcast.erp.action;

import cn.itcast.erp.entity.Emp;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginAction {

	private String username;//登陆用户名
	private String pwd;//密码

	//	private IEmpBiz empBiz;
//	public void setEmpBiz(IEmpBiz empBiz) {
//		this.empBiz = empBiz;
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void checkUser(){
//		try{
//			//查询是否存在
//			Emp loginUser = empBiz.findByUsernameAndPwd(username, pwd);
//			if(loginUser != null){
//				//记录当前登陆的用记
//				ActionContext.getContext().getSession().put("loginUser", loginUser);
//				ajaxReturn(true, "");
//			}else{
//				ajaxReturn(false, "用户名或密码不正确");
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//			ajaxReturn(false, "登陆失败");
//		}

		//1.创建令牌（对用户和密码的封装）
		UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);

		//2.获得subject（主题，当前用户操作类，封装了一系列的操作，应用程序与shiro交互的入口部分）
		Subject subject = SecurityUtils.getSubject();
		//3.执行认证
		try{
			subject.login(token);
			ajaxReturn(true, "登录成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			ajaxReturn(true, "用户名或密码不正确");
		}
	}
	
	/**
	 * 显示登陆用户名
	 */
	public void showName(){
		//获取当前登陆的用户
		Subject subject = SecurityUtils.getSubject();
		Emp emp = (Emp) subject.getPrincipal();
		//session是否会超时，用户是否登陆过了
		if(null != emp){
			ajaxReturn(true, emp.getName());
		}else{
			ajaxReturn(false, "");
		}
	}
	
	/**
	 * 退出登陆
	 */
	public void loginOut(){
//		ActionContext.getContext().getSession().remove("loginUser");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		ajaxReturn(true, "");
	}
	
	/**
	 * 返回前端操作结果
	 * @param success
	 * @param message
	 */
	private void ajaxReturn(boolean success, String message) {
		//返回前端的JSON数据
		Map<String, Object> rtn = new HashMap<>();
		rtn.put("success",success);
		rtn.put("message",message);
		//JSON.toJSONString(rtn) => {"success":true,"message":'超级管理员'}
		write(JSON.toJSONString(rtn));
	}
	
	/**
	 * 输出字符串到前端
	 * @param jsonString
	 */
	public void write(String jsonString){
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			//设置编码
			response.setContentType("text/html;charset=utf-8"); 
			//输出给页面
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
