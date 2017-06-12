package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.AccRoleMapper;
import com.vvcs.pharm.pojo.AccRole;
@Service
public class AccRoleService {
	@Autowired
	AccRoleMapper accRoleMapper;
	/**
	* 说明： 添加角色
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
	public int addAccountRole(AccRole accrole,String [] roleid){
		DataSourceContextHandler.setDataSourceContext("DB4");
		int num=0;
		for(int i=0;i<roleid.length;i++){
			accrole.setRoleId(Integer.parseInt(roleid[i]));
			num=accRoleMapper.addAccountRole(accrole);
		}
		return num;
	}
	
	/**
	* 说明： 根据用户id来查询所有角色id
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
  public List<AccRole> selectuserid(Integer user_id){
		DataSourceContextHandler.setDataSourceContext("DB4");
		 List<AccRole> accRole=accRoleMapper.selectuserid(user_id);
	return accRole;
  }
	/**
	* 说明： 根据id删除用户角色
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
 public int finaccountroledel(Integer user_id){
		DataSourceContextHandler.setDataSourceContext("DB4");
		 int count=accRoleMapper.finaccountroledel(user_id);
		return count;
		
  }

	
}
