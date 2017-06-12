package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.AccountMapper;
import com.vvcs.pharm.pojo.Account;

@Service
public class AccountService {
	/**
	* 说明： 添加员工
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	@Autowired
	AccountMapper accountMapper;
	public int addAcount(Account account){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 int count=accountMapper.addAccount(account);
		return count;
	}
	/**
	* 说明： 查询员工最大的值
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public Account selectAccountusername(String loginname){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 Account account=accountMapper.selectAccountusername(loginname);
		return account;
	
	}
	/**
	* 说明：根据pid来查询所有的用户信息
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
	public List<Account>selectAccountpid(Integer pid){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 List<Account>list=accountMapper.selectAccountpid(pid);
		return list;
	}
 	/**
 	* 说明：根据id删除用户信息
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年4月28日
 	*/
    public int finaccountdel(Integer id){
		 DataSourceContextHandler.setDataSourceContext("DB4");
		 int count =accountMapper.finaccountdel(id);
		return count; 
    }
 	/**
 	* 说明：编辑用户信息
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年4月28日
 	*/
    public int	updateaccount(Account account){
		 DataSourceContextHandler.setDataSourceContext("DB4"); 
		 int count=accountMapper.updateaccount(account);
		return count;
    }
 	/**
 	* 说明：根据id查询用户
 	* 
 	* @param 
 	* @return
 	* @author 研发部：姜子濠
 	* @time  2017年5月2日
 	*/
   public List<Account> findselectid(Integer id){
		 DataSourceContextHandler.setDataSourceContext("DB4");
		 List<Account>list=accountMapper.findselectid(id);
		return list; 
   }
   
    
}
