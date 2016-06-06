package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the goods_class_banner database table.
 * 
 */
@Entity
@Table(name="goods_class_banner")
@NamedQuery(name="GoodsClassBanner.findAll", query="SELECT g FROM GoodsClassBanner g")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class GoodsClassBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="goods_banner_id")
	private int goodsBannerId;

	@Column(name="banner_image")
	private String bannerImage;

	@Column(name="goods_class_id")
	private int goodsClassId;
	
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="goods_class_id", insertable = false, updatable = false)
    private GoodsClass goodsClass;

	public GoodsClassBanner() {
	}

	public int getGoodsBannerId() {
		return this.goodsBannerId;
	}

	public void setGoodsBannerId(int goodsBannerId) {
		this.goodsBannerId = goodsBannerId;
	}

	public String getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public int getGoodsClassId() {
		return this.goodsClassId;
	}

	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public GoodsClass getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(GoodsClass goodsClass) {
		this.goodsClass = goodsClass;
	}
	
	

}