package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the member_level database table.
 * 
 */
@Entity
@Table(name="member_level")
@NamedQuery(name="MemberLevel.findAll", query="SELECT m FROM MemberLevel m")
public class MemberLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="member_level_id")
	private int memberLevelId;

	private int growth;

	@Column(name="member_level")
	private String memberLevel;

	public MemberLevel() {
	}

	public int getMemberLevelId() {
		return this.memberLevelId;
	}

	public void setMemberLevelId(int memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	public int getGrowth() {
		return growth;
	}

	public void setGrowth(int growth) {
		this.growth = growth;
	}

	public String getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

}