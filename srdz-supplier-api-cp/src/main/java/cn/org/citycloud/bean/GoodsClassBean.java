package cn.org.citycloud.bean;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="商品分类Model", description="商品分类接口数据Model")
public class GoodsClassBean {
	
	@Min(0)
	@ApiModelProperty(value = "一级商品分类ID", required = true)
	private int goodsClassId;
	
	@NotBlank(message = "一级商品分类名称不能为空")
	@Length(max = 50)
	@ApiModelProperty(value = "一级商品分类名称", required = true)
	private String goodsClassName;
	
	@Min(0)
	@ApiModelProperty(value = "二级商品分类ID", required = true)
	private int goodsClassTwoId;
	
	@Length(max = 50)
	@ApiModelProperty(value = "二级商品分类名称", required = true)
	private String goodsClassTwoName;
	
	@Min(0)
	@ApiModelProperty(value = "三级商品分类ID", required = true)
	private int goodsClassThreeId;
	
	@Length(max = 50)
	@ApiModelProperty(value = "三级商品分类名称", required = true)
	private String goodsClassThreeName;

	public int getGoodsClassId() {
		return goodsClassId;
	}

	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public String getGoodsClassName() {
		return goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

	public int getGoodsClassTwoId() {
		return goodsClassTwoId;
	}

	public void setGoodsClassTwoId(int goodsClassTwoId) {
		this.goodsClassTwoId = goodsClassTwoId;
	}

	public String getGoodsClassTwoName() {
		return goodsClassTwoName;
	}

	public void setGoodsClassTwoName(String goodsClassTwoName) {
		this.goodsClassTwoName = goodsClassTwoName;
	}

	public int getGoodsClassThreeId() {
		return goodsClassThreeId;
	}

	public void setGoodsClassThreeId(int goodsClassThreeId) {
		this.goodsClassThreeId = goodsClassThreeId;
	}

	public String getGoodsClassThreeName() {
		return goodsClassThreeName;
	}

	public void setGoodsClassThreeName(String goodsClassThreeName) {
		this.goodsClassThreeName = goodsClassThreeName;
	}
	
	
	
	
}
