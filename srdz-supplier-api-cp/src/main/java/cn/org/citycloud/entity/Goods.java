package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the goods database table.
 * 
 */
@Entity
@Table(name="goods")
@NamedQuery(name="Goods.findAll", query="SELECT g FROM Goods g")
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="goods_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int goodsId;

	@Column(name="already_sale")
	private int alreadySale;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="auto_down_time")
	private Date autoDownTime;

	@Column(name="brand_id")
	private Integer brandId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="discount_flg")
	private byte discountFlg;

	@Column(name="flow_template_id")
	private int flowTemplateId;

	@Column(name="goods_add_count")
	private int goodsAddCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="goods_addtime")
	private Date goodsAddtime;

	@Column(name="goods_class_id")
	private int goodsClassId;
	
	@Column(name="goods_class_name")
	private String firstClassName;
	
	@Column(name="goods_class_two_id")
	private int goodsClassTwoId;
	
	@Column(name="goods_class_two_name")
	private String secondClassName;
	
	@Column(name="goods_class_three_id")
	private int goodsClassThreeId;
	
	@Column(name="goods_class_three_name")
	private String thirdClassName;

	@Lob
	@Column(name="goods_desc")
	private String goodsDesc;

	@Column(name="goods_image")
	private String goodsImage;

	@Column(name="goods_name")
	private String goodsName;

	@Column(name="goods_status")
	private int goodsStatus;

	@Column(name="init_price")
	private BigDecimal initPrice;

	@Column(name="init_sale_count")
	private int initSaleCount;

	@Column(name="member_id")
	private int memberId;

	@Column(name="region_area")
	private int regionArea;

	@Column(name="region_city")
	private int regionCity;

	@Column(name="region_prov")
	private int regionProv;

	@Column(name="sale_price")
	private BigDecimal salePrice;
	
	@Column(name="sale_rates")
	private BigDecimal saleRates;
	
	@Column(name="goods_weight")
	private BigDecimal goodsWeight;

	@Column(name="saler_flg")
	private byte salerFlg;

	private String standard;

	@Column(name="supplier_id")
	private int supplierId;

	private int surplus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="region_prov", insertable = false, updatable = false)
    private RegionInfo provinceName;
	 
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="region_city", insertable = false, updatable = false)
    private RegionInfo cityName;
	
	// optional=true：可选，表示此对象可以没有，可以为null；false表示必须存在
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="region_area", insertable = false, updatable = false)
    private RegionInfo areaName;
    
    @OneToOne(mappedBy = "goods")
	private DistGoods distGoods;
    
    @OneToOne(mappedBy = "goods")
    @JsonIgnore
   	private DiscountGoods discountGoods;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<GoodsBanner> goodsBanner = new HashSet<GoodsBanner>(0);
    
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="supplier_id", insertable = false, updatable = false)
    private Supplier supplier;
	

	public Goods() {
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

	public Date getAutoDownTime() {
		return this.autoDownTime;
	}

	public void setAutoDownTime(Date autoDownTime) {
		this.autoDownTime = autoDownTime;
	}

	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public byte getDiscountFlg() {
		return this.discountFlg;
	}

	public void setDiscountFlg(byte discountFlg) {
		this.discountFlg = discountFlg;
	}

	public int getFlowTemplateId() {
		return this.flowTemplateId;
	}

	public void setFlowTemplateId(int flowTemplateId) {
		this.flowTemplateId = flowTemplateId;
	}

	public int getGoodsAddCount() {
		return this.goodsAddCount;
	}

	public void setGoodsAddCount(int goodsAddCount) {
		this.goodsAddCount = goodsAddCount;
	}

	public Date getGoodsAddtime() {
		return this.goodsAddtime;
	}

	public void setGoodsAddtime(Date goodsAddtime) {
		this.goodsAddtime = goodsAddtime;
	}

	public int getGoodsClassId() {
		return this.goodsClassId;
	}

	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}


	public int getGoodsClassThreeId() {
		return this.goodsClassThreeId;
	}

	public void setGoodsClassThreeId(int goodsClassThreeId) {
		this.goodsClassThreeId = goodsClassThreeId;
	}


	public int getGoodsClassTwoId() {
		return this.goodsClassTwoId;
	}

	public void setGoodsClassTwoId(int goodsClassTwoId) {
		this.goodsClassTwoId = goodsClassTwoId;
	}


	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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

	public int getGoodsStatus() {
		return this.goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public BigDecimal getInitPrice() {
		return this.initPrice;
	}

	public void setInitPrice(BigDecimal initPrice) {
		this.initPrice = initPrice;
	}

	public int getInitSaleCount() {
		return this.initSaleCount;
	}

	public void setInitSaleCount(int initSaleCount) {
		this.initSaleCount = initSaleCount;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getRegionArea() {
		return this.regionArea;
	}

	public void setRegionArea(int regionArea) {
		this.regionArea = regionArea;
	}


	public int getRegionCity() {
		return this.regionCity;
	}

	public void setRegionCity(int regionCity) {
		this.regionCity = regionCity;
	}


	public int getRegionProv() {
		return this.regionProv;
	}

	public void setRegionProv(int regionProv) {
		this.regionProv = regionProv;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public byte getSalerFlg() {
		return this.salerFlg;
	}

	public void setSalerFlg(byte salerFlg) {
		this.salerFlg = salerFlg;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getSurplus() {
		return this.surplus;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public RegionInfo getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(RegionInfo provinceName) {
		this.provinceName = provinceName;
	}

	public RegionInfo getCityName() {
		return cityName;
	}

	public void setCityName(RegionInfo cityName) {
		this.cityName = cityName;
	}

	public RegionInfo getAreaName() {
		return areaName;
	}

	public void setAreaName(RegionInfo areaName) {
		this.areaName = areaName;
	}

	public DistGoods getDistGoods() {
		return distGoods;
	}

	public void setDistGoods(DistGoods distGoods) {
		this.distGoods = distGoods;
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

	public BigDecimal getSaleRates() {
		return saleRates;
	}

	public void setSaleRates(BigDecimal saleRates) {
		this.saleRates = saleRates;
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public Set<GoodsBanner> getGoodsBanner() {
		return goodsBanner;
	}

	public void setGoodsBanner(Set<GoodsBanner> goodsBanner) {
		this.goodsBanner = goodsBanner;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public DiscountGoods getDiscountGoods() {
		return discountGoods;
	}

	public void setDiscountGoods(DiscountGoods discountGoods) {
		this.discountGoods = discountGoods;
	}

	
	
	

}