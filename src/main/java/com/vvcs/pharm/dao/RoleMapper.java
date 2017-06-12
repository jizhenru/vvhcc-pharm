package com.vvcs.pharm.dao;



import java.util.List;

import com.vvcs.pharm.pojo.Role;

public interface RoleMapper {
	/**
	* 说明： 添加角色
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public int addRole(Role role);
	/**
	* 说明： 查询所有角色
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public List<Role> selectRole();
	/**
	* 说明： 根据id查询所有角色
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public List<Role> selectRoleId(Integer id);
	
}
