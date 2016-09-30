package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the member_addr database table.
 * 
 */
@Entity
@Table(name="member_addr")
@NamedQuery(name="MemberAddr.findAll", query="SELECT m FROM MemberAddr m")
public class MemberAddr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="member_addr_id")
	private int memberAddrId;

	@Column(name="contacts_address")
	private String contactsAddress;

	@Column(name="contacts_name")
	private String contactsName;

	@Column(name="contacts_phone")
	private String contactsPhone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="member_id")
	private int memberId;

	@Column(name="post_code")
	private String postCode;

	@Column(name="region_area_name")
	private String regionAreaName;

	@Column(name="region_city_name")
	private String regionCityName;

	@Column(name="region_code")
	private int regionCode;

	@Column(name="region_prov_name")
	private String regionProvName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public MemberAddr() {
	}

	public int getMemberAddrId() {
		return this.memberAddrId;
	}

	public void setMemberAddrId(int memberAddrId) {
		this.memberAddrId = memberAddrId;
	}

	public String getContactsAddress() {
		return this.contactsAddress;
	}

	public void setContactsAddress(String contactsAddress) {
		this.contactsAddress = contactsAddress;
	}

	public String getContactsName() {
		return this.contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsPhone() {
		return this.contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getRegionAreaName() {
		return this.regionAreaName;
	}

	public void setRegionAreaName(String regionAreaName) {
		this.regionAreaName = regionAreaName;
	}

	public String getRegionCityName() {
		return this.regionCityName;
	}

	public void setRegionCityName(String regionCityName) {
		this.regionCityName = regionCityName;
	}

	public int getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionProvName() {
		return this.regionProvName;
	}

	public void setRegionProvName(String regionProvName) {
		this.regionProvName = regionProvName;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}