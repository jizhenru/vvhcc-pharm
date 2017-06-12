package com.vvcs.pharm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vvcs.pharm.pojo.AccRole;
import com.vvcs.pharm.pojo.Account;
import com.vvcs.pharm.pojo.Role;
import com.vvcs.pharm.service.AccRoleService;
import com.vvcs.pharm.service.AccountService;
import com.vvcs.pharm.service.RoleService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccRoleService accroleService;
    @RequestMapping("Administrator/AddAcount.do")
	@ResponseBody
	public ModelAndView AddAcount(HttpServletRequest request){
    	ModelAndView mav=null;
	    int count;
    	String username=request.getParameter("username");
    	String pwd=request.getParameter("pwd");
    	String name=request.getParameter("name");
    	String sex=request.getParameter("sex");
    	String telephone=request.getParameter("telephone");
    	String remark=request.getParameter("remark");
    	Account account=new Account();
        account.setPid(1);
    	account.setLoginname(username);
    	account.setPassword(pwd);
    	account.setName(name);
    	account.setSex(sex);
    	account.setTelephone(telephone);
    	account.setRemark(remark);
    	count=accountService.addAcount(account);
         if(count>0){
         	 String [] roleid=request.getParameterValues("roleid");
           	 System.out.println("角色ID="+roleid);
          	 Account account1=accountService.selectAccountusername(username);
        	 System.out.println("用户ID="+account1.getId());
        	 AccRole accrole=new AccRole();
        	 accrole.setUserId(account1.getId());
            int a=accroleService.addAccountRole(accrole, roleid);
         }
		return mav;
    	
    }
    @RequestMapping("Administrator/selectRole.do")
	@ResponseBody
	public ModelAndView selectRole(HttpServletRequest request){
    	ModelAndView mav=null;
    List<Role> role1=roleService.selectRole();
	request.setAttribute("role", role1);
	 mav=new ModelAndView("addpeople");
	 return mav;
   
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
    @RequestMapping("Administrator/selectShow.do")
    @ResponseBody
    public List<Account> selectpid(){
    	List<Account>list=accountService.selectAccountpid(1);//根据pid查询所有用户
    	for(Account ac:list){   
    		 String rolename="";
    		List<AccRole>listp=accroleService.selectuserid(ac.getId());//根据用户id查询 用户角色里面的所有角色
    		 for(AccRole role:listp){
    			 List<Role> ro= roleService.selectRoleId(role.getRoleId());//根据角色id查询 所有角色名
    			 for(Role r:ro){
    			  rolename=rolename+"\t"+r.getRolename();	 
    			 }
    			 
    		 }
    		 ac.setRolename(rolename);
    	}
                 
		return list;
    }
	/**
	* 说明：删除用户角色和用户
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
    @RequestMapping("Administrator/deletecount.do")
    @ResponseBody
    public int deletecount(HttpServletRequest request){
    	int count=0;
    	String id=request.getParameter("id");
    	count=accroleService.finaccountroledel(Integer.parseInt(id));//删除用户角色		
    	count=accountService.finaccountdel(Integer.parseInt(id));//删除用户名
		return count;
    }
	/**
	* 说明：编辑用户
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月28日
	*/
    @RequestMapping("Administrator/updateaccount.do")
	@ResponseBody
	public int updateaccount(HttpServletRequest request){
    	int count;
    	String id=request.getParameter("id");
    	String name=request.getParameter("name");
    	String sex=request.getParameter("sex");
    	String telephone=request.getParameter("telephone");
    	String remark=request.getParameter("remark");
    	Account account=new Account();
    	account.setName(name);
    	account.setSex(sex);
    	account.setTelephone(telephone);
    	account.setRemark(remark);
    	account.setId(Integer.parseInt(id));
    	return count=accountService.updateaccount(account);
    }
    
}
