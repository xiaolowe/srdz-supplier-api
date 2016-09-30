package cn.org.citycloud.core;

/**
 * 控制器基类
 * 
 * @author Allen
 *
 */
public class BaseController {

	/**
	 * 供应商用户Id
	 */
	private int userId;
	
	/**
	 * 供应商用户名
	 */
	private String userName;

	/**
	 * 供应商Id
	 */
	private int supplierId;

	/**
	 * token
	 */
	private String accessToken;

	
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
