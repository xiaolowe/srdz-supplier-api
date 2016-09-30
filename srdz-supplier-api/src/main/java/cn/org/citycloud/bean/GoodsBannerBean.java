package cn.org.citycloud.bean;

import org.hibernate.validator.constraints.Length;
import io.swagger.annotations.ApiModelProperty;

public class GoodsBannerBean {


	@Length(max = 200)
	@ApiModelProperty(value = "商品大图片")
	private String bannerImage;

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	

	
}
