package com.vvcs.pharm.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 说明：	药桶
 * @author 研发部：纪振儒
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_drugbox")
public class Drugbox implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2132683307570003693L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "device_id")
	private Integer deviceId;//设备id
	@Column(name = "id_in_device")
	private Integer idInDevice;//药桶内设备编号
	@Column(name = "coordinate_X")
	private String coordinateX;//药桶坐标
	@Column(name = "coordinate_Y")
	private String coordinateY;
	@Column(name = "coordinate_Z")
	private String coordinateZ;
	@Column(name = "drugnum")
	private Integer drugnum;
	@Column(name = "drug")
	private String drug;//药桶存放药品
	@Column(name = "status")
	private Integer status;//药桶状态
	public Integer getDrugnum() {
		return drugnum;
	}
	public void setDrugnum(Integer drugnum) {
		this.drugnum = drugnum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getIdInDevice() {
		return idInDevice;
	}
	public void setIdInDevice(Integer idInDevice) {
		this.idInDevice = idInDevice;
	}
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	public String getCoordinateZ() {
		return coordinateZ;
	}
	public void setCoordinateZ(String coordinateZ) {
		this.coordinateZ = coordinateZ;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Drugbox [id=" + id + ", deviceId=" + deviceId + ", idInDevice=" + idInDevice + ", coordinateX="
				+ coordinateX + ", coordinateY=" + coordinateY + ", coordinateZ=" + coordinateZ + ", drugnum=" + drugnum
				+ ", drug=" + drug + ", status=" + status + "]";
	}
	
}
