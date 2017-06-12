package com.vvcs.pharm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.mq.Interface_mq;
import com.vvcs.pharm.pojo.Account;
import com.vvcs.pharm.pojo.Device;
import com.vvcs.pharm.pojo.Drugbox;
import com.vvcs.pharm.pojo.Generic;
import com.vvcs.pharm.pojo.GenericNum;
import com.vvcs.pharm.pojo.Pharmacy;
import com.vvcs.pharm.pojo.Prescription;
import com.vvcs.pharm.pojo.PrescriptionDetails;
import com.vvcs.pharm.service.PrescriptionService;
import com.vvcs.pharm.service.AccountService;
import com.vvcs.pharm.service.DeviceService;
import com.vvcs.pharm.service.DrugboxService;
import com.vvcs.pharm.service.GenericService;
import com.vvcs.pharm.service.PharmacyService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class PrescriptionController {
	@Autowired
	PrescriptionService prescriptionService;
	@Autowired
	GenericService genericService;
	@Autowired 
	PharmacyService pharmacyService;
	@Autowired
	DrugboxService drugboxService;
	@Autowired
    private AccountService accountService;
    @Autowired
    private DeviceService derivceService;
	/**
	 * 说明： 处方列表   查询所有未出药的处方列表
	 *                              
	 * @param 
	 *   
	 * @return
	 * @author 研发部：姜子濠
	 * @time 2017年3月27日
	 */
	@ResponseBody
	@RequestMapping("druggist/table.do") 
	public String prescription(String order,Integer offset, Integer limit) throws IOException {
		List<Prescription> prescriptions = prescriptionService.test03(order,offset, limit,0);
		int total = prescriptionService.findPrescriptionOutStatusNum();
		for (Prescription prescription : prescriptions) { // 循环获取处方
			String username="";
		//查询患者名字
	     List<Account>listA=accountService.findselectid(prescription.getPatientId());//获取用户id
	     for(Account a:listA){
	    	  username=username+"\t"+a.getName();
      }
        //查询药房名字
		  String nameyf="";	
		   List<Pharmacy>listPh=pharmacyService.findPharmacy(prescription.getAppointID()); //获取处方里面药房id值
			for(Pharmacy ph:listPh){
				nameyf=nameyf+"\t"+ph.getName();
	      }
			String desc = "";
			Set<PrescriptionDetails> prescriptionDetails = prescription.getPrescriptionDetails(); // 获取处方详情
			for (PrescriptionDetails p1 : prescriptionDetails) {// 遍历处方详情集合
				Generic generic = genericService.test02(p1.getGenericID());// 根据处方详情的id来查找处方id
				desc = desc + "\t" + generic.getGenericName() + "*" + p1.getMedicineNum();
    }  
	   DataSourceContextHandler.clearDataSourceContext();
	      prescription.setDesc(desc);
		  prescription.setNameyf(nameyf);
	      prescription.setUsername(username);
		} 
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", prescriptions);
		return json.toString();
      }  
	/**
	 * 说明： 处方列表   修改出药状态
	 * 
	 * @param 
	 * 
	 * @return
	 * @author 研发部：姜子濠
	 * @time 2017年3月27日
	 */
	@ResponseBody
	
	@RequestMapping("druggist/updatePrescription.do")
	public int updatePrescription(HttpServletRequest request) {
		/*********处方分解**********/
		//生成药房ID
		Integer pharmacyId = 2;
		String pharmacyName = "华美大药房";
		//根据药房id查询药房内设备
		List<Device> devices = derivceService.findDeviceByPharmacyId(pharmacyId);
		/*********处方分解**********/
		//根据设备查找药房药筒 
//		List<Drugbox> drudboxlistall = new ArrayList<Drugbox>();
//		if(devices!=null){
//			for (Device device : devices) {
//				List<Drugbox> drudboxs = drugboxService.findDrugboxByDriveId(device.getId());
//				for (Drugbox drugbox : drudboxs) {
//					drudboxlistall.add(drugbox);
//				}
//			}
//		}
		String id = request.getParameter("id");
		/*******************更新药桶药品数量************************/
		List<Drugbox> drudboxlist = new ArrayList<Drugbox>();
		try {
	   String desc="";
         //获取处方中的药品信息
       int intId = Integer.parseInt(id);
	    Prescription prescription = prescriptionService.findPrescriptionId(intId);
			Set<PrescriptionDetails> prescriptionDetails = prescription.getPrescriptionDetails();//获取处方详情
			List<GenericNum> GenericNums = new ArrayList<GenericNum>();
			//遍历处方详情
			for (PrescriptionDetails prescriptionDetail : prescriptionDetails) {
				GenericNum genericNum = new GenericNum();
				Generic generic = genericService.test02(prescriptionDetail.getGenericID());// 根据药品id查询药品
				genericNum.setNum(prescriptionDetail.getMedicineNum());
				genericNum.setGeneric(generic.getGenericName().trim());
				GenericNums.add(genericNum);                    
	         }
	      //根据药品信息查询药桶坐标
		    for (GenericNum genericNum : GenericNums) {
				 for(Device dev:devices){
			List<Drugbox> drugboxs = drugboxService.findDrugboxByDriveAndDrug(dev.getDeviceid(),genericNum.getGeneric());
			       //desc=desc+"\t"+dev.getName();    //设备名
			       if(drugboxs.size()!=0){
			    	  Drugbox drugbox = drugboxs.get(0);
			    	  if(drugbox.getDrugnum()-genericNum.getNum()>=0){
							drugbox.setDrugnum(drugbox.getDrugnum()-genericNum.getNum());
							drudboxlist.add(drugbox);
							break;
			       }
				break;
         }
	       } 
		 }
		/****MQ通信****/
			if(drudboxlist.size()!=GenericNums.size()){
				int count=0;
				if(count==0){
			   JOptionPane.showMessageDialog(null, "该药筒已无药.", "提示",JOptionPane.PLAIN_MESSAGE);   
	       	}
				return count;
            }
			/*********处方分解**********/
			//处理药筒与设备直接的关系
			List<List<Drugbox>> drulist = new ArrayList<List<Drugbox>>();
			Set<Integer> DeviceSet = new HashSet<Integer>();
			for (Drugbox drugbox : drudboxlist) {//获取设备
				DeviceSet.add(drugbox.getDeviceId());
			}
			for (Integer integer : DeviceSet) {
				List<Drugbox> listdrugboxbydevice = new ArrayList<Drugbox>();
				for (Drugbox drugbox : drudboxlist) {
					if(integer == drugbox.getDeviceId()){
						listdrugboxbydevice.add(drugbox);
					}
				}
				drulist.add(listdrugboxbydevice);
			}
			System.out.println(drulist);
			/*********处方分解**********/
			int[] ColumnNumber=new int[drudboxlist.size()];
			int[] RowNumber=new int[drudboxlist.size()];
			for (int i = 0; i < drudboxlist.size(); i++) {
				Drugbox drugbox = drudboxlist.get(i);
				ColumnNumber[i]=Integer.parseInt(drugbox.getCoordinateX());
				RowNumber[i]=Integer.parseInt(drugbox.getCoordinateY());
       }
     	int total = drudboxlist.size();
		JSONObject json = new JSONObject();
			json.put("DeciceName", desc);
			json.put("DrugsNumber", total);
			json.put("Action", 1);
			json.put("ColumnNumber", ColumnNumber);
			json.put("RowNumber", RowNumber);
			String MQ_drugbox = json.toString();
			System.out.println(MQ_drugbox); 
  /*		Interface_mq interfacemq = new Interface_mq();
			String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox);
			System.out.println(sendMessage);*/
           //更新药桶药品数量
			for (Drugbox drugbox : drudboxlist) {
				Integer i = drugboxService.updateDrugboxById1(drugbox);
				System.out.println(i);  
          	} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	 /************************药桶更新完成******************************/
       int count=0;
    	Prescription prescription1 = new Prescription();
		prescription1.setOutStatus(1);
		prescription1.setId(Integer.parseInt(id));
	    count = prescriptionService.test04(prescription1);
		if (count > 0) {
			System.out.println("处方状态修改成功");
	   }  
      return count;
     }
	//分解处方
	@ResponseBody
	@RequestMapping("druggist/fenjiechufang.do")
	public int fenjiechufang(HttpServletRequest request,String order,Integer offset, Integer limit){
    ModelAndView mav=null;
    int count=0;
	String id = request.getParameter("id");
	 List<Device>listd=derivceService.selectdevice();  //查询所有设备
     for(Device d:listd){
    //根据药筒的设备id 查询所有药品  
      List<Prescription> prescriptions = prescriptionService.selectPrescrptionInfo();
     for (Prescription prescription : prescriptions) {	
     String desc = ""; 
      Set<PrescriptionDetails> prescriptionDetails = prescription.getPrescriptionDetails(); // 获取处方详情
	  for (PrescriptionDetails p1 : prescriptionDetails) {// 遍历处方详情集合
		Generic generic = genericService.test02(p1.getGenericID());// 根据处方详情的id来查找处方id
		desc = desc + "\t" + generic.getGenericName() + "*" + p1.getMedicineNum();
		String [] split=desc.split(","); //截取字符串  
		for(int i=0; i<split.length;i++){
         System.out.println("split"+split[i]);
   }
             } 
         }                            
      }
     
	 Prescription prescription1 = new Prescription();
     prescription1.setOutStatus(1);
     prescription1.setId(Integer.parseInt(id));
     count = prescriptionService.test04(prescription1);
    if (count > 0) {
         System.out.println("处方状态修改成功 ");
    }
  return count;
	}
        
	/**
	 * 说明： 处方列表   根据时间来查询处方列表进行分页
	 * 
	 * @param 
	 *           
	 * @return
	 * @author 研发部：姜子濠
	 * @time 2017年3月28日
	 */ 
		@ResponseBody
	   @RequestMapping("druggist/OutTime.do")
	   public String PrescriptionTimeByOutstuts(Integer offset, Integer limit,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		   request.setCharacterEncoding("utf-8");
		   String outTime=request.getParameter("outTime"); //获取输入时间
		   String outTime1=request.getParameter("outTime1");
		   List<Prescription> listp=prescriptionService.test05(outTime, outTime1, 0,offset,limit);
		   int total = prescriptionService.findPrescriptionNumByTime(outTime, outTime1, 0);
		    System.out.println("list等于"+listp.size());
			 for(Prescription p:listp){ //循环获取处方
			  String username="";
				//查询患者名字
			     List<Account>listA=accountService.findselectid(p.getPatientId());//获取用户id
			     for(Account a:listA){
			    	  username=username+"\t"+a.getName();
             }
		     //查询药房名字
		      	 String nameyf="";
				 List<Pharmacy>listPh=pharmacyService.findPharmacy(p.getAppointID()); //获取处方里面药房id值
					for(Pharmacy ph:listPh){
       			nameyf=nameyf+"\t"+ph.getName();
               }
             String  desc="";
		      Set<PrescriptionDetails>prescriptionDetails=p.getPrescriptionDetails(); //获取处方详情
		      for(PrescriptionDetails p1:prescriptionDetails){//遍历处方详情集合
		    	   Generic generic=genericService.test02(p1.getGenericID());//根据处方详情的id来查找处方id
		          desc=desc+"药品\t"+generic.getGenericName()+"\t*"+p1.getMedicineNum();
            
		      }
		      System.out.println(p.getId()+"\t"+p.getOutTime());
		      DataSourceContextHandler.clearDataSourceContext();
		      p.setDesc(desc);
		      p.setNameyf(nameyf);
			  p.setUsername(username);
			 } 
			 JSONObject json = new JSONObject();
		     json.put("total", total);
		     json.put("rows", listp);
	      	return json.toString();  
	   }		
		/**
		 * 说明： 处方历史   根据用户来查询所有的处方历史并 进行分页
		 * 
		 * @param 
		 *           
		 * @return
		 * @author 研发部：姜子濠
		 * @time 2017年3月29日
		 */
		@ResponseBody
		@RequestMapping("consumer/history_list.do") 
		public 	List<Prescription>  prescription1(String order, Integer offset, Integer limit,HttpServletRequest request,HttpSession session ) throws IOException {                       
			  Cookie[] cookies1=request.getCookies();
				String username = "";
			      if(cookies1 != null){
			      for(Cookie cookie:cookies1){
			      if(cookie.getName().equals("username"))
			        username = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			      }}
			System.out.print("用户名"+username);
			  Account account1=accountService.selectAccountusername(username);
			  System.out.print("用户id等于"+account1.getId());
			List<Prescription> prescriptions = prescriptionService.test06(account1.getId(),1);//根据用户id来查找 历史处方
		   int total = prescriptionService.findPrescriptionOutStatusNum();
			for (Prescription prescription : prescriptions) { // 循环获取处方
				String nameyf="";   
				List<Pharmacy>listPh=pharmacyService.findPharmacy(prescription.getAppointID()); //获取处方里面药房id值
				for(Pharmacy ph:listPh){
					nameyf=nameyf+"\t"+ph.getName();
				}
	            String desc = "";
				Set<PrescriptionDetails> prescriptionDetails = prescription.getPrescriptionDetails(); // 获取处方详情
				for (PrescriptionDetails p1 : prescriptionDetails) {// 遍历处方详情集合
					Generic generic = genericService.test02(p1.getGenericID());// 根据处方详情的id来查找处方id
					desc = desc + "\t" + generic.getGenericName() + "*" + p1.getMedicineNum();
             }
				DataSourceContextHandler.clearDataSourceContext();
				prescription.setNameyf(nameyf);
				prescription.setDesc(desc);
			} 
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("rows", prescriptions);
        	return prescriptions;
      
		}  
		/**
		 * 说明：   根据用户来查询所有未领药处方
		 * 
		 * @param 
		 *           
		 * @return
		 * @author 研发部：姜子濠
		 * @time 2017年3月29日
		 */
	  @ResponseBody
	  @RequestMapping("consumer/unfinished_list.do") 
	  public 	List<Prescription>  prescription2(String order, Integer offset, Integer limit,HttpServletRequest request,HttpSession session ) throws IOException {                       
			  Cookie[] cookies1=request.getCookies();
				String username = "";
			      if(cookies1 != null){
			      for(Cookie cookie:cookies1){
			      if(cookie.getName().equals("username"))
			        username = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
      }}
	    Account account1=accountService.selectAccountusername(username);
	    List<Prescription> prescriptions = prescriptionService.test06(account1.getId(),0);//根据用户id来查找 历史处方
	  int total = prescriptionService.findPrescriptionOutStatusNum();
	      for (Prescription prescription : prescriptions) { // 循环获取处方
				String nameyf="";
				List<Pharmacy>listPh=pharmacyService.findPharmacy(prescription.getAppointID()); //获取处方里面药房id值
				for(Pharmacy ph:listPh){
					nameyf=nameyf+"\t"+ph.getName();
	       }
	           String desc = "";
				Set<PrescriptionDetails> prescriptionDetails = prescription.getPrescriptionDetails(); // 获取处方详情
				for (PrescriptionDetails p1 : prescriptionDetails) {// 遍历处方详情集合
					Generic generic = genericService.test02(p1.getGenericID());// 根据处方详情的id来查找处方id
					desc = desc + "\t" + generic.getGenericName() + "*" + p1.getMedicineNum();
				}
				DataSourceContextHandler.clearDataSourceContext();
				prescription.setNameyf(nameyf);
				prescription.setDesc(desc);
			}
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("rows", prescriptions);   
			return prescriptions;
		}
		/**
		 * 说明： 修改预约药房状态 进行分页
		 * 
		 * @param 
		 *           
		 * @return
		 * @author 研发部：姜子濠
		 * @throws IOException 
		 * @throws ServletException 
		 * @time 2017年3月29日
		 */
                              
  @ResponseBody
  @RequestMapping("consumer/updatepharmacy.do")
 public int updateupdatepharmacy(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException{
	   String appid=request.getParameter("id");
       String  id1=request.getParameter("id1");
	     Prescription prescription=new Prescription();
	      prescription.setAppointID(Integer.parseInt(appid));
	      prescription.setId(Integer.parseInt(id1));
	  int count=prescriptionService.updatePrescriptionAppoint(prescription);
	  if(count>0){
		  System.out.println("预约修改成功");
		  request.getRequestDispatcher("/consumer/unfinished_list.html").forward(request, response);
	  }
	  return count;  
  }
	/**
	 * 说明： 处方列表
	 * 
	 * @param offset:当前页码
	 *            limit：每页显示记录数 order：排序方式
	 * @return
	 * @author 研发部：纪振儒
	 * @time 2017年3月27日
	 */
	@RequestMapping("druggist/medicinelist.do")
	@ResponseBody
	public String medicinelist(String order, Integer offset, Integer limit) {
   
		List<Prescription> prescriptions = prescriptionService.findPrescriptionByPage(order, offset, limit);// 分页获取处方
		int total = prescriptionService.findPrescriptionNum();
		for (Prescription prescription : prescriptions) {// 遍历处方获取处方详情
			String username="";
			//查询患者名字
	     List<Account>listA=accountService.findselectid(prescription.getPatientId());//获取用户id
	     for(Account a:listA){
	    	  username=username+"\t"+a.getName();
	     }
	     //查询药房名字
			String nameyf="";
			List<Pharmacy>listPh=pharmacyService.findPharmacy(prescription.getAppointID()); //获取处方里面药房id值
			for(Pharmacy ph:listPh){
				nameyf=nameyf+"\t"+ph.getName();
			}
			String desc = "";
			Set<PrescriptionDetails> prescriptionDetails = prescription.getPrescriptionDetails();// 获取处方详情集合
			for (PrescriptionDetails prescriptionDetails2 : prescriptionDetails) {// 遍历处方详情集合
				Generic generic = genericService.test02(prescriptionDetails2.getGenericID());// 根据处方详情的药品id获取药品
				desc = desc + "\t" + generic.getGenericName() + "*" + prescriptionDetails2.getMedicineNum();// 拼接处方详细
			}
			DataSourceContextHandler.clearDataSourceContext();
			prescription.setDesc(desc);
		    prescription.setNameyf(nameyf);
		    prescription.setUsername(username);
		}

		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", prescriptions);
		return json.toString();
	}
	/**     
			* 说明： 根據時間分頁顯示處方
			* 
			* @param 
			* @return
			* @author 研发部：纪振儒
			* @time  2017年3月29日
			*/
		@ResponseBody
		   @RequestMapping("druggist/PrescriptionCreateDate")
		   public String PrescriptionTime(Integer offset, Integer limit,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			   request.setCharacterEncoding("utf-8");
			   String startPrescriptionCreateDate=request.getParameter("startPrescriptionCreateDate"); //获取输入时间
			   String endPrescriptionCreateDate=request.getParameter("endPrescriptionCreateDate");
			   List<Prescription> listp=prescriptionService.findPrescriptionByTime(startPrescriptionCreateDate, endPrescriptionCreateDate,offset,limit);
			   int total = prescriptionService.findPrescriptionNumByTime(startPrescriptionCreateDate, endPrescriptionCreateDate);
				 for(Prescription p:listp){ //循环获取处方
						String username="";
						//查询患者名字
				     List<Account>listA=accountService.findselectid(p.getPatientId());//获取用户id
				     for(Account a:listA){
				    	  username=username+"\t"+a.getName();
				     } 
				  //查询药房名字
			        String nameyf="";
						List<Pharmacy>listPh=pharmacyService.findPharmacy(p.getAppointID()); //获取处方里面药房id值
						for(Pharmacy ph:listPh){
							nameyf=nameyf+"\t"+ph.getName();
					  }
				   String  desc="";
			    Set<PrescriptionDetails>prescriptionDetails=p.getPrescriptionDetails(); //获取处方详情
			      for(PrescriptionDetails p1:prescriptionDetails){//遍历处方详情集合
			    	   Generic generic=genericService.test02(p1.getGenericID());//根据处方详情的id来查找处方id
			          desc=desc+"药品\t"+generic.getGenericName()+"\t*"+p1.getMedicineNum();
			           System.out.println("药品\t"+generic.getGenericName()+"\t数量"+p1.getMedicineNum());
			      }
			   System.out.println(p.getId()+"\t"+p.getOutTime());
			      DataSourceContextHandler.clearDataSourceContext();
			      p.setDesc(desc);
				    p.setNameyf(nameyf);
				    p.setUsername(username);
				 }  
				 JSONObject json = new JSONObject();
					json.put("total", total);
					json.put("rows", listp);
					return json.toString();  
	}
}
 