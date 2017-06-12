package com.vvcs.pharm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.vvcs.pharm.pojo.Drugbox;

public interface DrugboxMapper extends Mapper<Drugbox>{

	/**
		 * 说明： 根据设备id查询药桶
		 * 
		 * @param 
		 * @return
		 * @author 研发部：纪振儒
		 * @time  Mar 23, 2017
		 */
	List<Drugbox> queryDrugboxPageByDeviceId(Integer offset, Integer limit,Integer id,String query);

	List<Drugbox>findDrugboxById1(Integer deviceid);
	/**
		* 说明： 根据药筒id更新药品数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
	int updateDrugboxById(Drugbox drugbox);

	/**
		* 说明： 根据药桶id查询药桶
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
	Drugbox findDrugboxById(Integer id);

	int insertDrugbox(Drugbox drugbox);

	/**
		* 说明： 根据设备id查询药桶数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月28日
		*/
	int queryDrugboxNumByDeviceId(Integer id);

	
	/**
		* 说明： 条件分页总数
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月28日
		*/
	/**
	* 说明： 根据药品名查询药桶
	* 
	* @param 
	* @return
	* @author 研发部：纪振儒
	* @time  2017年4月6日
	*/
	List<Drugbox> findDrugboxByDriveAndDrug(@Param("i")int i, @Param("generic")String generic);

	int queryDrugboxNumByDeviceIdAndQuery(Integer id, String query);
	/**
	* 说明： 修改药筒工作状态
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月7日
	*/
	int findrugboxupdate (Drugbox drugbox);
	/**
	* 说明： 根据id查询所有药品数量 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月10日
	*/
	
	Drugbox findDrugboxdeviceid(@Param("deviceId")Integer deviceId,@Param("coordinateX")String coordinateX,@Param("coordinateY")String coordinateY);
	/**
	* 说明： 修改出药数量并根据位置和设备来修改
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月7日
	*/
	int findrugboxupdateid(Drugbox drugbox);
	/**
	* 说明： 编辑修改药品名称
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月17日
	*/
   int findupdatedrug(Drugbox drugbox);
	/**
	* 说明：删除药筒
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月18日
	*/
   int delvvcsdrugbox(Integer id);
	/**
	* 说明：增加药筒
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月18日
	*/
  int  Addvvcsdrugbox(Drugbox drugbox);

	/**
		* 说明： 根据设备id查找已启用药筒
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年6月7日
		*/
//	List<Drugbox> findDrugboxByDriveId(Integer id);

}
