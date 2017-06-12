package com.vvcs.pharm.dao;

import java.util.List;

import com.vvcs.pharm.pojo.Device;


public interface DeviceMapper {
	/**
	* 说明： 添加设备列表
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月1日
	*/
    public  int addDervice(Device device);
	/**
	* 说明：根据药房id 查询所有设备
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月5日
	*/
    public List<Device> findDevice(Integer pharmacyid);
	/**
	* 说明： 根据id查询设备药筒id
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月5日
	*/
   public Device finDeviceId(Integer id);
	/**
	* 说明： 根据设备药筒id查询设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月6日
	*/
   public List<Device>  finDevicedeviceid(Integer deviceid);
	/**
	* 说明： 删除设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月6日
	*/
   public int findevicedelete(Integer id);
	/**
	* 说明： 修改设备的基本信息 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月7日
	*/
   public int findeviceupdate(Device device);
	/**
	* 说明： 修改设备状态进行开机关机 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月12日
	*/
  public  int findeviceupdatestatus(Device device);
	/**
	* 说明:编辑设备名和ip地址
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月21日
	*/
  public  int findevicenameipaddr(Device device);
	/**
	* 说明:查询设备id是否重复
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月25日
	*/
  public Device  findedeviceidID(Integer deviceid);
  //查询所有设备
    public List<Device>  selectdevice();
}
