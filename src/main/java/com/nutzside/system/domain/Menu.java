package com.nutzside.system.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("SYSTEM_MENU")
public class Menu {
	@Id
	private Long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String name;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String url;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String description;
	@Column
	private Long parentId;

	@ManyMany(target = Role.class, relation = "SYSTEM_ROLE_MENU", from = "MENUID", to = "ROLEID")
	private List<Role> roles;
	
	@Many(target = Organization.class, field = "parentId")
	private List<Organization> childrenMenu;
	
	public static Menu getInstance(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		menu.id = rs.getLong("ID");
		menu.name = rs.getString("NAME");
		menu.url = rs.getString("URL");
		menu.description = rs.getString("DESCRIPTION");
		return menu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<Organization> getChildrenMenu() {
		return childrenMenu;
	}

	public void setChildrenMenu(List<Organization> childrenMenu) {
		this.childrenMenu = childrenMenu;
	}
	
	
}