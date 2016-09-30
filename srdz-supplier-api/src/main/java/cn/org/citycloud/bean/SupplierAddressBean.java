package cn.org.citycloud.bean;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class SupplierAddressBean {

	
	@Length(max = 50)
	@ApiModelProperty(value = "联系人", required = true)
	private String contactName ;

	@ApiModelProperty(value="联系电话", required=true)
	private String contactPhone;
	
	@Min(0)
	@ApiModelProperty(value = "省ID", required = true)
	private int regionProv;
	
	@Min(0)
	@ApiModelProperty(value = "市ID", required = true)
	private int regionCity;
	
	@Min(0)
	@ApiModelProperty(value = "区ID", required = true)
	private int regionArea;

	@ApiModelProperty(value="供应商地址", required=true)
	private String supplierAddress ;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public int getRegionProv() {
		return regionProv;
	}

	public void setRegionProv(int regionProv) {
		this.regionProv = regionProv;
	}

	public int getRegionCity() {
		return regionCity;
	}

	public void setRegionCity(int regionCity) {
		this.regionCity = regionCity;
	}

	public int getRegionArea() {
		return regionArea;
	}

	public void setRegionArea(int regionArea) {
		this.regionArea = regionArea;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	
	
	
	
	
}
