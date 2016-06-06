package cn.org.citycloud.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.auth0.jwt.JWTSigner;
import cn.org.citycloud.bean.ForgetPwdBean;
import cn.org.citycloud.bean.LoginBean;
import cn.org.citycloud.bean.UserPwdBean;
import cn.org.citycloud.bean.UserToken;
import cn.org.citycloud.constants.Constants;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.Supplier;
import cn.org.citycloud.entity.SupplierUser;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.SupplierDao;
import cn.org.citycloud.repository.SupplierUserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 供应商用户接口
 * @author Allen
 *
 */
@RestController
@Api(tags="供应商用户", value = "/supplieruser",  description = "供应商用户接口", consumes="application/json")
public class SupplierUserController extends BaseController{

	@Autowired
	private SupplierUserDao supplierUserDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	MemcachedClient memcachedClient;


	/**
	 * 供应商登录
	 */
	@RequestMapping(value = "/supplieruser", method = RequestMethod.POST)
	@ApiOperation(value = "供应商登录", notes = "供应商登录")
	public Object stadiumLogin(@ApiParam(value = "供应商登录", required = true) @Valid @RequestBody LoginBean bean) throws Exception, RuntimeException {
		
		String userName = bean.getUserName();
		String userPwd = bean.getUserPwd();
		
		SupplierUser supplierUser = supplierUserDao.findByUserNameAndUserPwd(userName, userPwd);
		if(supplierUser == null){
			throw new BusinessErrorException(ErrorCodes.NO_DATA, "用户名或密码错误");
		}
		if(supplierUser != null && supplierUser.getUserStatus() == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该用户已经被禁用");
		}
		
		Supplier supplier = supplierDao.findByPhone(userName);
		if(supplier == null){
			throw new BusinessErrorException(ErrorCodes.NO_DATA, "该供应商不存在");
		}
		supplierUser.setSupplierId(supplier.getSupplierId());
		supplierUserDao.save(supplierUser);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userName", userName);
		paramMap.put("userPwd", userPwd);
		
		// 生成登录用Token
		String token = generateToken(paramMap, Constants.TOKEN_SECRET);
		
		// 将Token存入Memcached
		UserToken tokenEntity = new UserToken();
		tokenEntity.setToken(token);
		tokenEntity.setCreateTs(System.currentTimeMillis());
		tokenEntity.setUserId(supplierUser.getSupplierUserId());
		tokenEntity.setUserName(supplierUser.getUserName());
		tokenEntity.setExpiresIn(Constants.TOKEN_EXPIRES_IN);
		tokenEntity.setSupplierId(supplier.getSupplierId());
		tokenEntity.setContactName(supplier.getContactName());
		tokenEntity.setSupplierStatus(supplier.getStatus());
		
		
		memcachedClient.set(token, Integer.parseInt(String.valueOf(Constants.TOKEN_EXPIRES_IN)), tokenEntity);
		
		Map<String, String> rstMap = new HashMap<String, String>();
		rstMap.put("user_id", String.valueOf(supplierUser.getSupplierUserId()));
		rstMap.put("user_name", supplierUser.getUserName());
		rstMap.put("token", token);
		rstMap.put("expires_in", String.valueOf(Constants.TOKEN_EXPIRES_IN));
		rstMap.put("supplier_id", String.valueOf(supplier.getSupplierId()));
		rstMap.put("supplier_status", String.valueOf(supplier.getStatus()));
		rstMap.put("contact_name", supplier.getContactName());
		
		return rstMap;


	}
	
	
	/**
	 * 修改登录密码
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/modifyuserpwd", method = RequestMethod.POST)
	@ApiOperation(value = "修改登录密码", notes = "修改登录密码")
	public Object modifyUserPwd(@ApiParam(value = "登录密码修改信息", required = true) @Valid @RequestBody UserPwdBean bean) throws BusinessErrorException {
		
		SupplierUser supplierUser = supplierUserDao.findOne(getUserId());
		
		if(supplierUser == null) {
			throw new BusinessErrorException(ErrorCodes.NON_EXIST_MEMBER, "用户账号不存在");
		}
		
		if (!bean.getUserPwd().equals(bean.getConfirmPwd())) {
			throw new BusinessErrorException(ErrorCodes.PARAM_ERROR, "两次输入的密码不一致，请重新输入");
		}
		
		if (!bean.getOldUserPwd().equals(supplierUser.getUserPwd())) {
			throw new BusinessErrorException(ErrorCodes.PARAM_ERROR, "密码输入错误，请重新输入");
		}

		supplierUser.setUserPwd(bean.getUserPwd());
		supplierUser.setUpdateTime(new Date());
		
		
		supplierUserDao.save(supplierUser);
		
		return supplierUser;
		
	}
	
	/**
	 * 忘记密码
	 * @throws BusinessErrorException
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	@RequestMapping(value = "/forgetpwd", method = RequestMethod.POST)
	@ApiOperation(value = "忘记密码", notes = "忘记密码")
	public Object register(@ApiParam(value = "忘记密码", required = true) @Valid @RequestBody ForgetPwdBean bean)
			throws BusinessErrorException, TimeoutException, InterruptedException, MemcachedException {

		SupplierUser supplierUser = supplierUserDao.findByUserName(bean.getUserName());
		
		if(supplierUser == null){
			throw new BusinessErrorException(ErrorCodes.PARAM_ERROR, "该用户不存在，请重新输入");
		}
		
		String smsMsg = memcachedClient.get(bean.getUserName());

		if (!Constants.MAGIC_CODE.equalsIgnoreCase(bean.getPhoneCode())) {
			if (smsMsg == null || !smsMsg.equalsIgnoreCase(bean.getPhoneCode())) {
				throw new BusinessErrorException(ErrorCodes.PARAM_ERROR, "验证码错误，请重新输入");
			}
		}

		if (!bean.getUserPwd().equals(bean.getConfirmPwd())) {
			throw new BusinessErrorException(ErrorCodes.PARAM_ERROR, "两次输入的密码不一致，请重新输入");
		}

		supplierUser.setUserPwd(bean.getUserPwd());
		
		
		return supplierUserDao.save(supplierUser);

	}
	
//	/**
//	 * 发送短信验证码
//	 * 
//	 * @param phone
//	 * @throws TimeoutException
//	 * @throws InterruptedException
//	 * @throws MemcachedException
//	 */
//	@RequestMapping(value = "/sms/{phone}", method = RequestMethod.POST)
//	@ApiOperation(value = "发送短信验证码", notes = "短信验证码（万能Code=>88888)")
//	public String sendSms(@ApiParam(value = "手机号码", required = true) @PathVariable String phone)
//			throws TimeoutException, InterruptedException, MemcachedException {
//		String code = SmsUtil.getRandomStr(true, 6);
//
//		SmsUtil.sendSms(phone, code);
//		memcachedClient.set(phone, 180, code);
//
//		return code;
//	}
	
	/**
	 * 退出登录
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ApiOperation(value = "退出登录", notes = "退出登录")
	public void logout() throws TimeoutException, InterruptedException, MemcachedException {
		
		// 清除用户Token缓存
		memcachedClient.delete(getAccessToken());
		
	}
	
	
	/**
	 * 生成token值
	 * @param user
	 * @return
	 */
	private String generateToken(Map<String, String> user, String secret) {
		JWTSigner jwtSigner = new JWTSigner(secret);
		Map<String, Object> claims = new HashMap<String, Object>();
		
		claims.put("userName", user.get("userName"));
		claims.put("userPwd", user.get("userPwd"));
		claims.put("timestamp", System.currentTimeMillis());
		String token = jwtSigner.sign(claims);
		
		return token;
	}

}
