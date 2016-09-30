package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sales_member database table.
 * 
 */
@Entity
@Table(name="sales_member")
@NamedQuery(name="SalesMember.findAll", query="SELECT s FROM SalesMember s")
public class SalesMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="member_id")
	private int memberId;

	@Column(name="account_bank")
	private String accountBank;

	@Column(name="account_owner")
	private String accountOwner;

	@Column(name="card_no")
	private String cardNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="identity_image")
	private String identityImage;

	@Column(name="identity_no")
	private String identityNo;

	@Column(name="member_email")
	private String memberEmail;

	@Column(name="member_pwd")
	private String memberPwd;

	@Column(name="member_truename")
	private String memberTruename;

	private String phone;

	@Column(name="region_area_name")
	private String regionAreaName;

	@Column(name="region_city")
	private int regionCity;

	@Column(name="region_city_name")
	private String regionCityName;

	@Column(name="region_prov")
	private int regionProv;

	@Column(name="region_prov_name")
	private String regionProvName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_time")
	private Date registerTime;

	@Column(name="sales_member_address")
	private String salesMemberAddress;

	@Column(name="sales_member_name")
	private String salesMemberName;

	private byte sex;

	private byte status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public SalesMember() {
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountOwner() {
		return this.accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdentityImage() {
		return this.identityImage;
	}

	public void setIdentityImage(String identityImage) {
		this.identityImage = identityImage;
	}

	public String getIdentityNo() {
		return this.identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getMemberEmail() {
		return this.memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPwd() {
		return this.memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberTruename() {
		return this.memberTruename;
	}

	public void setMemberTruename(String memberTruename) {
		this.memberTruename = memberTruename;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegionAreaName() {
		return this.regionAreaName;
	}

	public void setRegionAreaName(String regionAreaName) {
		this.regionAreaName = regionAreaName;
	}

	public int getRegionCity() {
		return this.regionCity;
	}

	public void setRegionCity(int regionCity) {
		this.regionCity = regionCity;
	}

	public String getRegionCityName() {
		return this.regionCityName;
	}

	public void setRegionCityName(String regionCityName) {
		this.regionCityName = regionCityName;
	}

	public int getRegionProv() {
		return this.regionProv;
	}

	public void setRegionProv(int regionProv) {
		this.regionProv = regionProv;
	}

	public String getRegionProvName() {
		return this.regionProvName;
	}

	public void setRegionProvName(String regionProvName) {
		this.regionProvName = regionProvName;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getSalesMemberAddress() {
		return this.salesMemberAddress;
	}

	public void setSalesMemberAddress(String salesMemberAddress) {
		this.salesMemberAddress = salesMemberAddress;
	}

	public String getSalesMemberName() {
		return this.salesMemberName;
	}

	public void setSalesMemberName(String salesMemberName) {
		this.salesMemberName = salesMemberName;
	}

	public byte getSex() {
		return this.sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}