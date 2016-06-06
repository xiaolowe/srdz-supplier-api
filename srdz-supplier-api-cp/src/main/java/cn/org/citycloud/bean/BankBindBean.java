package cn.org.citycloud.bean;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class BankBindBean {

	
	@NotBlank(message = "手机号码不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "手机号码", required = true)
	private String userName;
	
	@NotBlank(message = "手机验证码不能为空")
	@Length(max = 6, min = 6)
	@ApiModelProperty(value="手机验证码", required=true)
	private String phoneCode;
	
	@NotBlank(message = "企业名称不能为空")
	@ApiModelProperty(value="企业名称", required=true)
	private String companyName;
	
//	@NotBlank(message = "法人")
	@ApiModelProperty(value="法人", required=true)
	private String supplierOwner;

	@NotBlank(message = "银行卡")
	@ApiModelProperty(value="银行卡", required=true)
	private String cardNo;
	
	@NotBlank(message = "持卡人")
	@ApiModelProperty(value="持卡人", required=true)
	private String cardOwner;
	
	@NotBlank(message = "银行")
	@ApiModelProperty(value="银行", required=true)
	private String bankName;
	
	@NotBlank(message = "开户行")
	@ApiModelProperty(value="开户行", required=true)
	private String accountBank;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSupplierOwner() {
		return supplierOwner;
	}

	public void setSupplierOwner(String supplierOwner) {
		this.supplierOwner = supplierOwner;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	
	
	
}
