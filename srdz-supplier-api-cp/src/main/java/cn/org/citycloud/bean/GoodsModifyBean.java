package cn.org.citycloud.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="修改商品Model", description="修改商品接口数据Model")
public class GoodsModifyBean {
	
	@Length(max = 50)
	@ApiModelProperty(value = "商品名称", required = true)
	private String goodsName;
	
	@ApiModelProperty(value = "品牌ID", required = true)
	private Integer brandId;
	
	@Min(0)
	@ApiModelProperty(value = "省ID", required = true)
	private int regionProv;
	
	@Min(0)
	@ApiModelProperty(value = "市ID")
	private int regionCity;
	
	@Min(0)
	@ApiModelProperty(value = "区ID")
	private int regionArea;
	
	@ApiModelProperty(value = "商品规格", required = true)
	private String standard;
	
	@Min(0)
	@ApiModelProperty(value = "商品上架数", required = true)
	private int goodsAddCount;
	
	@ApiModelProperty(value = "商品起售价", required = true)
	private BigDecimal initPrice;
	
	@Min(1)
	@ApiModelProperty(value = "商品起售数", required = true)
	private int initSaleCount;
	
	@Min(0)
	@ApiModelProperty(value = "是否加入分销（1 是   0 否）", required = true)
	private byte salerFlg;
	
	@ApiModelProperty(value = "最小分销利润", required = true)
	private BigDecimal rangeLow;
	
	@ApiModelProperty(value = "最大分销利润", required = true)
	private BigDecimal rangeHigh;
	
	@ApiModelProperty(value = "零售利润比例", required = true)
	private BigDecimal saleRates;
	
	@ApiModelProperty(value = "零售价格", required = true)
	private BigDecimal salePrice;
	
	@ApiModelProperty(value = "商品重量", required = true)
	private BigDecimal goodsWeight;
	
	@Min(0)
	@ApiModelProperty(value = "物流模板", required = true)
	private Integer flowTemplateId ;
	
	@ApiModelProperty(value = "零售价格", required = true)
	private Date  autoDownTime;
	
	@Length(max = 200)
	@ApiModelProperty(value = "商品小图片", required = true)
	private String goodsImage;
	
	@ApiModelProperty(value = "商品介绍", required = true)
	private String goodsDesc;
	
	private List<GoodsBannerBean>  goodsBannerList = new ArrayList<GoodsBannerBean>();

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getGoodsAddCount() {
		return goodsAddCount;
	}

	public void setGoodsAddCount(int goodsAddCount) {
		this.goodsAddCount = goodsAddCount;
	}

	public BigDecimal getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(BigDecimal initPrice) {
		this.initPrice = initPrice;
	}

	public int getInitSaleCount() {
		return initSaleCount;
	}

	public void setInitSaleCount(int initSaleCount) {
		this.initSaleCount = initSaleCount;
	}

	public byte getSalerFlg() {
		return salerFlg;
	}

	public void setSalerFlg(byte salerFlg) {
		this.salerFlg = salerFlg;
	}

	public BigDecimal getRangeLow() {
		return rangeLow;
	}

	public void setRangeLow(BigDecimal rangeLow) {
		this.rangeLow = rangeLow;
	}

	public BigDecimal getRangeHigh() {
		return rangeHigh;
	}

	public void setRangeHigh(BigDecimal rangeHigh) {
		this.rangeHigh = rangeHigh;
	}

	public BigDecimal getSaleRates() {
		return saleRates;
	}

	public void setSaleRates(BigDecimal saleRates) {
		this.saleRates = saleRates;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getFlowTemplateId() {
		return flowTemplateId;
	}

	public void setFlowTemplateId(Integer flowTemplateId) {
		this.flowTemplateId = flowTemplateId;
	}

	public Date getAutoDownTime() {
		return autoDownTime;
	}

	public void setAutoDownTime(Date autoDownTime) {
		this.autoDownTime = autoDownTime;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public List<GoodsBannerBean> getGoodsBannerList() {
		return goodsBannerList;
	}

	public void setGoodsBannerList(List<GoodsBannerBean> goodsBannerList) {
		this.goodsBannerList = goodsBannerList;
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	
	
	
	
	
}
