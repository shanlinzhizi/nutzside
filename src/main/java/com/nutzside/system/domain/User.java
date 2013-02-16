package com.nutzside.system.domain;

import java.sql.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

@Table("SYSTEM_USER")
public class User {
	@Id
	private Long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String number;
	@Column
	@ColDefine(type = ColType.CHAR, width = 44)
	@JsonField(ignore = true)
	private String password;
	@Column
	@ColDefine(type = ColType.CHAR, width = 24)
	@JsonField(ignore = true)
	private String salt;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String name;
	/**
	 * 性别 01:男;02:女（由系统枚举表维护）
	 */
	@Column
	@ColDefine(type = ColType.CHAR, width = 2)
	private String gender;
	@Column
	private Integer age;
	@Column
	private String birthday;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String phone;
	@Column("is_account_enabled")
	@ColDefine(notNull = false, type = ColType.BOOLEAN, width = 1)
	private Boolean accountEnabled;

	@Column("locked_date")
	@ColDefine(notNull = false, type = ColType.TIMESTAMP)
	private Date lockedDate;
	@Column("is_account_expired")
	@ColDefine(notNull = false, type = ColType.BOOLEAN, width = 1)
	private Boolean accountExpired;

	@Column("is_account_locked")
	@ColDefine(notNull = false, type = ColType.BOOLEAN, width = 1)
	private Boolean accountLocked;

	@Column("is_credentials_expired")
	@ColDefine(notNull = false, type = ColType.BOOLEAN, width = 1)
	private Boolean credentialsExpired;

	@Column("login_failure_count")
	@ColDefine(notNull = false, type = ColType.BOOLEAN, width = 11)
	private Integer loginFailureCount;

	
	@ManyMany(target = Role.class, relation = "SYSTEM_USER_ROLE", from = "USERID", to = "ROLEID")
	private List<Role> roles;
	@ManyMany(target = Message.class, relation = "SYSTEM_MESSAGE_RECEIVERUSER", from = "USERID", to = "MESSAGEID")
	private List<Message> receivedMessages;
	@Many(target = Message.class, field = "senderUserId")
	private List<Message> sentMessages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public Boolean getAccountEnabled() {
		return accountEnabled;
	}

	public void setAccountEnabled(Boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Boolean getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

}