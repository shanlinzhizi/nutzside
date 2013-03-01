package com.nutzside.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.IdEntityService;

import com.nutzside.system.domain.Permission;
import com.nutzside.system.domain.Role;
import com.nutzside.system.domain.User;

@IocBean(fields = { "dao" })
public class RoleService extends IdEntityService<Role> {
	public RoleService() {
		super();
	}

	public RoleService(Dao dao) {
		super(dao);
	}

	public List<Role> list() {
		return query(null, null);
	}

	public void insert(Role role) {
		dao().insert(role);
	}

	public Role view(Long id) {
		return dao().fetchLinks(fetch(id), "permissions");
	}

	public void update(Role role) {
		dao().update(role);
	}

	public Role fetchByName(String name) {
		return fetch(Cnd.where("NAME", "=", name));
	}

	

	public Map<String, Object> Pagerlist( int pageNum ,int numPerPage,@Param("..") Role obj) {
		
		Pager pager = dao().createPager((pageNum<1)?1:pageNum, (numPerPage < 1)? 20:numPerPage);
		List<Role> list = dao().query(Role.class, bulidQureyCnd(obj), pager);
		Map<String, Object> map = new HashMap<String, Object>();
		if (pager != null) {
			pager.setRecordCount(dao().count(Role.class,bulidQureyCnd(obj)));
			map.put("pager", pager);
		}
		map.put("o", obj);
		map.put("pagerlist", list);
		return map;

	}
	public List<String> getPermissionNameList(Role role) {
		dao().fetchLinks(role, "permissions");
		List<String> permissionNameList = new ArrayList<String>();

		for (Permission permission : role.getPermissions()) {
			permissionNameList.add(permission.getName());
		}
		return permissionNameList;
	}

	public Map<Long, String> map() {
		Map<Long, String> map = new HashMap<Long, String>();
		List<Role> roles = query(null, null);
		for (Role role : roles) {
			map.put(role.getId(), role.getName());
		}
		return map;
	}

	public void addPermission(Long roleId, Long permissionId) {
		dao().insert("SYSTEM_ROLE_PERMISSION",
				Chain.make("ROLEID", roleId).add("PERMISSIONID", permissionId));
	}

	public void removePermission(Long roleId, Long permissionId) {
		dao().clear(
				"SYSTEM_ROLE_PERMISSION",
				Cnd.where("ROLEID", "=", roleId).and("PERMISSIONID", "=",
						permissionId));
	}

	public QueryResult getRoleListByPager(int pageNumber, int pageSize) {
		Pager pager = dao().createPager(pageNumber, pageSize);
		List<Role> list = dao().query(Role.class, null, pager);
		pager.setRecordCount(dao().count(Role.class));
		return new QueryResult(list, pager);
	}
	
	
	/**
	 * 构建查询条件
	 * @param obj
	 * @return
	 */
	private Cnd bulidQureyCnd(Role obj){
		Cnd cnd=null;
		if(obj!=null){
			cnd=Cnd.where("1", "=", 1);
	        //按名称查询
	        if(!Strings.isEmpty(obj.getName()))
				cnd.and("name", "=", obj.getName());
	       
		}
		return cnd;
	}
}
