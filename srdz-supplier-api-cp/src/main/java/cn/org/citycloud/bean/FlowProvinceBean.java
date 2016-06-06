package cn.org.citycloud.bean;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class FlowProvinceBean {
	
	@Length(max = 50)
	@ApiModelProperty(value = "省", required = true)
	private String regionName;
	
	@NotNull(message="省编码不能为空")
	private Integer regionCode;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

	
	
	
	
}
