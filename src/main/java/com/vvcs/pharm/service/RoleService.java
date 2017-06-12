package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.RoleMapper;
import com.vvcs.pharm.pojo.Role;
@Service
public class RoleService {
	/**
	* 说明： 添加角色
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	@Autowired
	private RoleMapper roleMapper;
	public int addrole(Role role){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 int count=roleMapper.addRole(role);
		return count;
		 
	}
	/**
	* 说明：查询所有角色
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public List<Role> selectRole(){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 List<Role> role=roleMapper.selectRole();
		return role;
		
	}
	/**
	* 说明： 根据id查询所有角色
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
	public List<Role> selectRoleId(Integer id){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 List<Role> role=roleMapper.selectRoleId(id);
		return role;

	}
	
}
