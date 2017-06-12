package com.vvcs.pharm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvcs.datasource.DataSourceContextHandler;
import com.vvcs.pharm.dao.PharmacyMapper;
import com.vvcs.pharm.pojo.Pharmacy;

@Service
public class PharmacyService {
	@Autowired
	PharmacyMapper pharmacyMapper;
	public List<Pharmacy> findPharmacy(Integer id){
		DataSourceContextHandler.setDataSourceContext("DB3");
		List<Pharmacy>listp=pharmacyMapper.findPharmacy(id);
		return listp;
	}
	//查询所有药房
	public List<Pharmacy> findPharmacy1(){
		DataSourceContextHandler.setDataSourceContext("DB3");
		List<Pharmacy>listp=pharmacyMapper.findPharmacy1();
		return listp;
	}



}
