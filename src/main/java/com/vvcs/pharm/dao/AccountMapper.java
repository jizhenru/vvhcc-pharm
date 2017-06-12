package com.vvcs.pharm.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.vvcs.pharm.pojo.Account;

public interface AccountMapper extends Mapper<Account> {
	/**
	* 说明： 添加员工
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/

     public int	addAccount(Account account);
 	/**
 	* 说明： 根据用户名查询用户id
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年4月27日
 	*/
    public Account selectAccountusername(String loginname);
 	/**
 	* 说明：根据pid来查询所有的用户信息
 	* 
 	* @param 
 	* @return 
 	* @author 研发部：姜子濠
 	* @time  2017年4月27日
 	*/
    List<Account>selectAccountpid(Integer pid);
 	/**
 	* 说明：根据id删除用户信息
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年4月28日
 	*/
    public int finaccountdel(Integer id);
 	/**
 	* 说明：编辑用户信息
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年4月28日
 	*/
    public int	updateaccount(Account account);
 	/**
 	* 说明：根据id查询用户
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年5月2日
 	*/
   public List<Account> findselectid(Integer id);
}
