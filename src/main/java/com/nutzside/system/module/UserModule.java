package com.nutzside.system.module;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import com.nutzside.common.util.DwzUtil;
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
	@Ok("httl:system.user_list")
	public Map<String, Object> list(@Param("pageNum") int pageNum,
			@Param("numPerPage") int numPerPage, @Param("..") User obj) {

		return userService.Pagerlist(pageNum, numPerPage, obj);
	}

	@At
	@Ok("httl:system.user_view")
	@RequiresPermissions("user:read:*")
	public User view(@Param("id") Long id) {
		return userService.view(id);
	}

	@At
	@Ok("httl:system.user_add")
	@RequiresPermissions("user:add:*")
	public void p_add() {
	}

    @At
	@Ok("httl:system.user_query")
    public void queryUi(){    	
    }
    
	@At
	@RequiresPermissions("user:add:*")
	public Object add(@Param("..") User obj) {
		try {

			userService.insert(obj);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "user");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@RequiresPermissions("user:update:*")
	public Object update(@Param("..") User obj) {
		try {

			userService.update(obj);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "view");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@RequiresPermissions("user:delete:*")
	public Object delete(@Param("..") User obj) {
		try {

			userService.delete(obj);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "user");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	
	@RequiresPermissions("user:roleAssign:*")
	public Object addRole(@Param("userId") Long userId,
			@Param("roleId") Long roleId) {

		try {

			userService.addRole(userId, roleId);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "view");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@RequiresPermissions("user:roleAssign:*")
	public Object removeRole(@Param("userId") Long userId,
			@Param("roleId") Long roleId) {

		try {

			userService.removeRole(userId, roleId);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "view");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}
}
