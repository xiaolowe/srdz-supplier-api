package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the flow_info database table.
 * 
 */
@Entity
@Table(name="flow_info")
@NamedQuery(name="FlowInfo.findAll", query="SELECT f FROM FlowInfo f")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class FlowInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="flow_info_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flowInfoId;

	@Column(name="add_flow_goods")
	private BigDecimal addFlowGoods;

	@Column(name="add_goods_price")
	private BigDecimal addGoodsPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="default_flag")
	private int defaultFlag;

	@Column(name="flow_goods")
	private BigDecimal flowGoods;

	@Column(name="flow_price")
	private BigDecimal flowPrice;

	@Column(name="flow_template_id")
	private int flowTemplateId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="flow_template_id", insertable = false, updatable = false)
	@JsonIgnore
    private FlowTemplate flowTemplate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flowInfo")
	private Set<FlowCity> flowCityList = new HashSet<FlowCity>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flowInfo")
	private Set<FlowProvince> province = new HashSet<FlowProvince>(0);
	

	public FlowInfo() {
	}

	public int getFlowInfoId() {
		return this.flowInfoId;
	}

	public void setFlowInfoId(int flowInfoId) {
		this.flowInfoId = flowInfoId;
	}

	public BigDecimal getAddFlowGoods() {
		return this.addFlowGoods;
	}

	public void setAddFlowGoods(BigDecimal addFlowGoods) {
		this.addFlowGoods = addFlowGoods;
	}

	public BigDecimal getAddGoodsPrice() {
		return this.addGoodsPrice;
	}

	public void setAddGoodsPrice(BigDecimal addGoodsPrice) {
		this.addGoodsPrice = addGoodsPrice;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public BigDecimal getFlowGoods() {
		return this.flowGoods;
	}

	public void setFlowGoods(BigDecimal flowGoods) {
		this.flowGoods = flowGoods;
	}

	public BigDecimal getFlowPrice() {
		return this.flowPrice;
	}

	public void setFlowPrice(BigDecimal flowPrice) {
		this.flowPrice = flowPrice;
	}

	public int getFlowTemplateId() {
		return this.flowTemplateId;
	}

	public void setFlowTemplateId(int flowTemplateId) {
		this.flowTemplateId = flowTemplateId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public FlowTemplate getFlowTemplate() {
		return flowTemplate;
	}

	public void setFlowTemplate(FlowTemplate flowTemplate) {
		this.flowTemplate = flowTemplate;
	}

	public Set<FlowCity> getFlowCityList() {
		return flowCityList;
	}

	public void setFlowCityList(Set<FlowCity> flowCityList) {
		this.flowCityList = flowCityList;
	}

	public Set<FlowProvince> getProvince() {
		return province;
	}

	public void setProvince(Set<FlowProvince> province) {
		this.province = province;
	}

	
	

	
}