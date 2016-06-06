package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the goods_banner database table.
 * 
 */
@Entity
@Table(name="goods_banner")
@NamedQuery(name="GoodsBanner.findAll", query="SELECT g FROM GoodsBanner g")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class GoodsBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="goods_banner_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int goodsBannerId;

	@Column(name="banner_image")
	private String bannerImage;

	@Column(name="goods_id")
	private int goodsId;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="goods_id", insertable = false, updatable = false)
	@JsonIgnore
    private Goods goods;

	public GoodsBanner() {
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

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	

}