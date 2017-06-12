package com.vvcs.pharm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vvcs.pharm.pojo.Pharmacy;
import com.vvcs.pharm.pojo.Prescription;
import com.vvcs.pharm.service.PharmacyService;
import com.vvcs.pharm.service.PrescriptionService;

@Controller
@RequestMapping
public class PharmacyController {
	/**
	 * 说明： 处方列表   查询所有未出药处方详情
	 * 
	 * @param 
	 *           
	 * @return
	 * @author 研发部：姜子濠
	 * @time 2017年3月27日
	 */
	@Autowired 
	PrescriptionService prescriptionService;
   @Autowired
   PharmacyService pharmacyService;
   @RequestMapping("consumer/pharmacy.do")
   public  ModelAndView  pharmacyselect(HttpServletRequest request,HttpServletResponse response,HttpSession session ) {
	   List<Pharmacy>listph=pharmacyService.findPharmacy1();
	   String id=request.getParameter("id");
	   System.out.println("---------------------"+id);
	   Prescription prescription=prescriptionService.findPrescriptionId(Integer.parseInt(id));
        System.out.println("编号\t"+prescription.getId());
	   for(Pharmacy ph:listph){
	  System.out.println(ph.getName());
	    }
	    request.setAttribute("pre", prescription);
	    request.setAttribute("listp", listph);
	return new ModelAndView("/pharmacy");
   }

}
    