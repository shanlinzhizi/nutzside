package com.nutzside.system.domain;

import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("SYSTEM_PERMISSION")
public class Permission {
	@Id
	private Long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 200)
	private String name;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String description;
	@Column
	private Long parentPermissionId;
	@One(target = Permission.class, field = "parentPermissionId")
	private Permission parentPermission;
	@Many(target = Permission.class, field = "parentPermissionId")
	private List<Permission> childrenPermissions;
	@ManyMany(target = Role.class, relation = "SYSTEM_ROLE_PERMISSION", from = "PERMISSIONID", to = "ROLEID")
	private List<Role> roles;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentPermissionId() {
		return parentPermissionId;
	}

	public void setParentPermissionId(Long parentPermissionId) {
		this.parentPermissionId = parentPermissionId;
	}

	public Permission getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}

	public List<Permission> getChildrenPermissions() {
		return childrenPermissions;
	}

	public void setChildrenPermissions(List<Permission> childrenPermissions) {
		this.childrenPermissions = childrenPermissions;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}