package com.vvcs.pharm.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="vvcs_account_role")
public class AccRole {
	@Id
	@Column(name="role_id")
	private Integer roleId;//角色id
	@Id
	@Column(name="user_id")
	private Integer userId; //用户id
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "AccRole [roleId=" + roleId + ", userId=" + userId + "]";
	}
	
}
