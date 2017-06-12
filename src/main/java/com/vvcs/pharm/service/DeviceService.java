package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.DeviceMapper;
import com.vvcs.pharm.pojo.Device;

@Service
public class DeviceService {
	/**
	* 说明： 添加设备列表
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月1日
	*/
	@Autowired
    DeviceMapper derviceMapper;
	 public int addDervice(Device device){
		 DataSourceContextHandler.setDataSourceContext("DB3"); 
		 int device1=derviceMapper.addDervice(device);
		return device1;
		 
	 }
		/**
		* 说明： 查询所有设备
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月5日
		*/
	    public List<Device> findDevice(Integer pharmacyid){
	   	 DataSourceContextHandler.setDataSourceContext("DB3"); 
	   	 List<Device>listd=derviceMapper.findDevice(pharmacyid);
			return listd;
	    	
	    }
		/**
		* 说明： 根据id查询设备药筒id
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月5日
		*/
	    
	    public Device finDeviceId(Integer id){
	    	System.out.println("finDeviceId");
	    	DataSourceContextHandler.setDataSourceContext("DB3");
	    	Device device=derviceMapper.finDeviceId(id);
			return device;
	    	
	    }
		/**
		* 说明： 根据设备药筒id查询设备的基本信息 
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月6日
		*/
	    public List<Device> finDevicedeviceid(Integer deviceid){
	    	DataSourceContextHandler.setDataSourceContext("DB3");
	    	List<Device> device=derviceMapper.finDevicedeviceid(deviceid);
			return device;
	    	
	    }
		/**
		* 说明：删除设备的基本信息 
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月6日
		*/
	    public int findevicedelete(Integer id){
	    	DataSourceContextHandler.setDataSourceContext("DB3");
	    	int count=derviceMapper.findevicedelete(id);
			return count;
	    	
	    }
		/**
		* 说明：修改设备的基本信息 
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月7日
		*/
	    public int findeviceupdate(Device device){
	    	System.out.println("findeviceupdate");
	    	DataSourceContextHandler.setDataSourceContext("DB3");
	    	int count=derviceMapper.findeviceupdate(device);
			return count;
	    }
		/**
		* 说明：修改设备的状态进行开机关机 
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月12日
		*/
	    public  int findeviceupdatestatus(Device device){
	    	DataSourceContextHandler.setDataSourceContext("DB3");
	    	int count =derviceMapper.findeviceupdatestatus(device);
			return count;
	    }
		/**
		* 说明：编辑基本信息
		* 
		* @param 
		* @return
		* @author 研发部：姜子濠
		* @time  2017年4月21日
		*/
	    public  int findevicenameipaddr(Device device){
	    	DataSourceContextHandler.setDataSourceContext("DB3");
	    	int count =derviceMapper.findevicenameipaddr(device);
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
	    public Device  findedeviceidID(Integer deviceid){
		  DataSourceContextHandler.setDataSourceContext("DB3");
		  Device device=derviceMapper.findedeviceidID(deviceid);
	     return device;
	    }
	    
	    public List<Device>  selectdevice(){
			  DataSourceContextHandler.setDataSourceContext("DB3");
			return derviceMapper.selectdevice();
			  
	    }
		/**
			* 说明： 根据药房id查询设备
			* 
			* @param 
			* @return
			* @author 研发部：纪振儒
			* @time  2017年6月6日
			*/
		public List<Device> findDeviceByPharmacyId(Integer pharmacyId) {
			DataSourceContextHandler.setDataSourceContext("DB3");
			return derviceMapper.findDevice(pharmacyId);
		}
	    
}
