package com.vvcs.pharm.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.vvcs.pharm.pojo.AccRole;


public interface AccRoleMapper extends Mapper<AccRole>{
	/**
	* 说明： 添加用户角色
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
    int addAccountRole(AccRole accrole);
	/**
	* 说明： 根据用户id来查询所有角色id
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
  public List<AccRole> selectuserid(Integer user_id);
	/**
	* 说明： 根据id删除用户角色
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
  public int finaccountroledel(Integer user_id);
}
