package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="member_id")
	private int memberId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="member_avatar")
	private String memberAvatar;

	@Column(name="member_email")
	private String memberEmail;

	@Column(name="member_level_id")
	private int memberLevelId;

	@Column(name="member_login_ip")
	private String memberLoginIp;

	@Column(name="member_login_num")
	private int memberLoginNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="member_login_time")
	private Date memberLoginTime;

	@Column(name="member_old_login_ip")
	private String memberOldLoginIp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="member_old_login_time")
	private Date memberOldLoginTime;

	@Column(name="member_phone")
	private String memberPhone;

	@JsonIgnore
	@Column(name="member_pwd")
	private String memberPwd;

	@Column(name="member_sex")
	private byte memberSex;

	@Column(name="member_status")
	private byte memberStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="member_time")
	private Date memberTime;

	@Column(name="member_truename")
	private String memberTruename;

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
	@Column(name="update_date")
	private Date updateDate;

	public Member() {
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMemberAvatar() {
		return this.memberAvatar;
	}

	public void setMemberAvatar(String memberAvatar) {
		this.memberAvatar = memberAvatar;
	}

	public String getMemberEmail() {
		return this.memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public int getMemberLevelId() {
		return this.memberLevelId;
	}

	public void setMemberLevelId(int memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	public String getMemberLoginIp() {
		return this.memberLoginIp;
	}

	public void setMemberLoginIp(String memberLoginIp) {
		this.memberLoginIp = memberLoginIp;
	}

	public int getMemberLoginNum() {
		return this.memberLoginNum;
	}

	public void setMemberLoginNum(int memberLoginNum) {
		this.memberLoginNum = memberLoginNum;
	}

	public Date getMemberLoginTime() {
		return this.memberLoginTime;
	}

	public void setMemberLoginTime(Date memberLoginTime) {
		this.memberLoginTime = memberLoginTime;
	}

	public String getMemberOldLoginIp() {
		return this.memberOldLoginIp;
	}

	public void setMemberOldLoginIp(String memberOldLoginIp) {
		this.memberOldLoginIp = memberOldLoginIp;
	}

	public Date getMemberOldLoginTime() {
		return this.memberOldLoginTime;
	}

	public void setMemberOldLoginTime(Date memberOldLoginTime) {
		this.memberOldLoginTime = memberOldLoginTime;
	}

	public String getMemberPhone() {
		return this.memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberPwd() {
		return this.memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public byte getMemberSex() {
		return this.memberSex;
	}

	public void setMemberSex(byte memberSex) {
		this.memberSex = memberSex;
	}

	public byte getMemberStatus() {
		return this.memberStatus;
	}

	public void setMemberStatus(byte memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getMemberTime() {
		return this.memberTime;
	}

	public void setMemberTime(Date memberTime) {
		this.memberTime = memberTime;
	}

	public String getMemberTruename() {
		return this.memberTruename;
	}

	public void setMemberTruename(String memberTruename) {
		this.memberTruename = memberTruename;
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

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}