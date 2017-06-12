package com.vvcs.pharm.pojo;

import java.io.Serializable;
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
@Table(name="vvcs_role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3031655365900673523L;
	@Id
	private Integer id;
	@Column(name="rolename")
	private String rolename;//角色名
	@Column(name="code")
	private String code;//角色名称
	@Column(name="description")
	private String description;//角色描述
	
	@Transient
	private Set<Account> accounts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String name) {
		this.rolename = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", code=" + code + ", description=" + description + ", accounts="
				+ accounts + "]";
	}
}
