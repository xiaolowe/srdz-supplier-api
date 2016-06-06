package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the flow_province database table.
 * 
 */
@Entity
@Table(name="flow_province")
@NamedQuery(name="FlowProvince.findAll", query="SELECT f FROM FlowProvince f")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class FlowProvince implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="flow_province_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flowProvinceId;

	@Column(name="flow_info_id")
	private int flowInfoId;

	@Column(name="flow_province_code")
	private int regionCode;

	@Column(name="flow_province_name")
	private String regionName;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="flow_info_id", insertable = false, updatable = false)
	@JsonIgnore
    private FlowInfo flowInfo;

	public FlowProvince() {
	}

	public int getFlowProvinceId() {
		return this.flowProvinceId;
	}

	public void setFlowProvinceId(int flowProvinceId) {
		this.flowProvinceId = flowProvinceId;
	}

	public int getFlowInfoId() {
		return this.flowInfoId;
	}

	public void setFlowInfoId(int flowInfoId) {
		this.flowInfoId = flowInfoId;
	}

	public int getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public FlowInfo getFlowInfo() {
		return flowInfo;
	}

	public void setFlowInfo(FlowInfo flowInfo) {
		this.flowInfo = flowInfo;
	}

	
}