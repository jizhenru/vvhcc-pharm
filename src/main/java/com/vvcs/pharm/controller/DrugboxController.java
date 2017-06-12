 package com.vvcs.pharm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.mq.Interface_mq;
import com.vvcs.pharm.pojo.Device;
import com.vvcs.pharm.pojo.Drugbox;
import com.vvcs.pharm.service.DeviceService;
import com.vvcs.pharm.service.DrugboxService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("pharm")
public class DrugboxController {
	@Autowired
	private DrugboxService pharmacistService;
	  @Autowired
	  private DeviceService derivceService;
	/**
		 * 说明： 根据设备id查询药桶并发送到页面
		 * 
		 * @param 
		 * @return
		 * @author 研发部：纪振儒
		 * @time  Mar 23, 2017
		 */
	@RequestMapping("druggist/showDrugbox")
	@ResponseBody
	public String showDrugbox(String order,@RequestParam(value="",required=false) String query, Integer offset, Integer limit,Integer id,HttpServletRequest request){
		try {
			String parameter = request.getParameter("query");
			int total = pharmacistService.queryDrugboxNumByDeviceId(id);
			if(parameter!=null || "".equals(query)){
				query = new String(request.getParameter("query").getBytes("iso-8859-1"), "utf-8");
				total = pharmacistService.queryDrugboxNumByDeviceIdAndQuery(id,query);
			}
			List<Drugbox> drugboxs = pharmacistService.queryDrugboxPageByDeviceId(query,offset,limit,id);
			DataSourceContextHandler.clearDataSourceContext();
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("rows", drugboxs);
			return json.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 说明： 根据设备id查询所有药桶并发送到页面 //管理员模块
	 * 
	 * @param 
	 * @return
	 * @author 研发部：姜子濠
	 * @time  Mar 23, 2017
	 */
	@RequestMapping("Administrator/showDrugbox.do")
	@ResponseBody
	public List<Drugbox> findDrugboxById1(HttpServletRequest request){
		String deviceid=request.getParameter("deviceid");
		List<Drugbox> drugboxs = pharmacistService.findDrugboxById1(Integer.parseInt(deviceid));
		return drugboxs;
		
	}
	
	/**
	 * 说明： 根据设备id查询所有药桶并发送到页面  //药剂师模块
	 * 
	 * @param 
	 * @return
	 * @author 研发部：姜子濠
	 * @time  Mar 23, 2017
	 */
	@RequestMapping("druggist/showDrugbox1.do")
	@ResponseBody
	public List<Drugbox> findDrugboxByIdd1(HttpServletRequest request){
		String deviceid=request.getParameter("deviceid");
		List<Drugbox> drugboxs = pharmacistService.findDrugboxById1(Integer.parseInt(deviceid));
		return drugboxs;
		
	}
	/**
	* 说明： 修改药筒工作状态 //管理员模块
	* 
	* @param 
	* @return
	* @author 研发部：姜子豪
	 * @throws IOException 
	* @time  2017年4月27日
	*/
	@RequestMapping("Administrator/findDrugboxupdate.do")
	@ResponseBody
	public int findDrugboxMapper(Integer id ,Integer stauts,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Drugbox drugbox=new Drugbox();
		if(stauts==0){
			drugbox.setStatus(1);
		}else{
			drugbox.setStatus(0);
		}
       drugbox.setId(id);
	  int count =pharmacistService.findrugboxupdate(drugbox);
	  return count;
	} 
	
	/**
	* 说明： 修改药筒工作状态 //药剂师模块
	* 
	* @param 
	* @return
	* @author 研发部：姜子豪
	 * @throws IOException 
	* @time  2017年4月27日
	*/
	@RequestMapping("druggist/findDrugboxupdate1.do")
	@ResponseBody
	public int findDrugboxMapper1(Integer id ,Integer stauts,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Drugbox drugbox=new Drugbox();
		if(stauts==0){
			drugbox.setStatus(1);
		}else{
			drugbox.setStatus(0);
		}
       drugbox.setId(id);
	  int count =pharmacistService.findrugboxupdate(drugbox);
	  return count;
	} 
	/**
	* 说明:修改并增加上药数量
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月7日
	*/
	@RequestMapping("Administrator/findrugboxupdateId.do")
	@ResponseBody
	public Map<String,Object> findrugboxupdateId(Integer num,Integer deviceId,String coordinateX,String coordinateY){
		Map<String,Object> result = new HashMap<String, Object>();
		int count=0;
		if(num!=null && deviceId!=null && coordinateX!=null && coordinateY!=null){
		Drugbox drugbox1=pharmacistService.findDrugboxdeviceid(deviceId, coordinateX, coordinateY);
			Drugbox drugbox=new Drugbox();
			 System.out.println("------"+num);
			 drugbox.setCoordinateX(coordinateX);
		     drugbox.setCoordinateY(coordinateY);
		     drugbox.setDeviceId(deviceId);
			drugbox.setDrugnum(drugbox1.getDrugnum()+num);
			/******************上药MQ**********************/
			JSONObject json = new JSONObject();
			int[] ColumnNumber=new int[1];
			int[] RowNumber=new int[1];
			ColumnNumber[0]=Integer.parseInt(drugbox.getCoordinateX());
			RowNumber[0]=Integer.parseInt(drugbox.getCoordinateY());
			json.put("DeciceName", "E09");
			json.put("DrugsNumber", 1); 
			json.put("Action", 1);
			json.put("ColumnNumber", ColumnNumber);
			json.put("RowNumber", RowNumber);
			String MQ_drugbox = json.toString();
			Interface_mq interfacemq = new Interface_mq();               
		/*	String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox);	//连接设备 
			System.out.println(sendMessage);*/
			count=pharmacistService.findrugboxupdateid(drugbox);			
		}
		result.put("flag", count);
		 return result;
	}
	/**
	* 说明:修改并增加上药数量 //药剂师 模块
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月7日
	*/
	@RequestMapping("druggist/findrugboxupdateId.do")
	@ResponseBody
	public Map<String,Object> findrugboxupdateIddd(Integer num,Integer deviceId,String coordinateX,String coordinateY){
		Map<String,Object> result = new HashMap<String, Object>();
		int count=0;
		if(num!=null && deviceId!=null && coordinateX!=null && coordinateY!=null){
		Drugbox drugbox1=pharmacistService.findDrugboxdeviceid(deviceId, coordinateX, coordinateY);
			Drugbox drugbox=new Drugbox();
			 System.out.println("------"+num);
			 drugbox.setCoordinateX(coordinateX);
		     drugbox.setCoordinateY(coordinateY);
		     drugbox.setDeviceId(deviceId);
			drugbox.setDrugnum(drugbox1.getDrugnum()+num);
			/******************上药MQ**********************/
			JSONObject json = new JSONObject();
			int[] ColumnNumber=new int[1];
			int[] RowNumber=new int[1];
			ColumnNumber[0]=Integer.parseInt(drugbox.getCoordinateX());
			RowNumber[0]=Integer.parseInt(drugbox.getCoordinateY());
			json.put("DeciceName", "E09");
			json.put("DrugsNumber", 1); 
			json.put("Action", 1);
			json.put("ColumnNumber", ColumnNumber);
			json.put("RowNumber", RowNumber);
			String MQ_drugbox = json.toString();
			Interface_mq interfacemq = new Interface_mq();               
		/*	String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox);	//连接设备 
			System.out.println(sendMessage);*/
			count=pharmacistService.findrugboxupdateid(drugbox);			
		}
		result.put("flag", count);
		 return result;
	}
	/**
	* 说明:修改并减少出药数量 //管理员模块
	* 
	* @param 
	* @return
	* @author 研发部：姜子 濠
	* @time  2017年4月10日
	*/
	@RequestMapping("Administrator/findrugboxupdateId1.do")
	@ResponseBody
	public Map<String,Object> findrugboxupdateId1(Integer num,Integer deviceId,String coordinateX,String coordinateY){
		Map<String,Object> result = new HashMap<String, Object>();
		int count=0;
	
		if(num!=null && deviceId!=null && coordinateX!=null && coordinateY!=null){
		   Drugbox drugbox1=pharmacistService.findDrugboxdeviceid(deviceId, coordinateX, coordinateY);
		   Drugbox drugbox=new Drugbox();
			 drugbox.setCoordinateX(coordinateX);
		     drugbox.setCoordinateY(coordinateY);
		     drugbox.setDeviceId(deviceId); 
		   if(drugbox1.getDrugnum()-num>0){
			drugbox.setDrugnum(drugbox1.getDrugnum()-num);
		   } else{
				drugbox.setDrugnum(0);
		   }
			/******************上药MQ**********************/
			JSONObject json = new JSONObject();
			int[] ColumnNumber=new int[1];
			int[] RowNumber=new int[1];
			ColumnNumber[0]=Integer.parseInt(drugbox.getCoordinateX());
			RowNumber[0]=Integer.parseInt(drugbox.getCoordinateY());
			json.put("DeciceName", "E09");
			json.put("DrugsNumber", 1);
			json.put("Action", 1);
			json.put("ColumnNumber", ColumnNumber);
			json.put("RowNumber", RowNumber);
			String MQ_drugbox = json.toString();
			Interface_mq interfacemq = new Interface_mq();
		/*	String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox);	//连接设备 
			System.out.println(sendMessage);*/
			count=pharmacistService.findrugboxupdateid(drugbox);			
		}
		result.put("flag", count);
		 return result;
	}
	
	/**
	* 说明:修改并减少出药数量 //药剂师模块
	* 
	* @param 
	* @return
	* @author 研发部：姜子 濠
	* @time  2017年4月10日
	*/
	@RequestMapping("druggist/findrugboxupdateId2.do")
	@ResponseBody
	public Map<String,Object> findrugboxupdateIdd1(Integer num,Integer deviceId,String coordinateX,String coordinateY){
		Map<String,Object> result = new HashMap<String, Object>();
		int count=0;
	
		if(num!=null && deviceId!=null && coordinateX!=null && coordinateY!=null){
		   Drugbox drugbox1=pharmacistService.findDrugboxdeviceid(deviceId, coordinateX, coordinateY);
		   Drugbox drugbox=new Drugbox();
			 drugbox.setCoordinateX(coordinateX);
		     drugbox.setCoordinateY(coordinateY);
		     drugbox.setDeviceId(deviceId); 
		 if(drugbox1.getDrugnum()-num>0){
			drugbox.setDrugnum(drugbox1.getDrugnum()-num);
		 }else{
		    drugbox.setDrugnum(0);
                }
			/******************上药MQ**********************/
			JSONObject json = new JSONObject();
			int[] ColumnNumber=new int[1];
			int[] RowNumber=new int[1];
			ColumnNumber[0]=Integer.parseInt(drugbox.getCoordinateX());
			RowNumber[0]=Integer.parseInt(drugbox.getCoordinateY());
			json.put("DeciceName", "E09");
			json.put("DrugsNumber", 1);
			json.put("Action", 1);
			json.put("ColumnNumber", ColumnNumber);
			json.put("RowNumber", RowNumber);
			String MQ_drugbox = json.toString();
			Interface_mq interfacemq = new Interface_mq();
		/*	String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox);	//连接设备 
			System.out.println(sendMessage);*/
			count=pharmacistService.findrugboxupdateid(drugbox);			
		}
		result.put("flag", count);
		 return result;
	}
	/**
	* 说明： 上药操作，更新药桶库存
	*        
	* @param 
	* @return
	* @author 研发部：纪振儒
	* @time  2017年3月24日
	*/
	@RequestMapping("druggist/upDrugbox")
	@ResponseBody
	public Map<String,Object> appendGeneric(Integer num,Integer id){
		Map<String,Object> result = new HashMap<String, Object>();
		int flge =0;  
		if(num !=null && id !=null){
			/******************上药MQ**********************/
			Drugbox drugbox = pharmacistService.findDrugboxById(id);
			JSONObject json = new JSONObject();
			int[] ColumnNumber=new int[1];
			int[] RowNumber=new int[1];
			ColumnNumber[0]=Integer.parseInt(drugbox.getCoordinateX());
			RowNumber[0]=Integer.parseInt(drugbox.getCoordinateY());
			json.put("DeciceName", "E09");
			json.put("DrugsNumber", 1);
			json.put("Action", 1);
			json.put("ColumnNumber", ColumnNumber);
			json.put("RowNumber", RowNumber);
			String MQ_drugbox = json.toString();
			Interface_mq interfacemq = new Interface_mq();
		/*	String sendMessage = interfacemq.SendMessage("rpc_queue", MQ_drugbox); 	//连接设备 
			System.out.println(sendMessage);*/
			/******************上药MQ**********************/
			flge = pharmacistService.updateDrugboxById(id,num);
		}
		result.put("flag", flge);
		return result;
	}
	/**
	* 说明： 编辑修改药品名称
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月17日
	*/
	@RequestMapping("Administrator/findupdatedrug.do")
	@ResponseBody
	public int findupdatedrug(HttpServletRequest request){
            String id=request.getParameter("id");
            System.out.println("id="+id);
            String drug=request.getParameter("drug");
            System.out.println("drug="+drug);
            Drugbox drugbox=new Drugbox();
            drugbox.setId(Integer.parseInt(id));
            drugbox.setDrug(drug);
            int count=pharmacistService.findupdatedrug(drugbox);
		 if(count>0){
			 System.out.println("编辑成功");
		 }
 
		return count;
	}
	/**
	* 说明： 编辑修改药品名称 //药剂师
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月17日
	*/
	@RequestMapping("druggist/findupdatedrug.do")
	@ResponseBody
	public int findupdatedrug1(HttpServletRequest request){
            String id=request.getParameter("id");
            String drug=request.getParameter("drug");
            Drugbox drugbox=new Drugbox();
            drugbox.setId(Integer.parseInt(id));
            drugbox.setDrug(drug);
            int count=pharmacistService.findupdatedrug(drugbox);
		 if(count>0){
			 System.out.println("编辑成功");
		 }
 
		return count;
	}
	/**
	* 说明：增加药筒
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月18日
	*/
 @ResponseBody
	@RequestMapping("Administrator/Addvvcsdrugbox.do")
 public  ModelAndView  Addvvcsdrugbox(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	 ModelAndView mav=null;
	 String deviceid=request.getParameter("deviceid1");
		String coordinateX=request.getParameter("coordinateX");
		String coordinateY=request.getParameter("coordinateY");
	   String coordinateZ=request.getParameter("coordinateZ");
	   String drug=request.getParameter("drug");
	   System.out.println("deviceid="+deviceid);
	    System.out.println("coordinateX"+coordinateX);
	    System.out.println("coordinateY"+coordinateY);
	    System.out.println("coordinateZ"+coordinateZ);
	    System.out.println("drug"+drug);
	   Drugbox drugbox=new Drugbox();
	    drugbox.setDeviceId(Integer.parseInt(deviceid));
         drugbox.setIdInDevice(0);
         drugbox.setCoordinateX(coordinateX);
         drugbox.setCoordinateY(coordinateY);
         drugbox.setCoordinateZ(coordinateZ);
		drugbox.setDrug(drug);
		drugbox.setDrugnum(0);
		drugbox.setStatus(0);
	   int count=pharmacistService.Addvvcsdrugbox(drugbox);
	   if(count>0){
		   mav=new ModelAndView("box_information");
	   }
	return mav;

  }
	/**
	* 说明：增加药筒
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月18日
	*/
@ResponseBody
	@RequestMapping("druggist/Addvvcsdrugbox.do")
public  ModelAndView  Addvvcsdrugbox1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	 ModelAndView mav=null;
	 String deviceid=request.getParameter("deviceid1");
		String coordinateX=request.getParameter("coordinateX");
		String coordinateY=request.getParameter("coordinateY");
	   String coordinateZ=request.getParameter("coordinateZ");
	   String drug=request.getParameter("drug");
	   System.out.println("deviceid="+deviceid);
	    System.out.println("coordinateX"+coordinateX);
	    System.out.println("coordinateY"+coordinateY);
	    System.out.println("coordinateZ"+coordinateZ);
	    System.out.println("drug"+drug);
	   Drugbox drugbox=new Drugbox();
	    drugbox.setDeviceId(Integer.parseInt(deviceid));
      drugbox.setIdInDevice(0);
      drugbox.setCoordinateX(coordinateX);
      drugbox.setCoordinateY(coordinateY);
      drugbox.setCoordinateZ(coordinateZ);
		drugbox.setDrug(drug);
		drugbox.setDrugnum(0);
		drugbox.setStatus(0);
	   int count=pharmacistService.Addvvcsdrugbox(drugbox);
	   if(count>0){
		   mav=new ModelAndView("box_information");
	   }
	return mav;

}
	/**
	* 说明：删除药筒
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月18日
	*/
	@RequestMapping("Administrator/deletevvcsdrugbox.do")
	@ResponseBody
	public int deletevvcsdrugbox(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		 String id=request.getParameter("id");
		 int count=pharmacistService.delvvcsdrugbox(Integer.parseInt(id));
		return count;
	}
	/**
	* 说明：删除药筒
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠  //药剂师 模块
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月18日
	*/
	@RequestMapping("druggist/deletevvcsdrugbox.do")
	@ResponseBody
	public int deletevvcsdrugbox1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		 String id=request.getParameter("id");
		 int count=pharmacistService.delvvcsdrugbox(Integer.parseInt(id));
		return count;
	}
	
}
