package com.nutzside.system.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.erp.product.bean.Product;
import com.nutzside.system.domain.User;
import com.nutzside.system.service.UserService;

@IocBean
@At("/system/usr")
public class UserModule {

	@At
	public User me() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}

	// 无需登录,就可以直接访问, 跟没写一样....
	@RequiresGuest
	@At("/ping")
	public Object ping() {
		return Times.now();
	}

	// 需要登录之后才能访问,否则跳转到首页
	@RequiresAuthentication
	@At
	public Object authOnly() {
		return "You are authed!";
	}

	@Inject
	private UserService userService;

	@At
	@Ok("jsp:system.user_list")
	@RequiresPermissions("user:read:*")
	public List<User> all() {
		return userService.list();
	}

	@At
	@Ok("jsp:system.user_list")
	public Object list(@Param("pageNum") int pageNum ,@Param("numPerPage") int numPerPage,@Param("..") User obj){
		
		return userService.Pagerlist(pageNum, numPerPage, obj);
	}
	
	@At
	@Ok("jsp:system.user_view")
	@RequiresPermissions("user:read:*")
	public User view(@Param("id") Long id) {
		return userService.view(id);
	}

	@At
	@Ok("jsp:system.user_add")
	@RequiresRoles(value = { "admin", "user-superadmin", "user-admin" }, logical = Logical.OR)
	public void p_add() {
	}

	@At
	@Ok(">>:/system/usr/view?id=${p.userId}")
	@RequiresPermissions("user:roleAssign:*")
	public void addRole(@Param("userId") Long userId,
			@Param("roleId") Long roleId) {
		userService.addRole(userId, roleId);
	}

	@At
	@Ok(">>:/system/usr/view?id=${p.userId}")
	@RequiresPermissions("user:roleAssign:*")
	public void removeRole(@Param("userId") Long userId,
			@Param("roleId") Long roleId) {
		userService.removeRole(userId, roleId);
	}
}
