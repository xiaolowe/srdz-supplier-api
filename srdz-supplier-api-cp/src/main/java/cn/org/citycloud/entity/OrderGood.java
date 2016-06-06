package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the order_goods database table.
 * 
 */
@Entity
@Table(name="order_goods")
@NamedQuery(name="OrderGood.findAll", query="SELECT o FROM OrderGood o")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class OrderGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_goods_id")
	private int orderGoodsId;

	@Column(name="goods_id")
	private int goodsId;

	@Column(name="goods_image")
	private String goodsImage;

	@Column(name="goods_name")
	private String goodsName;

	@Column(name="goods_num")
	private short goodsNum;

	@Column(name="goods_pay_price")
	private BigDecimal goodsPayPrice;

	@Column(name="goods_price")
	private BigDecimal goodsPrice;
	
	private String standard;

	@Column(name="member_id")
	private int memberId;

	@Column(name="order_id")
	private int orderId;

	@Column(name="promotions_id")
	private int promotionsId;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="order_id", insertable = false, updatable = false)
	@JsonIgnore
    private Order order;

	public OrderGood() {
	}

	public int getOrderGoodsId() {
		return this.orderGoodsId;
	}

	public void setOrderGoodsId(int orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImage() {
		return this.goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public short getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(short goodsNum) {
		this.goodsNum = goodsNum;
	}

	public BigDecimal getGoodsPayPrice() {
		return this.goodsPayPrice;
	}

	public void setGoodsPayPrice(BigDecimal goodsPayPrice) {
		this.goodsPayPrice = goodsPayPrice;
	}

	public BigDecimal getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPromotionsId() {
		return this.promotionsId;
	}

	public void setPromotionsId(int promotionsId) {
		this.promotionsId = promotionsId;
	}


	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}