package cn.org.citycloud.bean;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SmsBean {

	
	@NotBlank(message = "手机号码不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "手机号码", required = true)
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
