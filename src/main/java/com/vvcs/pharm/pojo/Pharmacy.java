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
 * 说明：	药房
 * @author 研发部：纪振儒
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_pharmacy")
public class Pharmacy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7862962576068957184L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Integer id;//
	@Column(name = "Name")
	private String name;//药房名
	@Column(name = "Locate")
	private String Locate;//地址
	@Column(name = "Tel")
	private String Tel;//电话
	@Column(name = "Owener")
	private String Owener;//所属公司
	@Column(name = "Bis_time")
	private String Bistime;//营业时间
	@Column(name = "Status")
	private Integer status;//状态
	private String nameshebei;
	public String getNameshebei() {
		return nameshebei;
	}
	public void setNameshebei(String nameshebei) {
		this.nameshebei = nameshebei;
	}
	@Transient
	private Set<Device> devices;//设备
	
	public Set<Device> getDevices() {
		return devices;
	}
	public void setDevices(Set<Device> devices) {
		this.devices = devices;
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
	public String getLocate() {
		return Locate;
	}
	public void setLocate(String locate) {
		Locate = locate;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getOwener() {
		return Owener;
	}
	public void setOwener(String owener) {
		Owener = owener;
	}
	public String getBistime() {
		return Bistime;
	}
	public void setBistime(String bistime) {
		Bistime = bistime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", Locate:" + Locate + ", Tel：" + Tel + ", Owener:" + Owener
				+ ", Bistime:" + Bistime + ", status:" + status+"}";
	}
}
