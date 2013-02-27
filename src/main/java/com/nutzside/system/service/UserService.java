package com.nutzside.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.IdEntityService;

import com.nutzside.system.domain.Role;
import com.nutzside.system.domain.User;

@IocBean(fields = { "dao" })
public class UserService extends IdEntityService<User> {

	public UserService() {
		super();
	}

	public UserService(Dao dao) {
		super(dao);
	}

	public List<User> list() {
		return query(null, null);
	}

	
	public Map<String, Object> Pagerlist( int pageNum ,int numPerPage,@Param("..") User obj) {
		
		Pager pager = dao().createPager((pageNum<1)?1:pageNum, (numPerPage < 1)? 20:numPerPage);
		List<User> list = dao().query(User.class, bulidQureyCnd(obj), pager);
		Map<String, Object> map = new HashMap<String, Object>();
		if (pager != null) {
			pager.setRecordCount(dao().count(User.class,bulidQureyCnd(obj)));
			map.put("pager", pager);
		}
		map.put("o", obj);
		map.put("pagerlist", list);
		return map;

	}
	
	public void update(User user) {
		dao().update(user);
	}
	
	public void delete(User user) {
		dao().delete(user);
	}

	public void insert(User user) {
		dao().insert(user);
	}

	public User view(Long id) {
		return dao().fetchLinks(fetch(id), "roles");
	}

	public User fetchByName(String name) {
		return fetch(Cnd.where("NAME", "=", name));
	}

	public List<String> getRoleNameList(User user) {
		dao().fetchLinks(user, "roles");
		List<String> roleNameList = new ArrayList<String>();
		for (Role role : user.getRoles()) {
			roleNameList.add(role.getName());
		}
		return roleNameList;
	}

	public void addRole(Long userId, Long roleId) {
		User user = fetch(userId);
		Role role = new Role();
		role.setId(roleId);
		user.setRoles(Lang.list(role));
		dao().insertRelation(user, "roles");
	}

	public void removeRole(Long userId, Long roleId) {
		dao().clear("SYSTEM_USER_ROLE",
				Cnd.where("USERID", "=", userId).and("ROLEID", "=", roleId));
	}

	public QueryResult getUserListByPager(int pageNumber, int pageSize) {
		Pager pager = dao().createPager(pageNumber, pageSize);
		List<User> list = dao().query(User.class, null, pager);
		pager.setRecordCount(dao().count(User.class));
		return new QueryResult(list, pager);
	}
	
	
	/**
	 * 构建查询条件
	 * @param obj
	 * @return
	 */
	private Cnd bulidQureyCnd(User obj){
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
