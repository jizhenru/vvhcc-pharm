package com.vvcs.pharm.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.vvcs.pharm.pojo.Generic;

public interface GenericMapper extends Mapper<Generic>{
	/**
		* 说明： 根据药品id查询药品
		* 
		* @param 
		* @return
		* @author 研发部：纪振儒
		* @time  2017年3月24日
		*/
	public Generic findGeneric(Integer i);
	/**
	* 说明： 查询所有药品
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月26日
	*/
	public List<Generic>selectGeneric();
	/**
	* 说明：删除药品 
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public int deleteGeneric(Integer id);
	/**
	* 说明：添加药品
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public int addGeneric(Generic generic); 
	/**
	* 说明：编辑药品
	* 
	* 
	* @param 
	* @return
	* @author 研发部：姜子濠
	* @time  2017年4月27日
	*/
	public int updateGeneric(Generic generic);
}
