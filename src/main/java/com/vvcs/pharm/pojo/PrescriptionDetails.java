package com.vvcs.pharm.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 说明：	处方详情
 * @author 研发部：纪振儒
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_PrescriptionDetails")
public class PrescriptionDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8539968522714703752L;

	@Id
	@Column(name = "Generic_id")
	private Integer GenericID;//药品ID
	@Id
	@Column(name = "PrescriptionInfo_id")
	private Integer PrescriptionInfoID;//处方ID
	@Column(name = "MedicineNum")
	private Integer MedicineNum;//药品数量
	public Integer getGenericID() {
		return GenericID;
	}
	public void setGenericID(Integer genericID) {
		GenericID = genericID;
	}
	public Integer getPrescriptionInfoID() {
		return PrescriptionInfoID;
	}
	public void setPrescriptionInfoID(Integer prescriptionInfoID) {
		PrescriptionInfoID = prescriptionInfoID;
	}
	public Integer getMedicineNum() {
		return MedicineNum;
	}
	public void setMedicineNum(Integer medicineNum) {
		MedicineNum = medicineNum;
	}
	@Override
	public String toString() {
		return "PrescriptionDetails [GenericID=" + GenericID + ", PrescriptionInfoID=" + PrescriptionInfoID
				+ ", MedicineNum=" + MedicineNum + "]";
	}
}
