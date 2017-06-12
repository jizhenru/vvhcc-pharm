package com.vvcs.pharm.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 说明：	药品字典
 * @author 研发部：纪振儒     
 * @version 1.0
 * @date Mar 17, 2017
 */
@Table(name="vvcs_Generic")
public class Generic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8649381195146004254L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name="GenericName")
	private String genericName;//名称
	@Column(name="Trade_Name")
	private String tradeName;//商品名
	@Column(name="En_Name")
	private String enName;//英文名
	@Column(name="Chemical_Name")
	private String chemicalName;//化学名
	@Column(name="manufacture")
	private String manufacture;//生产企业
	@Column(name="Approvalnum")
	private String approvalNum;//生产批文
	@Column(name="OTC")
	private Boolean OTC;
	@Column(name="Description")
	private String Description;//性状
	@Column(name="Pharmacological_Actions")
	private String pharmacologicalActions;//药理作用
	@Column(name="Indications")
	private String indications;//适应症
	@Column(name="Contraindications")
	private String contraindications;//禁忌症
	@Column(name="Usage_Administration")
	private String usageAdministration;//用法用量
	@Column(name="Adverse_Reactions")
	private String adverseReactions;//不良反应
	@Column(name="Precautions")
	private String precautions;//注意事项
	@Column(name="Package")
	private String Package;//包装
	@Column(name="Storage")
	private String storage;//储存
	@Column(name="Others")
	private String others;//其他
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getChemicalName() {
		return chemicalName;
	}
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getApprovalNum() {
		return approvalNum;
	}
	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}
	public Boolean getOTC() {
		return OTC;
	}
	public void setOTC(Boolean oTC) {
		OTC = oTC;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPharmacologicalActions() {
		return pharmacologicalActions;
	}
	public void setPharmacologicalActions(String pharmacologicalActions) {
		this.pharmacologicalActions = pharmacologicalActions;
	}
	public String getIndications() {
		return indications;
	}
	public void setIndications(String indications) {
		this.indications = indications;
	}
	public String getContraindications() {
		return contraindications;
	}
	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}
	public String getUsageAdministration() {
		return usageAdministration;
	}
	public void setUsageAdministration(String usageAdministration) {
		this.usageAdministration = usageAdministration;
	}
	public String getAdverseReactions() {
		return adverseReactions;
	}
	public void setAdverseReactions(String adverseReactions) {
		this.adverseReactions = adverseReactions;
	}
	public String getPrecautions() {
		return precautions;
	}
	public void setPrecautions(String precautions) {
		this.precautions = precautions;
	}
	public String getPackage() {
		return Package;
	}
	public void setPackage(String package1) {
		Package = package1;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	@Override
	public String toString() {
		return "Generic [id=" + id + ", genericName=" + genericName + ", tradeName=" + tradeName + ", enName=" + enName
				+ ", chemicalName=" + chemicalName + ", manufacture=" + manufacture + ", approvalNum=" + approvalNum
				+ ", OTC=" + OTC + ", Description=" + Description + ", pharmacologicalActions=" + pharmacologicalActions
				+ ", indications=" + indications + ", contraindications=" + contraindications + ", usageAdministration="
				+ usageAdministration + ", adverseReactions=" + adverseReactions + ", precautions=" + precautions
				+ ", Package=" + Package + ", storage=" + storage + ", others=" + others + "]";
	}
}
