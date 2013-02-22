package com.nutzside.system.module;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzside.system.domain.Permission;
import com.nutzside.system.service.PermissionService;

@IocBean
@At("/system/permission")
public class PermissionModule {

	@Inject
	private PermissionService permissionService;

	@At
	@Ok("jsp:system.permission_list")
	// @Authorization(requiresPermissions = "permission:read:*")
	public List<Permission> all() {
		return permissionService.list();
	}

	@At
	@Ok("json")
	// @Authorization(requiresPermissions = "permission:read:*")
	public Map<Long, String> map() {
		return permissionService.map();
	}

	@At
	@Ok(">>:/system/permission/all")
	// @Authorization(requiresPermissions = "permission:delete:*")
	public void delete(@Param("id") Long id) {
		permissionService.delete(id);
	}

	@At
	@Ok("jsp:system.permission_add")
	// @Authorization(requiresRolesAtLeastOne = "admin,security-admin")
	public void p_add() {

	}

	@At
	@Ok(">>:/system/permission/all")
	// @Authorization(requiresPermissions = "permission:create:*")
	public void add(@Param("..") Permission permission) {
		permissionService.insert(permission);
	}

	@At
	@Ok("jsp:system.permission_view")
	// @Authorization(requiresPermissions = "permission:read:*")
	public Permission view(@Param("id") Long id) {
		return permissionService.view(id);
	}

	@At
	@Ok(">>:/system.permission/all")
	// @Authorization(requiresPermissions = "permission:update:*")
	public void edit(@Param("..") Permission permission) {
		permissionService.update(permission);
	}
}
