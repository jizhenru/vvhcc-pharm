package com.vvcs.filter;

import java.util.List;

import com.vvcs.pharm.pojo.PrescriptionGeneric;

import net.sf.json.JSONArray;

public class Test {
	public static void main(String[] args) {
		String jsons = "[{\"drugboxX\":1,\"durgboxY\":2,\"drugboxZ\":3,\"status\":0,\"deviceId\":1,\"GenericNum\":3},{\"drugboxX\":1,\"durgboxY\":2,\"drugboxZ\":3,\"status\":0,\"deviceId\":1,\"GenericNum\":3}]";
		System.out.println(jsons);
  	    JSONArray jsonArray = JSONArray.fromObject(jsons);
	    
	    List<PrescriptionGeneric> list = (List<PrescriptionGeneric>) JSONArray.toCollection(jsonArray, PrescriptionGeneric.class);
	    System.out.println(list);
	}
}
