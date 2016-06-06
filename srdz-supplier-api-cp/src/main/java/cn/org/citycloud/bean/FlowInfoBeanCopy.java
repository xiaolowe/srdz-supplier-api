package cn.org.citycloud.bean;

import java.math.BigDecimal;

import javax.validation.constraints.Min;


import io.swagger.annotations.ApiModelProperty;

public class FlowInfoBeanCopy {

	@ApiModelProperty(value = "物流物品")
	private BigDecimal flowGoods;

	@ApiModelProperty(value="物流价格")
	private BigDecimal flowPrice;
	
	@ApiModelProperty(value = "加多少物品（重量或件数）")
	private BigDecimal addFlowGoods;
	
	@ApiModelProperty(value = "加物品价格（重量或件数）")
	private BigDecimal addGoodsPrice;

	@Min(0)
	@ApiModelProperty(value="是否默认模板（1 默认  0 普通  ）", required=true)
	private int defaultFlag;
	

	public BigDecimal getFlowGoods() {
		return flowGoods;
	}

	public void setFlowGoods(BigDecimal flowGoods) {
		this.flowGoods = flowGoods;
	}

	public BigDecimal getFlowPrice() {
		return flowPrice;
	}

	public void setFlowPrice(BigDecimal flowPrice) {
		this.flowPrice = flowPrice;
	}

	public BigDecimal getAddFlowGoods() {
		return addFlowGoods;
	}

	public void setAddFlowGoods(BigDecimal addFlowGoods) {
		this.addFlowGoods = addFlowGoods;
	}

	public BigDecimal getAddGoodsPrice() {
		return addGoodsPrice;
	}

	public void setAddGoodsPrice(BigDecimal addGoodsPrice) {
		this.addGoodsPrice = addGoodsPrice;
	}

	public int getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	
	
	
}
