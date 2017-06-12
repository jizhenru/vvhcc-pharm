package com.vvcs.pharm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 说明：	处方
 * @author 研发部：纪振儒
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_PrescriptionInfo")
public class Prescription implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 266964868993195491L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "HospitalID")
	private Integer hospitalId;//医院ID
	@Column(name = "PrescriptionID")
	private Integer prescriptionId;//处方ID
	@Column(name = "PatientID")
	private Integer patientId;//病人ID
	@Column(name = "doctorID")
	private Integer doctorId;//医生ID
	@Column(name="PharmacistID")
	private Integer pharmacistID;//药师ID
	@Column(name = "PrescriptionCreateDate")
	private Date prescriptionCreateDate;//处方创建时间
	@Column(name = "OutTime")
	private Date outTime;//出药时间
	@Column(name = "OutStatus")
	private Integer outStatus;//出药状态    0 1 
	@Column(name = "PrescriptionStatus")
	private Integer prescriptionStatus;//处方状态
	@Column(name = "AppointID")
	private Integer appointID;//预约药房ID 
	@Column(name = "Advice")
	private String advice;//医嘱
	@Transient
	private String desc;
	@Transient
	private String nameyf; //药房名
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Transient
	private String username;//患者姓名

	public String getNameyf() {
		return nameyf;
	}
	public void setNameyf(String nameyf) {
		this.nameyf = nameyf;
	}
	@Transient
	private Set<PrescriptionDetails> prescriptionDetails;

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Set<PrescriptionDetails> getPrescriptionDetails() {
		return prescriptionDetails;
	}
	public void setPrescriptionDetails(Set<PrescriptionDetails> prescriptionDetails) {
		this.prescriptionDetails = prescriptionDetails;
	}
  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Integer getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getPharmacistID() {
		return pharmacistID;
	}
	public void setPharmacistID(Integer pharmacistID) {
		this.pharmacistID = pharmacistID;
	}
	public Date getPrescriptionCreateDate() {
		return prescriptionCreateDate;
	}
	public void setPrescriptionCreateDate(Date prescriptionCreateDate) {
		this.prescriptionCreateDate = prescriptionCreateDate;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Integer getOutStatus() {
		return outStatus;
	}
	public void setOutStatus(Integer outStatus) {
		this.outStatus = outStatus;
	}
	public Integer getPrescriptionStatus() {
		return prescriptionStatus;
	}
	public void setPrescriptionStatus(Integer prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}
	public Integer getAppointID() {
		return appointID;
	}
	public void setAppointID(Integer appointID) {
		this.appointID = appointID;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	@Override
	public String toString() {
		return "Prescription [id=" + id + ", hospitalId=" + hospitalId + ", prescriptionId=" + prescriptionId
				+ ", patientId=" + patientId + ", doctorId=" + doctorId + ", pharmacistID=" + pharmacistID
				+ ", prescriptionCreateDate=" + prescriptionCreateDate + ", outTime=" + outTime + ", outStatus="
				+ outStatus + ", prescriptionStatus=" + prescriptionStatus + ", appointID=" + appointID + ", advice="
				+ advice + ", prescriptionDetails=" + prescriptionDetails + "]";
	}
}
