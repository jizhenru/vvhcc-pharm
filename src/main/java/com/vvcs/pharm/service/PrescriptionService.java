package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.PrescriptionMapper;
import com.vvcs.pharm.pojo.Prescription;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionMapper prescriptionMapper;


	// 处方列表查询
	public List<Prescription> test03(String order, Integer offset, Integer limit,Integer OutStatus) {
		System.out.println("prescriptionMappertest03");
		DataSourceContextHandler.setDataSourceContext("DB1");
		List<Prescription> list = prescriptionMapper.findPrescriptionPageByOutstatus(offset,limit,OutStatus);
		return list;
	}

	// 修改处方出药状态
	public int test04(Prescription prescription1) {
		System.out.println("prescriptionMappertest04");
		DataSourceContextHandler.setDataSourceContext("DB1");
		int prescription = prescriptionMapper.updatePrescription(prescription1);
		return prescription;

	}

	/**
	 * 说明： 分页显示处方
	 * 
	 * @param
	 * @return
	 * @author 研发部：纪振儒
	 * @time 2017年3月27日
	 */
	public List<Prescription> findPrescriptionByPage(String order, Integer offset, Integer limit) {
		DataSourceContextHandler.setDataSourceContext("DB1");
		List<Prescription> list = prescriptionMapper.findPrescriptionByPage(offset, limit);
		return list;
	}

	/**
	 * 说明： 查询处方总记录数
	 * 
	 * @param
	 * @return
	 * @author 研发部：纪振儒
	 * @time 2017年3月27日
	 */
	public int findPrescriptionNum() {
		DataSourceContextHandler.setDataSourceContext("DB1");
		Integer findPrescriptionNum = prescriptionMapper.findPrescriptionNum();
		//System.out.println(findPrescriptionNum);
		return findPrescriptionNum;
	}
	/**
		* 说明： 查询未出药处方数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月28日
		*/
	public int findPrescriptionOutStatusNum() {
		DataSourceContextHandler.setDataSourceContext("DB1");
		Integer findPrescriptionNum = prescriptionMapper.findPrescriptionOutStatusNum();
		//System.out.println(findPrescriptionNum);
		return findPrescriptionNum;
	}
	
	//根据时间查询处方出药状态
	  public List<Prescription> test05(String outTime,String  outTime1,Integer OutStatus ,Integer offset,Integer limit){
			DataSourceContextHandler.setDataSourceContext("DB1");  
			List<Prescription> listp=prescriptionMapper.findPrescriptionTimeByOutStatus(outTime, outTime1, OutStatus,offset,limit);
			return listp;
	  }
		//查询用户处方历史
	  public List<Prescription> test06(Integer patientID,Integer OutStatus){

			DataSourceContextHandler.setDataSourceContext("DB1");  
			List<Prescription> listp=prescriptionMapper.findPrescriptionUser(patientID,OutStatus);
			return listp;
	  }
	  //根据id查询处方编号
	    public Prescription findPrescriptionId(int id){	

	    	 DataSourceContextHandler.setDataSourceContext("DB1");
	    	Prescription prescription=prescriptionMapper.findPrescriptionId(id);
		return prescription;
	    	
	    }
    //修改药房出药状态
	  public  int  updatePrescriptionAppoint(Prescription prescription){

		  DataSourceContextHandler.setDataSourceContext("DB1");  
		  int count=prescriptionMapper.updatePrescriptionAppoint(prescription);
		return count;
		  
	  }
	/**
		* 说明： 根據時間獲取未出藥處方數量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月29日
		*/
	public int findPrescriptionNumByTime(String outTime, String outTime1, int i) {
		int flag = prescriptionMapper.findPrescriptionNumByTimeByOutStatus(outTime,outTime1,i);
		return flag;
	}

	/**
		* 说明： 根據時間查詢處方
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月29日
		*/
	public List<Prescription> findPrescriptionByTime(String startPrescriptionCreateDate, String endPrescriptionCreateDate,Integer offset,Integer limit) {
		DataSourceContextHandler.setDataSourceContext("DB1");  
		List<Prescription> listp=prescriptionMapper.findPrescriptionTime(startPrescriptionCreateDate, endPrescriptionCreateDate,offset,limit);
		return listp;
	}

	public int findPrescriptionNumByTime(String startPrescriptionCreateDate, String endPrescriptionCreateDate) {
		int flag = prescriptionMapper.findPrescriptionNumByTime(startPrescriptionCreateDate,endPrescriptionCreateDate);
		return flag;
	}
	public List<Prescription>	selectPrescrptionInfo(){
		  DataSourceContextHandler.setDataSourceContext("DB1");
		  List<Prescription> listp=  prescriptionMapper.selectPrescrptionInfo();
		return listp;
		
	}
	
}
