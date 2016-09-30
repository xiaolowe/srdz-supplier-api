package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the after_sale database table.
 * 
 */
@Entity
@Table(name="after_sale")
@NamedQuery(name="AfterSale.findAll", query="SELECT a FROM AfterSale a")
public class AfterSale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AFTER_SALE_AFTERSALEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AFTER_SALE_AFTERSALEID_GENERATOR")
	@Column(name="after_sale_id")
	private int afterSaleId;

	@Column(name="confirm_cause")
	private String confirmCause;

	@Column(name="confirm_id")
	private int confirmId;

	@Column(name="confirm_name")
	private String confirmName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="confirm_time")
	private Date confirmTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="member_id")
	private int memberId;

	@Column(name="member_name")
	private String memberName;

	@Column(name="order_id")
	private int orderId;

	private String result;

	@Column(name="rollback_cause")
	private String rollbackCause;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rollback_time")
	private Date rollbackTime;

	private int status;

	@Column(name="supplier_id")
	private int supplierId;

	@Column(name="supplier_name")
	private String supplierName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	public AfterSale() {
	}

	public int getAfterSaleId() {
		return this.afterSaleId;
	}

	public void setAfterSaleId(int afterSaleId) {
		this.afterSaleId = afterSaleId;
	}

	public String getConfirmCause() {
		return this.confirmCause;
	}

	public void setConfirmCause(String confirmCause) {
		this.confirmCause = confirmCause;
	}

	public int getConfirmId() {
		return this.confirmId;
	}

	public void setConfirmId(int confirmId) {
		this.confirmId = confirmId;
	}

	public String getConfirmName() {
		return this.confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public Date getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRollbackCause() {
		return this.rollbackCause;
	}

	public void setRollbackCause(String rollbackCause) {
		this.rollbackCause = rollbackCause;
	}

	public Date getRollbackTime() {
		return this.rollbackTime;
	}

	public void setRollbackTime(Date rollbackTime) {
		this.rollbackTime = rollbackTime;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}