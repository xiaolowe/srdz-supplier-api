package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the dist_goods database table.
 * 
 */
@Entity
@Table(name="dist_goods")
@NamedQuery(name="DistGoods.findAll", query="SELECT d FROM DistGoods d")
public class DistGoods implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="goods_id")
	private int goodsId;

	@Column(name="already_sale")
	private int alreadySale;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="range_high")
	private BigDecimal rangeHigh;

	@Column(name="range_low")
	private BigDecimal rangeLow;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="goods_id", insertable = false, updatable = false)
	@JsonIgnore
	private Goods goods;

	public DistGoods() {
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getAlreadySale() {
		return this.alreadySale;
	}

	public void setAlreadySale(int alreadySale) {
		this.alreadySale = alreadySale;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getRangeHigh() {
		return this.rangeHigh;
	}

	public void setRangeHigh(BigDecimal rangeHigh) {
		this.rangeHigh = rangeHigh;
	}

	public BigDecimal getRangeLow() {
		return this.rangeLow;
	}

	public void setRangeLow(BigDecimal rangeLow) {
		this.rangeLow = rangeLow;
	}


	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	

}