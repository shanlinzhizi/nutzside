package com.nutzside.system.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

@Table("SYSTEM_SYSENUM")
public class SysEnum {
	@Id
	private Long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String name;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String description;
	@Many(target = SysEnumItem.class, field = "sysEnumId")
	private List<SysEnumItem> items;

	public static SysEnum getInstance(ResultSet rs) throws SQLException {
		SysEnum sysEnum = new SysEnum();
		sysEnum.id = rs.getLong("ID");
		sysEnum.name = rs.getString("NAME");
		sysEnum.description = rs.getString("DESCRIPTION");
		return sysEnum;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SysEnumItem> getItems() {
		return items;
	}

	public void setItems(List<SysEnumItem> items) {
		this.items = items;
	}
}