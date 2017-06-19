package com.vvcs.pharm.pojo;

import java.io.Serializable;

public class PrescriptionGeneric implements Serializable{
	private String pharmacy;
	private String deriveName;
	private String genericName;
	private Integer genericNum;
	private Integer drugboxX;
	private Integer durgboxY;
	private Integer drugboxZ;
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
	public Integer getDrugboxX() {
		return drugboxX;
	}
	public void setDrugboxX(Integer drugboxX) {
		this.drugboxX = drugboxX;
	}
	public Integer getDurgboxY() {
		return durgboxY;
	}
	public void setDurgboxY(Integer durgboxY) {
		this.durgboxY = durgboxY;
	}
	public Integer getDrugboxZ() {
		return drugboxZ;
	}
	public void setDrugboxZ(Integer drugboxZ) {
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
				+ genericName + ", genericNum=" + genericNum + ", drugboxX=" + drugboxX + ", durgboxY=" + durgboxY
				+ ", drugboxZ=" + drugboxZ + ", status=" + status + "]";
	}
}
