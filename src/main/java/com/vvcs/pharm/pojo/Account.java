package com.vvcs.pharm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 说明：
 * @author 研发部：纪振儒
 * @version 1.0
 * @date 2017年2月27日
 */
@Table(name="vvcs_account")
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -319693329195402912L;
	@Id
	private Integer id;//id
	@Column(name="pid")
	private Integer pid;//富及id
	@Column(name="loginname")
	private String loginname;//用户名
	@Column(name="password")
	private String password;//密码
	@Column(name="name")
	private String name;//姓名
	@Column(name="sex")
	private String sex;//性别
	@Column(name="birthday")
	private Date birthday;//生日
	@Column(name="email")
	private String email;//email
	@Column(name="telephone")
	private String telephone;//电话
	@Column(name="remark")
	private String remark;//备注
	@Column(name="pharmacyid")
	private Integer pharmacyid;//药房id
	
	public Integer getPharmacyid() {
		return pharmacyid;
	}

	public void setPharmacyid(Integer pharmacyid) {
		this.pharmacyid = pharmacyid;
	}

	@Transient
	private Set<Role> roles;
	
	@Transient
	private String rolename;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}



	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", loginname=" + loginname + ", name=" + name + ", roles=" + roles + "]";
	}
}
