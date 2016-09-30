package cn.org.citycloud.bean;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class FlowCityBean {
	
	@NotBlank(message = "城市名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "城市", required = true)
	private String flowCityName;
	
	@NotNull(message="城市编码不能为空")
	private Integer flowCityCode;

	public String getFlowCityName() {
		return flowCityName;
	}

	public void setFlowCityName(String flowCityName) {
		this.flowCityName = flowCityName;
	}

	public Integer getFlowCityCode() {
		return flowCityCode;
	}

	public void setFlowCityCode(Integer flowCityCode) {
		this.flowCityCode = flowCityCode;
	}
	
	
	
	
}
