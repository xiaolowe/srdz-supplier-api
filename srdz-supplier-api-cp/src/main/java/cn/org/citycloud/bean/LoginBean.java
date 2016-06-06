package cn.org.citycloud.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户登录Model", description="用户登录接口数据Model")
public class LoginBean {

	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty(value="手机号码", required=true)
	private String userName;
	
	@NotBlank(message = "密码不能为空")
	@Pattern(regexp = "[A-Za-z0-9]{6,12}", message = "请输入6~12位数字或英文字母")
	@ApiModelProperty(value="登录密码", required=true)
	private String userPwd;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	
	
}
