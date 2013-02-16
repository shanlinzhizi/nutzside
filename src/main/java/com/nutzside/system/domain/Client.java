package com.nutzside.system.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("SYSTEM_CLIENT")
public class Client {
	@Id
	private Long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 100)
	private String sessionId;
	@Column
	private Long userId;
	@One(target = User.class, field = "userId")
	private User user;
	@Column
	private Timestamp logonTime;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String ipAddr;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 200)
	private String userAgent;

	public static Client getInstance(ResultSet rs) throws SQLException {
		Client client = new Client();
		client.id = rs.getLong("ID");
		client.sessionId = rs.getString("SESSIONID");
		client.userId = rs.getLong("USERID");
		client.logonTime = rs.getTimestamp("LOGONTIME");
		client.ipAddr = rs.getString("IPADDR");
		client.userAgent = rs.getString("USERAGENT");
		return client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getLogonTime() {
		return logonTime;
	}

	public void setLogonTime(Timestamp logonTime) {
		this.logonTime = logonTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}