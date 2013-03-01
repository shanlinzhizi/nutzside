package com.nutzside.system.module;

import java.util.List;
import java.util.Map;


import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzside.common.util.DwzUtil;
import com.nutzside.system.domain.Permission;
import com.nutzside.system.domain.Role;
import com.nutzside.system.domain.User;
import com.nutzside.system.service.PermissionService;
import com.nutzside.system.service.RoleService;

@IocBean
@At("/system/role")
public class RoleModule {

	@Inject
	private RoleService roleService;
	@Inject
	private PermissionService permissionService;

	@At
	@Ok("fm:system.role_list")
	@RequiresPermissions("role:read:*")
	public Object all() {
		return roleService.getRoleListByPager(1, 20);
	}
	
	
	@At
	@Ok("httl:system.role_list")
	public Map<String, Object> list(@Param("pageNum") int pageNum,
			@Param("numPerPage") int numPerPage, @Param("..") Role obj) {

		return roleService.Pagerlist(pageNum, numPerPage, obj);
	}

	@At
	@Ok("json")
	@RequiresPermissions("role:read:*")
	public Map<Long, String> map() {
		return roleService.map();
	}

	@At
	@Ok("httl:system.role_add")
	@RequiresPermissions("role:read:*")
	public List<Permission> p_add() {
		Mvcs.getReq().setAttribute("isAddAction", true);
		return permissionService.list();
	}

	@At

	@RequiresPermissions("role:create:*")
	public Object add(@Param("..") Role role) {
		
		try {
			roleService.insert(role);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "role");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@RequiresPermissions("role:delete:*")
	public Object delete(@Param("id") Long id) {
		
		try {
			roleService.delete(id);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "role");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}


	@At
	@Ok("httl:system.role_view")
	@RequiresPermissions("role:read:*")
	public Role view(@Param("id") Long id) {
		return roleService.view(id);
	}


	@At
	@RequiresPermissions("role:update:*")
	public Object edit(@Param("..") Role role) {
		
		try {
			roleService.update(role);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "role");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@RequiresPermissions("role:permissionAssign:*")
	public Object addPermission(@Param("roleId") Long roleId,
			@Param("permissionId") Long permissionId) {

		try {

			roleService.addPermission(roleId, permissionId);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "view");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@RequiresPermissions("role:permissionAssign:*")
	public Object removePermission(@Param("roleId") Long roleId,
			@Param("permissionId") Long permissionId) {
		
		try {

			roleService.removePermission(roleId, permissionId);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "view");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At

	public boolean save(@Param("::role.") Role role, Integer[] pIDs) {
		Role $role = roleService.fetchByName(role.getName());
		if (Lang.isEmpty($role)) {
			roleService.insert(role);
			List<Permission> permissions = permissionService.query(
					Cnd.where("id", "iN", pIDs), null);
			role.setPermissions(permissions);
			roleService.dao().insertRelation(role, "permissions");
			return true;
		}
		return false;
	}

	@At
	public boolean update(@Param("::role.") Role role, Long id, Integer[] pIDs) {
		Role $role = roleService.fetch(id);
		if (!Lang.isEmpty($role)) {
			role.setId(id);
			roleService.update(role);
			List<Permission> permissions = permissionService.query(
					Cnd.where("id", "iN", pIDs), null);
			role.setPermissions(permissions);
			roleService.dao().insertRelation(role, "permissions");
			return true;
		}
		return false;
	}
}
