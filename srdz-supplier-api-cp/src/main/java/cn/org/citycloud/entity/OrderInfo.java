package cn.org.citycloud.entity;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the order_info database table.
 * 
 */
@Entity
@Table(name="order_info")
@NamedQuery(name="OrderInfo.findAll", query="SELECT o FROM OrderInfo o")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id")
	private int orderId;

	@Column(name="platform_amount")
	private BigDecimal platformAmount;

	@Column(name="platform_rates")
	private BigDecimal platformRates;

	@Column(name="service_center_amount")
	private BigDecimal serviceCenterAmount;

	@Column(name="service_center_rates")
	private BigDecimal serviceCenterRates;

	@Column(name="supplier_id")
	private int supplierId;

	@Column(name="supplier_name")
	private String supplierName;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", insertable = false, updatable = false)
	@JsonIgnore
	private Order order;

	public OrderInfo() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPlatformAmount() {
		return this.platformAmount;
	}

	public void setPlatformAmount(BigDecimal platformAmount) {
		this.platformAmount = platformAmount;
	}

	public BigDecimal getPlatformRates() {
		return this.platformRates;
	}

	public void setPlatformRates(BigDecimal platformRates) {
		this.platformRates = platformRates;
	}

	public BigDecimal getServiceCenterAmount() {
		return this.serviceCenterAmount;
	}

	public void setServiceCenterAmount(BigDecimal serviceCenterAmount) {
		this.serviceCenterAmount = serviceCenterAmount;
	}

	public BigDecimal getServiceCenterRates() {
		return this.serviceCenterRates;
	}

	public void setServiceCenterRates(BigDecimal serviceCenterRates) {
		this.serviceCenterRates = serviceCenterRates;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}