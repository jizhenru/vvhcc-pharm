package com.vvcs.pharm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vvcs.pharm.pojo.Generic;
import com.vvcs.pharm.service.GenericService;

@Controller
public class GenericController {
	@Autowired
	private GenericService genericService;
	/**
	* 说明： 查询所有药品
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	@RequestMapping("Administrator/showGeneric.do")
	@ResponseBody
	public List<Generic>selectGenric(){
		List<Generic>list=genericService.selectGeneric();
		System.out.println("等于"+list.size());
		return list;
	}
	/**
	* 说明：删除药品 
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	@RequestMapping("Administrator/delGeneric.do")
	@ResponseBody
	public  int delGeneric(HttpServletRequest request){
		String id=request.getParameter("id");
		int count=genericService.deleteGeneric(Integer.parseInt(id));
		return count;
	}
	/**
	* 说明：添加药品 
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月27日
	*/
	@RequestMapping("Administrator/addGeneric.do")
	@ResponseBody
	public int addGeneric(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		String genericName=request.getParameter("GenericName");
		String manufacture=request.getParameter("manufacture");
        String approvalNum=request.getParameter("Approvalnum");
        Generic generic=new Generic();
         generic.setGenericName(genericName);
         generic.setManufacture(manufacture);
         generic.setApprovalNum(approvalNum);
         int count=genericService.addGeneric(generic);
         if(count>0){
        	 request.getRequestDispatcher("addmedicine.html").forward(request, response);
         }
		return count;
	}
	@RequestMapping("Administrator/updateGeneric.do")
	@ResponseBody
	public int updateGeneric(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		String genericName=request.getParameter("genericName");
		String manufacture=request.getParameter("manufacture");
        String approvalNum=request.getParameter("approvalNum");
        Generic generic=new Generic();
         generic.setGenericName(genericName);
         generic.setManufacture(manufacture);
         generic.setApprovalNum(approvalNum);
         generic.setId(Integer.parseInt(id));
         int count=genericService.updateGeneric(generic);
		return count;
	}

	
	
}
