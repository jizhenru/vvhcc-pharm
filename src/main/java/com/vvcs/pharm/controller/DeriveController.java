package com.vvcs.pharm.controller;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vvcs.pharm.pojo.Account;
import com.vvcs.pharm.pojo.Device;
import com.vvcs.pharm.pojo.Drugbox;
import com.vvcs.pharm.pojo.Pharmacy;
import com.vvcs.pharm.service.AccountService;
import com.vvcs.pharm.service.DeviceService;
import com.vvcs.pharm.service.PharmacyService;

import net.sf.json.JSONObject;

@Controller
public class DeriveController {
  @Autowired
  private DeviceService derivceService;
  
  @Autowired
  private AccountService accountService;
	/**
	* 说明： 添加设备列表
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月1日
	*/
  

  @RequestMapping("Administrator/selectpharmacyid.do")
  @ResponseBody
  public ModelAndView selectpharmacyid(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException{
	  ModelAndView mav=null;
	  Cookie[] cookies1=request.getCookies();
		String username = "1";
	      if(cookies1 != null){
	      for(Cookie cookie:cookies1){
	      if(cookie.getName().equals("username"))
	        username = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
	      }}
	  Account account1=accountService.selectAccountusername(username);
      if(account1!=null){
	   request.setAttribute("account", account1);
	   mav=new ModelAndView("addmachine");
   } 
	return mav;
  }
  @ResponseBody
  @RequestMapping("Administrator/insertDervice.do")
  public ModelAndView addDervice(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	  ModelAndView mav=null;
	  int count;
	  String name=request.getParameter("name");
      String type=request.getParameter("type");
       String  iPADDR=request.getParameter("IPADDR");
       String pharmacyid=request.getParameter("pharmacyid");
       String device_id=request.getParameter("device_id");
	  Device device=new Device();
      device.setName(name);
      device.setDeviceid(Integer.parseInt(device_id));
      device.setType(type);
      device.setPharmacyid(Integer.parseInt(pharmacyid));
      device.setIPADDR(iPADDR);
      device.setStatus(0);
	  count =derivceService.addDervice(device);
	   if(count>0){
	    JOptionPane.showMessageDialog(null, "添加成功.", "提示",JOptionPane.PLAIN_MESSAGE);   
	     mav=new ModelAndView("addmachine");
         }
	return mav;  
          }
	/**
	* 说明： 查询所有设备
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月5日
	*/
 @RequestMapping("Administrator/findDevice.do")
  @ResponseBody
  public  List<Device> findDevice1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Cookie[] cookies1=request.getCookies();
		String username = "1";
	      if(cookies1 != null){
	      for(Cookie cookie:cookies1){
	      if(cookie.getName().equals("username"))
	        username = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
	      }}
	      Account account=accountService.selectAccountusername(username);
	 List<Device>device=derivceService.findDevice(account.getPharmacyid());
	  System.out.println(device);
	  for(Device device1:device){
		  System.out.println(device1.getName());
	  }
	  request.setAttribute("device",device );
	  return device;  
  }
	/**
	* 说明： 查询所有设备  //药剂师 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月5日
	*/
@RequestMapping("druggist/findDevice1.do")
@ResponseBody
public List<Device> findDevice2(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException{
	Cookie[] cookies1=request.getCookies();
	String username = "1";
      if(cookies1 != null){
      for(Cookie cookie:cookies1){
      if(cookie.getName().equals("username"))
        username = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
      }}
      Account account=accountService.selectAccountusername(username);
	    List<Device>  device=derivceService.findDevice(account.getPharmacyid()); //获取药房的id
         for(Device device1:device){
    	  System.out.println(device1.getName());
           }
         return device;
}

   /**
	* 说明： 根据id查询设备药筒id
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月5日
	*/
  @RequestMapping("Administrator/findDeviceId.do")
  @ResponseBody
  public ModelAndView findDeviceId1(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException{
	  ModelAndView mav=null;
	  String id=request.getParameter("id");
	  Device 
	  device=derivceService.finDeviceId(Integer.parseInt(id));
      if(device!=null){
	   request.setAttribute("device", device);
	   mav=new ModelAndView("box_information");
   } 
	return mav;
  }
	/**
	* 说明： 根据id查询设备药筒id  //药剂师模块
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月5日
	*/
@RequestMapping("druggist/findDeviceId1.do")
@ResponseBody
public ModelAndView findDeviceIdd1(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException{
	  ModelAndView mav=null;
	  String id=request.getParameter("id");
     Device  device=derivceService.finDeviceId(Integer.parseInt(id));
	      if(device!=null){
	      request.setAttribute("device", device);
		  mav=new ModelAndView("box_information1");
             }

	return mav;
}
	/**
	* 说明： 根据设备药筒id查询设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月6日
	*/
  @RequestMapping("Administrator/finDevicedeviceid.do")
  @ResponseBody
   public List<Device> finDevicedeviceid(HttpServletRequest request){
	  String deviceid=request.getParameter("deviceid");
	  List<Device> devicef=derivceService.finDevicedeviceid(Integer.parseInt(deviceid));
	  for(Device d:devicef){
	   System.out.println(d.getName()+"-----"+d.getIPADDR());
	  }
	   return devicef;
	     
  }
  
	/**
	* 说明： 根据设备药筒id查询设备的基本信息  //药剂师
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月6日
	*/
@RequestMapping("druggist/finDevicedeviceid.do")
@ResponseBody
 public List<Device> finDevicedeviceid1(HttpServletRequest request){
	  String deviceid=request.getParameter("deviceid");
	  List<Device> devicef=derivceService.finDevicedeviceid(Integer.parseInt(deviceid));
	  for(Device d:devicef){
	   System.out.println(d.getName()+"-----"+d.getIPADDR());
	  }
	   return devicef;
	     
}
	/**
	* 说明：删除设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	* @time  2017年4月5日
	*/
  @RequestMapping("Administrator/findevicedelete.do")
  @ResponseBody
  public int findevicedelete(HttpServletRequest request,HttpServletResponse response){
        String id=request.getParameter("id");
        int count=derivceService.findevicedelete(Integer.parseInt(id));
	  return count; 
  }
	/**
	* 说明：删除设备的基本信息  //药剂师
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	* @time  2017年4月5日
	*/
@RequestMapping("druggist/findevicedelete.do")
@ResponseBody
public int findevicedelete1(HttpServletRequest request,HttpServletResponse response){
      String id=request.getParameter("id");
      int count=derivceService.findevicedelete(Integer.parseInt(id));
	  return count; 
}
	/**
	* 说明： 根据id查询设备基本信息
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月7日
	*/
@RequestMapping("Administrator/findDeviceidID.do")
@ResponseBody
public ModelAndView findDeviceId2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	  ModelAndView mav=null;
	  String id=request.getParameter("id");
	  Device device=derivceService.finDeviceId(Integer.parseInt(id));
	   System.out.println(device.getId()+"---"+device.getPharmacyid()+"---"+device.getName());
 if(device!=null){
	 request.setAttribute("device", device);
	   mav=new ModelAndView("updateDevict");
 }
	return mav;
}
/**
* 说明： 根据id查询设备基本信息
* 
* @param 
* @return
* @author 研发部：姜子濠
* @time  2017年4月7日
*/
@RequestMapping("druggist/findDeviceidID.do")
@ResponseBody
public ModelAndView findDeviceId3(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
  ModelAndView mav=null;
  String id=request.getParameter("id");
  Device device=derivceService.finDeviceId(Integer.parseInt(id));
   System.out.println(device.getId()+"---"+device.getPharmacyid()+"---"+device.getName());
if(device!=null){
 request.setAttribute("device", device);
   mav=new ModelAndView("updateDevict");
}
return mav;
}
	/**
	* 说明：修改设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月7日
	*/
  @RequestMapping("Administrator/findeviceupdate.do")
  @ResponseBody
  public int findeviceupdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	  ModelAndView mav=null;
	  String name=request.getParameter("name");
      String type=request.getParameter("type");
       String  iPADDR=request.getParameter("IPADDR");
       String pharmacyid=request.getParameter("pharmacyid");
       String status=request.getParameter("status");
      String device_id=request.getParameter("device_id");
      String id=request.getParameter("id");
	  Device device=new Device();
      device.setName(name);
      device.setDeviceid(Integer.parseInt(device_id));
      device.setType(type);
      device.setPharmacyid(Integer.parseInt(pharmacyid));
      device.setIPADDR(iPADDR);
      device.setStatus(Integer.parseInt(status));
      device.setId(Integer.parseInt(id));
      int count=derivceService.findeviceupdate(device);
	return count;
  }
	/**
	* 说明：修改设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	 * @throws ServletException 
	* @time  2017年4月7日
	*/
@RequestMapping("druggist/findeviceupdate.do")
@ResponseBody
public int findeviceupdate1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	  ModelAndView mav=null;
	  String name=request.getParameter("name");
    String type=request.getParameter("type");
     String  iPADDR=request.getParameter("IPADDR");
      String pharmacyid=request.getParameter("pharmacyid");
      String status=request.getParameter("status");
      String device_id=request.getParameter("device_id");
      String id=request.getParameter("id");
	  Device device=new Device();
    device.setName(name);
    device.setDeviceid(Integer.parseInt(device_id));
    device.setType(type);
    device.setPharmacyid(Integer.parseInt(pharmacyid));
    device.setIPADDR(iPADDR);
    device.setStatus(Integer.parseInt(status));
    device.setId(Integer.parseInt(id));
    int count=derivceService.findeviceupdate(device);
	return count;
}
	/**
	* 说明：修改设备状态进行开关机 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	* @time  2017年4月7日
	*/
  @RequestMapping("Administrator/findeviceupdatestatus3.do")
	@ResponseBody
	public int findDrugboxMapper(Integer id ,Integer stauts,HttpServletRequest request,HttpServletResponse response) throws IOException{
	  Device device=new Device();
		if(stauts==0){
			 device.setStatus(1);
		}else{
			 device.setStatus(0);
		}
		
		 device.setId(id);
	   int count=derivceService.findeviceupdatestatus(device);
	  return count;
	} 
	/**
	* 说明：修改设备状态进行开关机 //药剂师
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	* @time  2017年4月7日
	*/
@RequestMapping("druggist/findeviceupdatestatus3.do")
	@ResponseBody
	public int findDrugboxMapper1(Integer id ,Integer stauts,HttpServletRequest request,HttpServletResponse response) throws IOException{
	  Device device=new Device();
		if(stauts==0){
			 device.setStatus(1);
		}else{
			 device.setStatus(0);
		}
	 device.setId(id);
	 int count=derivceService.findeviceupdatestatus(device);
	  return count;
	} 
	/**
	* 说明：编辑基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	* @time  2017年4月21日
	*/
  @RequestMapping("Administrator/findevicenameip.do")
	@ResponseBody
   public int findevicenameip(HttpServletRequest request,HttpServletResponse response){
	  String id=request.getParameter("id");
	  String ipaddr=request.getParameter("ipaddr");
	  String name=request.getParameter("name");
	  Device device=new Device();
   device.setName(name);
	  device.setIPADDR(ipaddr);
	  device.setId(Integer.parseInt(id));
      int count=derivceService.findevicenameipaddr(device);
	return count;
   
   } 
	/**
	* 说明：编辑基本信息  //药剂师
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	 * @throws IOException 
	* @time  2017年4月21日
	*/
@RequestMapping("druggist/findevicenameip.do")
	@ResponseBody
 public int findevicenameip1(HttpServletRequest request,HttpServletResponse response){
	  String id=request.getParameter("id");
	  String ipaddr=request.getParameter("ipaddr");
	  String name=request.getParameter("name");
	  Device device=new Device();
 device.setName(name);
	  device.setIPADDR(ipaddr);
	  device.setId(Integer.parseInt(id));
	  int count=derivceService.findevicenameipaddr(device);
	return count;
	   
 } 
	/**
	* 说明:查询设备id是否重复
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月25日
	*/
  @RequestMapping("Administrator/isdeviceid.do")
	@ResponseBody
	public int DevicedeviceId(String device_id ,HttpServletRequest request){
	 int a=1;
   if(device_id!=null){
    device_id=request.getParameter("device_id");
	 Device device=derivceService.findedeviceidID(Integer.parseInt(device_id));
	 if(device!=null){
		 a=2;
	 }
	 
	 }
	  return a;
		

  }
  
}
