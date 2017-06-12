package com.vvcs.pharm.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 说明：	药房详情
 * @author 研发部：纪振儒
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_pharmacy_detail")
public class PharmacyDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1407051852511284781L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "Medicine_ID")
	private Integer medicineId;//药品ID
	@Column(name = "Box_X")
	private String BOX_X;
	@Column(name = "Box_Y")
	private String BOX_Y;
	@Column(name = "Box_Z")
	private String BOX_Z;
	@Column(name = "stock")
	private String stock;//库存
	@Column(name = "batche")
	private String batche;//生产批次
	@Column(name = "ProductDate")
	private Date productDate;//生产日常
	@Column(name = "stock_in_box")
	private String stickInBox;//药桶内库存
	@Column(name = "Mapping_box_ID")
	private Integer mappingBoxId;//药品映射药桶id
	@Column(name = "Vendor")
	private String vendor;//药品生产商
	@Column(name = "ProductArea")
	private String productArea;//药品产地
	@Column(name = "ProductAreaCode")
	private String productAreaCode;//产地编号
	@Column(name = "speci")
	private String speci;//包装
	@Column(name = "status")
	private Integer status;//状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public String getBOX_X() {
		return BOX_X;
	}
	public void setBOX_X(String bOX_X) {
		BOX_X = bOX_X;
	}
	public String getBOX_Y() {
		return BOX_Y;
	}
	public void setBOX_Y(String bOX_Y) {
		BOX_Y = bOX_Y;
	}
	public String getBOX_Z() {
		return BOX_Z;
	}
	public void setBOX_Z(String bOX_Z) {
		BOX_Z = bOX_Z;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getBatche() {
		return batche;
	}
	public void setBatche(String batche) {
		this.batche = batche;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public String getStickInBox() {
		return stickInBox;
	}
	public void setStickInBox(String stickInBox) {
		this.stickInBox = stickInBox;
	}
	public Integer getMappingBoxId() {
		return mappingBoxId;
	}
	public void setMappingBoxId(Integer mappingBoxId) {
		this.mappingBoxId = mappingBoxId;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getProductArea() {
		return productArea;
	}
	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	public String getProductAreaCode() {
		return productAreaCode;
	}
	public void setProductAreaCode(String productAreaCode) {
		this.productAreaCode = productAreaCode;
	}
	public String getSpeci() {
		return speci;
	}
	public void setSpeci(String speci) {
		this.speci = speci;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", medicineId:" + medicineId + ", BOX_X:" + BOX_X + ", BOX_Y:" + BOX_Y
				+ ", BOX_Z:" + BOX_Z + ", stock:" + stock + ", batche:" + batche + ", productDate:" + productDate
				+ ", stickInBox:" + stickInBox + ", mappingBoxId:" + mappingBoxId + ", vendor=" + vendor
				+ ", productArea:" + productArea + ", productAreaCode:" + productAreaCode + ", speci:" + speci
				+ ", status:" + status + "}";
	}
	
	
}
