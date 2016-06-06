package cn.org.citycloud.bean;

import java.io.Serializable;

/**
 * Token Bean
 * 
 * @author Allen
 *
 */
public class UserToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5259132293621138195L;

	/**
	 * Token
	 */
	private String token;
	/**
	 * 供应商后台用户id
	 */
	private int userId;
	
	/**
	 * 供应商id
	 */
	private int supplierId;
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 供应商申请人姓名
	 */
	private String contactName;
	
	/**
	 * accessToken
	 */
	private String accessToken;

	/**
	 * 多少秒后过期
	 */
	private long expiresIn;

	/**
	 * 创建时间戳
	 */
	private long createTs;
	
	
	private int supplierStatus;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getCreateTs() {
		return createTs;
	}

	public void setCreateTs(long createTs) {
		this.createTs = createTs;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(int supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	
	
	

}
