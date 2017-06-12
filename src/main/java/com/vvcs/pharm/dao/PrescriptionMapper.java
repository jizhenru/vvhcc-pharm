package com.vvcs.pharm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.vvcs.pharm.pojo.Prescription;

public interface PrescriptionMapper extends Mapper<Prescription>{
	/**
		* 说明： 根据出药状态查询除非列表
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月27日
		*/
	public List<Prescription> findPrescriptionPageByOutstatus(Integer offset, Integer limit,Integer OutStatus);

	/**
		* 说明： 处方总数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月27日
		*/
	public Integer findPrescriptionNum();
	/**
		* 说明： 未出药处方数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月28日
		*/
	public Integer findPrescriptionOutStatusNum();
	
	public Prescription findGenericByPrescription(Integer id);
	//查询处方列表
	public List<Prescription> findPrescription(Integer OutStatus);
	//修改处方出药状态
	 public  int updatePrescription(Prescription prescription1);
	 //根据id查询处方编号
	    public Prescription findPrescriptionId(int id);
	 //修改预约出药状态
	 public  int  updatePrescriptionAppoint(Prescription prescription);
      //查询用户的处方历史
	public List<Prescription>findPrescriptionUser(@Param("patientID")Integer patientID,@Param("outStatus")Integer outStatus);

	/**
		* 说明： 分页显示处方
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月27日
		*/
	public List<Prescription> findPrescriptionByPage(Integer offset, Integer limit);
	
	 //根据时间来查询未出药的处方
	 public List<Prescription>findPrescriptionTimeByOutStatus(@Param("outTime")String outTime,@Param("outTime1")String outTime1,@Param("outStatus")Integer OutStatus,@Param("offset")Integer offset,@Param("limit")Integer limit);

	/**
		* 说明： 根據時間查詢未出藥處方數量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月29日
		*/
	public int findPrescriptionNumByTimeByOutStatus(String outTime, String outTime1, int i);

	/**
		* 说明： 根據時間查詢處方
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月29日
		*/
	public List<Prescription> findPrescriptionTime(String startPrescriptionCreateDate, String endPrescriptionCreateDate,Integer offset,Integer limit);

	/**
		* 说明： 根據時間查詢處方數量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月29日
		*/
	public int findPrescriptionNumByTime(String startPrescriptionCreateDate, String endPrescriptionCreateDate);
	/**
	* 说明： 查询所有的处方
	* 
	* @param 
	* @return
	* @author 研发部：纪振儒
	* @time  2017年3月29日
	*/
	public List<Prescription>	selectPrescrptionInfo();
	
}
