package cn.org.citycloud.bean;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModelProperty;

public class CashBean {

	@NotNull(message = "")
	@ApiModelProperty(value = "申请提现金额", required = true)
	private BigDecimal applyMoney;

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}
	
	
	


	
	
	
}
