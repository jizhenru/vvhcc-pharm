package com.vvcs.pharm.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 说明：	设备
 * @author 研发部：纪振儒
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_device")
public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7553464886377209066L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;//设备名
	@Column(name="deviceid")
	private Integer deviceid; //药桶id
	@Column(name = "type")
	private String type;//设备型号
	@Column(name ="pharmacyid")
	private Integer pharmacyid;//药房id
	@Column(name = "IPADDR")
	private String IPADDR;//设备IP
	@Column(name = "status")
	private Integer status;//设备状态
	@Transient
	private Set<Drugbox> drugboxs;//药桶

	public Set<Drugbox> getDrugboxs() {
		return drugboxs;
	}
	public void setDrugboxs(Set<Drugbox> drugboxs) {
		this.drugboxs = drugboxs;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Integer getPharmacyid() {
		return pharmacyid;
	}
	public void setPharmacyid(Integer pharmacyid) {
		this.pharmacyid = pharmacyid;
	}
	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}
	public String getIPADDR() {
		return IPADDR;
	}
	public void setIPADDR(String iPADDR) {
		IPADDR = iPADDR;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", deviceid=" + deviceid + ", type=" + type + ", pharmacyid="
				+ pharmacyid + ", IPADDR=" + IPADDR + ", status=" + status + "]";
	}
	
}
