package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.GenericMapper;
import com.vvcs.pharm.pojo.Drugbox;
import com.vvcs.pharm.pojo.Generic;

@Service
public class GenericService {
	@Autowired
	private GenericMapper genericMapper;
	
	/**
		* 说明： 根据处方状态获取处方和处方详情
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
//	@Transactional("TX01")
//	public List<Prescription> test01(Integer i){
//		DataSourceContextHandler.setDataSourceContext("DB1");//切换数据库
//		List<Prescription> Prescriptions = prescriptionMapper.findPrescriptionByOutstatus(i);//查询处方和处方详情
//		return Prescriptions;
//	}
	/**
		* 说明： 查询所以处方
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月27日
		*/
	/*@Transactional("TX01")
	public List<Prescription> findPrescription(){
		DataSourceContextHandler.setDataSourceContext("DB1");//切换数据库
		List<Prescription> Prescriptions = prescriptionMapper.findPrescription();//查询处方和处方详情
		return Prescriptions;
	}*/
	
	/**
		* 说明： 根据药品id查询药品
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
	@Transactional("TX02")
	public Generic test02(Integer i){
		DataSourceContextHandler.setDataSourceContext("DB2"); //切换数据源
		Generic findGeneric = genericMapper.findGeneric(i);
		return findGeneric;
	}
	/**
	* 说明： 查询所有药品
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月26日
	*/
	public List<Generic>selectGeneric(){
		DataSourceContextHandler.setDataSourceContext("DB2");
		List<Generic>list=genericMapper.selectGeneric();
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
	public int deleteGeneric(Integer id){
		DataSourceContextHandler.setDataSourceContext("DB2");
		int count=genericMapper.deleteGeneric(id);
		return count;
		
	}
	/**
	* 说明：添加药品 
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public int addGeneric(Generic generic){
		DataSourceContextHandler.setDataSourceContext("DB2");
		int count=genericMapper.addGeneric(generic);
		return count;
	}
	/**
	* 说明：编辑药品
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public int updateGeneric(Generic generic){
	  DataSourceContextHandler.setDataSourceContext("DB2");
	  int count=genericMapper.updateGeneric(generic);
     return count;
	}
	
}
