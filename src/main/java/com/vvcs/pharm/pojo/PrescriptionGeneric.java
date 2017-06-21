package com.vvcs.pharm.pojo;

import java.io.Serializable;

public class PrescriptionGeneric implements Serializable{
	private String pharmacy;
	private String deriveName;
	private String genericName;
	private Integer genericNum;
	private Integer deviceId;
	private String drugboxX;
	private String durgboxY;
	private String drugboxZ;
	private Integer status;
	public String getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}
	public String getDeriveName() {
		return deriveName;
	}
	public void setDeriveName(String deriveName) {
		this.deriveName = deriveName;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public Integer getGenericNum() {
		return genericNum;
	}
	public void setGenericNum(Integer genericNum) {
		this.genericNum = genericNum;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDrugboxX() {
		return drugboxX;
	}
	public void setDrugboxX(String drugboxX) {
		this.drugboxX = drugboxX;
	}
	public String getDurgboxY() {
		return durgboxY;
	}
	public void setDurgboxY(String durgboxY) {
		this.durgboxY = durgboxY;
	}
	public String getDrugboxZ() {
		return drugboxZ;
	}
	public void setDrugboxZ(String drugboxZ) {
		this.drugboxZ = drugboxZ;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PrescriptionGeneric [pharmacy=" + pharmacy + ", deriveName=" + deriveName + ", genericName="
				+ genericName + ", genericNum=" + genericNum + ", deviceId=" + deviceId + ", drugboxX=" + drugboxX
				+ ", durgboxY=" + durgboxY + ", drugboxZ=" + drugboxZ + ", status=" + status + "]";
	}
	
}
