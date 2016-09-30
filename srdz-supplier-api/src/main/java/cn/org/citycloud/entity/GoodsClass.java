package cn.org.citycloud.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the goods_class database table.
 * 
 */
@Entity
@Table(name="goods_class")
@NamedQuery(name="GoodsClass.findAll", query="SELECT g FROM GoodsClass g")
public class GoodsClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GOODS_CLASS_GOODSCLASSID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOODS_CLASS_GOODSCLASSID_GENERATOR")
	@Column(name="goods_class_id")
	private int goodsClassId;

	@Column(name="class_image")
	private String classImage;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="del_flag")
	private int delFlag;

	@Column(name="goods_class_name")
	private String goodsClassName;

	@Lob
	@Column(name="goods_desc")
	private String goodsDesc;

	@Column(name="parent_id")
	private int parentId;

	private int sort;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goodsClass")
	private Set<GoodsClassBanner> goodsClassBanner = new HashSet<GoodsClassBanner>();

	public GoodsClass() {
	}

	public int getGoodsClassId() {
		return this.goodsClassId;
	}

	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public String getClassImage() {
		return this.classImage;
	}

	public void setClassImage(String classImage) {
		this.classImage = classImage;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getGoodsClassName() {
		return this.goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<GoodsClassBanner> getGoodsClassBanner() {
		return goodsClassBanner;
	}

	public void setGoodsClassBanner(Set<GoodsClassBanner> goodsClassBanner) {
		this.goodsClassBanner = goodsClassBanner;
	}
	
	

}