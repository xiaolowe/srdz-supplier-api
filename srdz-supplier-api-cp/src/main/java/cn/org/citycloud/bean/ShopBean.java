package cn.org.citycloud.bean;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="门店装修Model", description="门店装修接口数据Model")
public class ShopBean {

	@NotEmpty(message = "店铺名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "店铺名称", required = true)
	private String supplierShopName;
	@NotEmpty(message = "LOGO不能为空")
	@Length(max = 200)
	@ApiModelProperty(value = "LOGO", required = true)
	private String logoIamge;
	@NotEmpty(message = "Banner图不能为空")
	@Length(max = 200)
	@ApiModelProperty(value = "Banner图", required = true)
	private String bannerImage;
	
	
	public String getSupplierShopName() {
		return supplierShopName;
	}
	public void setSupplierShopName(String supplierShopName) {
		this.supplierShopName = supplierShopName;
	}
	public String getLogoIamge() {
		return logoIamge;
	}
	public void setLogoIamge(String logoIamge) {
		this.logoIamge = logoIamge;
	}
	public String getBannerImage() {
		return bannerImage;
	}
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	
	
	
	
}
