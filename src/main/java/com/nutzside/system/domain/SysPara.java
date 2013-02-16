package com.nutzside.system.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("SYSTEM_SYSPARA")
public class SysPara {
	@Id
	private Long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 100)
	private String name;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String value;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String description;

	public static SysPara getInstance(ResultSet rs) throws SQLException {
		SysPara sysPara = new SysPara();
		sysPara.id = rs.getLong("ID");
		sysPara.name = rs.getString("NAME");
		sysPara.value = rs.getString("VALUE");
		sysPara.description = rs.getString("DESCRIPTION");
		return sysPara;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}