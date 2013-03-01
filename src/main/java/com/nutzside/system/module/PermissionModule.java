package com.nutzside.system.module;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzside.common.util.DwzUtil;
import com.nutzside.system.domain.Permission;
import com.nutzside.system.service.PermissionService;

@IocBean
@At("/system/permission")
public class PermissionModule {

	@Inject
	private PermissionService permissionService;

	@At
	@Ok("jsp:system.permission_list")
	@RequiresPermissions("permission:read:*")
	public List<Permission> all() {
		return permissionService.list();
	}

	@At
	@Ok("httl:system.permission_list")
	public Map<String, Object> list(@Param("pageNum") int pageNum,
			@Param("numPerPage") int numPerPage, @Param("..") Permission obj) {

		return permissionService.Pagerlist(pageNum, numPerPage, obj);
	}

	@At
	@Ok("json")
	@RequiresPermissions("permission:read:*")
	public Map<Long, String> map() {
		return permissionService.map();
	}

	@At
	@RequiresPermissions("permission:delete:*")
	public Object delete(@Param("id") Long id) {
		try {

			permissionService.delete(id);
			return DwzUtil.dialogAjaxDonenoclose(DwzUtil.OK, "permission");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}

	}

	@At
	@Ok("httl:system.permission_add")
	@RequiresPermissions("permission:create:*")
	public void p_add() {

	}
    @At
	@Ok("httl:system.permission_query")
    public void queryUi(){    	
    }
    
	@At
	@RequiresPermissions("permission:create:*")
	public Object add(@Param("..") Permission permission) {

		try {

			permissionService.insert(permission);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "permission");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}

	@At
	@Ok("httl:system.permission_add")

	@RequiresPermissions("permission:create:*")
	public Permission view(@Param("id") Long id) {
		return permissionService.view(id);
	}

	@At
	@RequiresPermissions("permission:update:*")
	public Object edit(@Param("..") Permission permission) {
		try {
			permissionService.update(permission);

			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "view");
		} catch (Throwable e) {

			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL);
		}
	}
}
