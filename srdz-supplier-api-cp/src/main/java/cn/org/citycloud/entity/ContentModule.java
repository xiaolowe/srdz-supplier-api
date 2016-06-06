package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the content_module database table.
 * 
 */
@Entity
@Table(name="content_module")
@NamedQuery(name="ContentModule.findAll", query="SELECT c FROM ContentModule c")
public class ContentModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="content_module_id")
	private int contentModuleId;

	@Column(name="content_module_name")
	private String contentModuleName;

	public ContentModule() {
	}

	public int getContentModuleId() {
		return this.contentModuleId;
	}

	public void setContentModuleId(int contentModuleId) {
		this.contentModuleId = contentModuleId;
	}

	public String getContentModuleName() {
		return this.contentModuleName;
	}

	public void setContentModuleName(String contentModuleName) {
		this.contentModuleName = contentModuleName;
	}

}