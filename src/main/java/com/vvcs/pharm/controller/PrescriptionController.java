package com.vvcs.pharm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
import com.vvcs.pharm.pojo.PrescriptionGeneric;
import com.vvcs.pharm.service.PrescriptionService;
import com.vvcs.pharm.service.AccountService;
import com.vvcs.pharm.service.DeviceService;
import com.vvcs.pharm.service.DrugboxService;
import com.vvcs.pharm.service.GenericService;
import com.vvcs.pharm.service.PharmacyService;

import net.sf.json.JSONArray;
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
    
    
    public static List<List<Drugbox>> drulist = new ArrayList<List<Drugbox>>();;
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
		Integer flag = 1;
		/*********处方分解**********/
		//生成药房ID
		Integer pharmacyId = 2;
		String pharmacyName = "华美大药房";
		//根据药房id查询药房内设备
		List<Device> devices = derivceService.findDeviceByPharmacyId(pharmacyId);
		/*********处方分解**********/
		String id = request.getParameter("id");
		List<Drugbox> drudboxlist = new ArrayList<Drugbox>();
		try {
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
			       if(drugboxs.size()!=0){
			    	  Drugbox drugbox = drugboxs.get(0);
			    	  if(drugbox.getDrugnum()-genericNum.getNum()>=0){
							drugbox.setDrugnum(genericNum.getNum());
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
			   JOptionPane.showMessageDialog(null, "设备药品不足.", "提示",JOptionPane.PLAIN_MESSAGE);   
	       	}
				return count;
            }
			/*********处方分解**********/
			//处理药筒与设备之间的关系
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
			JSONObject jsontotal = new JSONObject();
			jsontotal.put("Action", 1);
			jsontotal.put("DeriveNumber", drulist.size());
			for (List<Drugbox> listDrugbox : drulist) {
				int[] ColumnNumber=new int[listDrugbox.size()];
				int[] RowNumber=new int[listDrugbox.size()];
				int[] ZNumber=new int[listDrugbox.size()];
				int[] GenericNum=new int[listDrugbox.size()];
				for (int i = 0; i < listDrugbox.size(); i++) {
					Drugbox drugbox = listDrugbox.get(i);
					ColumnNumber[i]=Integer.parseInt(drugbox.getCoordinateX());
					RowNumber[i]=Integer.parseInt(drugbox.getCoordinateY());
					if(drugbox.getCoordinateZ()!=null){
						ZNumber[i]=Integer.parseInt(drugbox.getCoordinateZ());
					}else{
						ZNumber[i]=-1;
					}
					GenericNum[i]=drugbox.getDrugnum();
				}
				Drugbox drugbox = listDrugbox.get(0);
				Device device = derivceService.findedeviceidID(drugbox.getDeviceId());
				JSONObject json = new JSONObject();
				json.put("DrugsNumber", listDrugbox.size());
				json.put("PrescriptionID", prescription.getId());
				json.put("Action", 1);//处方编号
				json.put("DeciceName", device.getName());
				json.put("ColumnNumber", ColumnNumber);
				json.put("RowNumber", RowNumber);
				json.put("ZNumber", ZNumber);
				json.put("GenericNum", GenericNum);
				jsontotal.put(device.getName(), json);
			}
			/*********处方分解**********/
			String MQ_drugbox = jsontotal.toString();
			System.out.println(MQ_drugbox); 
			//超时处理
			ExecutorService exec = Executors.newFixedThreadPool(1);
			Callable<String> call = new Callable<String>() {  
			    public String call(){  
			        //开始执行耗时操作 
			    	Interface_mq interfacemq = new Interface_mq();
					String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox);
			        return sendMessage;
			    }  
			};
			try {  
			    Future<String> future = exec.submit(call);  
			    String obj = future.get(1000 * 3, TimeUnit.MILLISECONDS); //任务处理超时时间设为 3 秒  
			    System.out.println("任务成功返回:" + obj);
			} catch (TimeoutException ex) {
			    System.out.println("处理超时啦....");
			    flag=0;
			    //ex.printStackTrace();  
			} catch (Exception e) {
			    System.out.println("处理失败.");  
			    e.printStackTrace();  
			}  
		// 关闭线程池  
		exec.shutdown();
//			System.out.println(sendMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
//      return count;
		return flag;
     }
	
	/**
		* 说明： 更新处方状态和药桶药品数量 
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年6月16日
		*/
	@ResponseBody
	@RequestMapping("druggist/updatedrugbox")
	public Integer updatePrescriptionAndDrugbox(Integer PrescriptionId){
		JSONObject json = new JSONObject();
		json.put("PrescriptionId", PrescriptionId);
		String MQ_drugbox = json.toString();
		//超时处理
		ExecutorService exec = Executors.newFixedThreadPool(1);
		Callable<String> call = new Callable<String>() {  
		    public String call(){ 
		        //开始执行耗时操作 
		    	Interface_mq interfacemq = new Interface_mq();
				String sendMessage = interfacemq.SendMessage("req_queue", MQ_drugbox);
		        return sendMessage;
		    }
		};
		try {  
		    Future<String> future = exec.submit(call);  
		    String jsondata = future.get(1000, TimeUnit.MILLISECONDS); //任务处理超时时间设为 3 秒  
		    JSONArray jsonArray = JSONArray.fromObject(jsondata);
		    List<PrescriptionGeneric> list = (List<PrescriptionGeneric>) JSONArray.toCollection(jsonArray, PrescriptionGeneric.class);
		    /***********更新药桶信息*********/
			for (List<Drugbox> drugboxs : drulist) {
				for (Drugbox drugbox : drugboxs) {
					 for (PrescriptionGeneric pg : list) {
						
					}
					System.out.println(drugbox);
				}
			}
			/***********更新药桶信息*********/
		} catch (TimeoutException ex) {
		    System.out.println("处理超时啦....");
		    //flag=0;
		    //ex.printStackTrace();  
		} catch (Exception e) {
		    System.out.println("处理失败.");  
		    e.printStackTrace();  
		}  
		// 关闭线程池  
		exec.shutdown();
		return null;
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
 