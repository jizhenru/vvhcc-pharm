package com.vvcs.pharm.pojo;

public class SkuVo {

	  private Long skuId;
	  private String productName;
	  private Long brandStoreSn;

	  public SkuVo(Long skuId, String productName, Long brandStoreSn) {
	    super();
	    this.skuId = skuId;
	    this.productName = productName;
	    this.brandStoreSn = brandStoreSn;
	  }

	  public Long getSkuId() {
	    return skuId;
	  }
	  public void setSkuId(Long skuId) {
	    this.skuId = skuId;
	  }
	  public String getProductName() {
	    return productName;
	  }
	  public void setProductName(String productName) {
	    this.productName = productName;
	  }
	  public Long getBrandStoreSn() {
	    return brandStoreSn;
	  }
	  public void setBrandStoreSn(Long brandStoreSn) {
	    this.brandStoreSn = brandStoreSn;
	  }

	  @Override
	  public String toString() {
	    return "SkuVo [skuId=" + skuId + ", productName=" + productName + ", brandStoreSn=" + brandStoreSn + "]";
	  }
	}

