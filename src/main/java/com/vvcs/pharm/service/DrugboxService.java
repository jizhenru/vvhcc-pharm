package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.DrugboxMapper;
import com.vvcs.pharm.pojo.Drugbox;

@Service
public class DrugboxService {
	
	@Autowired
	private DrugboxMapper drugboxMapper;

	/**
		 * 说明： 根据设备id查询药桶
		 * 
		 * @param 设备id
		 * @return	药桶集合
		 * @author 研发部：纪振儒
		 * @time  Mar 23, 2017
		 */
	public List<Drugbox> queryDrugboxPageByDeviceId(String query,Integer offset, Integer limit,Integer id) {
		DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
		System.out.println("query:"+query);
		if("".equals(query) || null!=query){
			query="%"+query+"%";
		}
		List<Drugbox> drugboxs = drugboxMapper.queryDrugboxPageByDeviceId(offset,limit,id,query);
		return drugboxs;
	}
	public List<Drugbox> findDrugboxById1(Integer device_id) {
		DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
     	List<Drugbox> drugboxs = drugboxMapper.findDrugboxById1(device_id);
		return drugboxs;
	}
	/**
		* 说明： 更新药桶药品数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
	public int updateDrugboxById(Integer id, Integer num) {
		DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
		Drugbox drugbox = findDrugboxById(id);
		drugbox.setId(id);
		drugbox.setDrugnum(num+drugbox.getDrugnum());
		int flag = drugboxMapper.updateDrugboxById(drugbox);
		return flag;
	}
	
	/**
		* 说明： 根据药桶Id查询药桶
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
	public Drugbox findDrugboxById(Integer id){
		DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
		return drugboxMapper.findDrugboxById(id);
	}

	/**
		* 说明： 根据设备id查询药桶数量
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月28日
		*/
	public int queryDrugboxNumByDeviceId(Integer id) {
		DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
		return drugboxMapper.queryDrugboxNumByDeviceId(id);
	}

	/**
		* 说明： 条件分页总数
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月28日
		*/
	public int queryDrugboxNumByDeviceIdAndQuery(Integer id, String query) {
		DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
		// TODO Auto-generated method stub
		if("".equals(query) || null!=query){
			query="%"+query+"%";
		}
		return drugboxMapper.queryDrugboxNumByDeviceIdAndQuery(id,query);
	}
	/**
	* 说明：修改药筒工作状态
	* 
	* @param 
	* @return
	* @author 研发部：姜子 濠
	* @time  2017年4月7日
	*/
   public	int findrugboxupdate (Drugbox drugbox){
	   DataSourceContextHandler.setDataSourceContext("DB3");
	   int count=drugboxMapper.findrugboxupdate(drugbox);
	return count;
	   
   }
   

	/**
	* 说明： 根据id查询所有药品数量 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月10日
	*/
	
	public Drugbox findDrugboxdeviceid(Integer deviceId,String coordinateX,String coordinateY){
		  DataSourceContextHandler.setDataSourceContext("DB3");
		return drugboxMapper.findDrugboxdeviceid(deviceId, coordinateX, coordinateY);
		
	}
	/**
	* 说明:修改出药位置并操作数量
	* 
	* @param 
	* @return
	* @author 研发部：姜子 濠
	* @time  2017年4月7日
	*/
 public   int findrugboxupdateid(Drugbox drugbox){
	  DataSourceContextHandler.setDataSourceContext("DB3");
	 int count=drugboxMapper.findrugboxupdateid(drugbox);
	return count;
	 
 }
	/**
	* 说明： 根据药品名查询库存最多的药桶
	* 
	* @param 
	* @return
	* @author 研发部：纪振儒
	* @time  2017年4月6日
	*/
public List<Drugbox> findDrugboxByDriveAndDrug(int i, String generic) {
	DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
	System.out.println(i+"||"+generic);
	return drugboxMapper.findDrugboxByDriveAndDrug(i,generic);
}
	/**
	* 说明： 出药后更新药品数量
	* 
	* @param 
	* @return
	* @author 研发部：纪振儒
	* @time  2017年4月6日
	*/
public Integer updateDrugboxById1(Drugbox drugbox) {
	DataSourceContextHandler.setDataSourceContext("DB3");//切换数据库
	return drugboxMapper.updateDrugboxById(drugbox);
}
/**
* 说明： 编辑修改药品名称
* 
* @param 
* @return
* @author 研发部：姜子濠
* @time  2017年4月17日
*/
public int findupdatedrug(Drugbox drugbox){
	 DataSourceContextHandler.setDataSourceContext("DB3");
	 int count=drugboxMapper.findupdatedrug(drugbox);
	return count;
}

/**
* 说明：删除药筒
* 
* @param 
* @return
* @author 研发部：姜子濠
* @time  2017年4月18日
*/
public int delvvcsdrugbox(Integer id){
	 DataSourceContextHandler.setDataSourceContext("DB3");
	 int count=drugboxMapper.delvvcsdrugbox(id);
	return count;
}
/**
* 说明：增加药筒
* 
* @param 
* @return
* @author 研发部：姜子濠
* @time  2017年4月18日
*/
 public int  Addvvcsdrugbox(Drugbox drugbox){
	 DataSourceContextHandler.setDataSourceContext("DB3");
	 int count=drugboxMapper.Addvvcsdrugbox(drugbox);
	return count;
	
}
/**
	* 说明： 根据设备id查找设备内所有已启用药筒
	* 
	* @param 
	* @return
	* @author 研发部：纪振儒
	* @time  2017年6月7日
	*/
//public List<Drugbox> findDrugboxByDriveId(Integer id) {
//	return drugboxMapper.findDrugboxByDriveId(id);
//}

}
