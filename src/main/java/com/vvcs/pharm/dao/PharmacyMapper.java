package com.vvcs.pharm.dao;

import java.util.List;

import com.vvcs.pharm.pojo.Pharmacy;

public interface PharmacyMapper {
	//根据处方预约状态查询药房名称
  public List<Pharmacy>findPharmacy(Integer id);
   //查询所有药房
  public List<Pharmacy>findPharmacy1();
  


  
}
