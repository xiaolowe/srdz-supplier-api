package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the supplier_level database table.
 * 
 */
@Entity
@Table(name="supplier_level")
@NamedQuery(name="SupplierLevel.findAll", query="SELECT s FROM SupplierLevel s")
public class SupplierLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="supplier_level_id")
	private int supplierLevelId;

	@Column(name="platform_rates")
	private BigDecimal platformRates;

	@Column(name="supplier_level_name")
	private String supplierLevelName;

	public SupplierLevel() {
	}

	public int getSupplierLevelId() {
		return this.supplierLevelId;
	}

	public void setSupplierLevelId(int supplierLevelId) {
		this.supplierLevelId = supplierLevelId;
	}

	public BigDecimal getPlatformRates() {
		return this.platformRates;
	}

	public void setPlatformRates(BigDecimal platformRates) {
		this.platformRates = platformRates;
	}

	public String getSupplierLevelName() {
		return this.supplierLevelName;
	}

	public void setSupplierLevelName(String supplierLevelName) {
		this.supplierLevelName = supplierLevelName;
	}

}