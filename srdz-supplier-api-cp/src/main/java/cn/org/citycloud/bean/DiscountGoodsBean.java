package cn.org.citycloud.bean;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="特惠商品Model", description="特惠商品接口数据Model")
public class DiscountGoodsBean {
	
	@Min(0)
	@ApiModelProperty(value = "商品上架数", required = true)
	private int goodsAddCount;
	
	@Min(1)
	@ApiModelProperty(value = "商品起售数", required = true)
	private int initSaleCount;
	
	@NotNull(message = "特惠价格不能为空")
	@ApiModelProperty(value = "特惠价格", required = true)
	private BigDecimal benefitPrice;
	
	@ApiModelProperty(value = "自动下架时间")
	private Date autoDownTime;



	public int getGoodsAddCount() {
		return goodsAddCount;
	}


	public void setGoodsAddCount(int goodsAddCount) {
		this.goodsAddCount = goodsAddCount;
	}


	public int getInitSaleCount() {
		return initSaleCount;
	}


	public void setInitSaleCount(int initSaleCount) {
		this.initSaleCount = initSaleCount;
	}


	public BigDecimal getBenefitPrice() {
		return benefitPrice;
	}


	public void setBenefitPrice(BigDecimal benefitPrice) {
		this.benefitPrice = benefitPrice;
	}


	public Date getAutoDownTime() {
		return autoDownTime;
	}


	public void setAutoDownTime(Date autoDownTime) {
		this.autoDownTime = autoDownTime;
	}

	

	
	
	
}
