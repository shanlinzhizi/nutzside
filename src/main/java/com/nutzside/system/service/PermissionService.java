package com.nutzside.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.IdEntityService;

import com.nutzside.system.domain.Permission;

@IocBean(fields = { "dao" })
public class PermissionService extends IdEntityService<Permission> {
	public PermissionService() {
		super();
	}

	public PermissionService(Dao dao) {
		super(dao);
	}

	public List<Permission> list() {
		return query(null, null);
	}

	public Map<Long, String> map() {
		Map<Long, String> map = new HashMap<Long, String>();
		List<Permission> permissions = query(null, null);
		for (Permission permission : permissions) {
			map.put(permission.getId(), permission.getName());
		}
		return map;
	}

public Map<String, Object> Pagerlist( int pageNum ,int numPerPage,@Param("..") Permission obj) {
		
		Pager pager = dao().createPager((pageNum<1)?1:pageNum, (numPerPage < 1)? 20:numPerPage);
		List<Permission> list = dao().query(Permission.class, bulidQureyCnd(obj), pager);
		Map<String, Object> map = new HashMap<String, Object>();
		if (pager != null) {
			pager.setRecordCount(dao().count(Permission.class,bulidQureyCnd(obj)));
			map.put("pager", pager);
		}
		map.put("o", obj);
		map.put("pagerlist", list);
		return map;

	}
	public void insert(Permission permission) {
		dao().insert(permission);
	}

	public Permission view(Long id) {
		return fetch(id);
	}

	public void update(Permission permission) {
		dao().update(permission);
	}
	/**
	 * 构建查询条件
	 * @param obj
	 * @return
	 */
	private Cnd bulidQureyCnd(Permission obj){
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
