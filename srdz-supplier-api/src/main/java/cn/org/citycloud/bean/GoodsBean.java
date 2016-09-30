package cn.org.citycloud.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="商品Model", description="商品接口数据Model")
public class GoodsBean {
	
	@NotBlank(message = "商品名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "商品名称", required = true)
	private String goodsName;
	
	@ApiModelProperty(value = "品牌ID", required = true)
	private Integer brandId;
	
	@NotNull(message="请选择省")
	@ApiModelProperty(value = "省ID", required = true)
	private Integer regionProv;
	
	@NotNull(message="请选择市")
	@ApiModelProperty(value = "市ID", required = true)
	private Integer regionCity;
	
	@NotNull(message="请选择区")
	@ApiModelProperty(value = "区ID", required = true)
	private Integer regionArea;
	
	@NotNull(message="请输入一级商品分类ID")
	@ApiModelProperty(value = "一级商品分类ID", required = true)
	private Integer goodsClassId;
	
	@NotBlank(message = "一级分类名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "一级分类名称", required = true)
	private String firstClassName;
	
	@NotNull(message="请输入二级商品分类ID")
	@ApiModelProperty(value = "二级商品分类ID", required = true)
	private Integer goodsClassTwoId;
	
	@NotBlank(message = "二级分类名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "二级分类名称", required = true)
	private String secondClassName;
	
	@NotNull(message="请输入三级商品分类ID")
	@ApiModelProperty(value = "三级商品分类ID", required = true)
	private Integer goodsClassThreeId;
	
	@NotBlank(message = "三级分类名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "三级分类名称", required = true)
	private String thirdClassName;

	
	@NotBlank(message = "商品规格不能为空")
	@ApiModelProperty(value = "商品规格", required = true)
	private String standard;
	
	@Min(0)
	@ApiModelProperty(value = "商品上架数", required = true)
	private int goodsAddCount;
	
	@NotNull(message = "起售价不能为空")
	@ApiModelProperty(value = "商品起售价", required = true)
	private BigDecimal initPrice;
	
	@Min(1)
	@ApiModelProperty(value = "商品起售数", required = true)
	private int initSaleCount;
	
	@Min(0)
	@ApiModelProperty(value = "是否加入分销（1 是   0 否）", required = true)
	private byte salerFlg;
	
	@NotNull(message = "最小分销利润不能为空")
	@ApiModelProperty(value = "最小分销利润", required = true)
	private BigDecimal rangeLow;
	
	@NotNull(message = "最大分销利润不能为空")
	@ApiModelProperty(value = "最大分销利润", required = true)
	private BigDecimal rangeHigh;
	
	@NotNull(message = "零售利润比例不能为空")
	@ApiModelProperty(value = "零售利润比例", required = true)
	private BigDecimal saleRates;
	
	@NotNull(message = "零售价格不能为空")
	@ApiModelProperty(value = "零售价格", required = true)
	private BigDecimal salePrice;
	
	@ApiModelProperty(value = "商品重量", required = true)
	private BigDecimal goodsWeight;
	
	@Min(0)
	@ApiModelProperty(value = "物流模板", required = true)
	private Integer flowTemplateId ;
	
	@NotNull(message = "自动下架时间")
	@ApiModelProperty(value = "自动下架时间", required = true)
	private Date  autoDownTime;
	
	@Length(max = 200)
	@ApiModelProperty(value = "商品小图片", required = true)
	private String goodsImage;
	
	@NotBlank(message = "商品介绍不能为空")
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

	public String getFirstClassName() {
		return firstClassName;
	}

	public void setFirstClassName(String firstClassName) {
		this.firstClassName = firstClassName;
	}

	public String getSecondClassName() {
		return secondClassName;
	}

	public void setSecondClassName(String secondClassName) {
		this.secondClassName = secondClassName;
	}

	public String getThirdClassName() {
		return thirdClassName;
	}

	public void setThirdClassName(String thirdClassName) {
		this.thirdClassName = thirdClassName;
	}

	public Integer getRegionProv() {
		return regionProv;
	}

	public void setRegionProv(Integer regionProv) {
		this.regionProv = regionProv;
	}

	public Integer getRegionCity() {
		return regionCity;
	}

	public void setRegionCity(Integer regionCity) {
		this.regionCity = regionCity;
	}

	public Integer getRegionArea() {
		return regionArea;
	}

	public void setRegionArea(Integer regionArea) {
		this.regionArea = regionArea;
	}

	public Integer getGoodsClassId() {
		return goodsClassId;
	}

	public void setGoodsClassId(Integer goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public Integer getGoodsClassTwoId() {
		return goodsClassTwoId;
	}

	public void setGoodsClassTwoId(Integer goodsClassTwoId) {
		this.goodsClassTwoId = goodsClassTwoId;
	}

	public Integer getGoodsClassThreeId() {
		return goodsClassThreeId;
	}

	public void setGoodsClassThreeId(Integer goodsClassThreeId) {
		this.goodsClassThreeId = goodsClassThreeId;
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	
}
