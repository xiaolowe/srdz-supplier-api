package cn.org.citycloud.controller;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.entity.SupplierUser;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.SupplierUserDao;
import cn.org.citycloud.utils.SmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 短信控制器
 * @author lanbo
 *
 */
@RestController
@RequestMapping(value = "/sms")
@Api(tags="共通接口", position=8, value = "/sms", description = "短信模块", consumes="application/json")
public class SmsController {
	
	@Autowired
	private MemcachedClient cacheClient;
	
	@Autowired
	private SupplierUserDao supplierUserDao;
	
	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/sendCodes/{phone}", method = RequestMethod.POST)
	@ApiOperation(value = "发送短信验证码", notes = "短信验证码（万能Code=>666666)")
	public String sendSms(@ApiParam(value = "手机号码", required = true) @PathVariable String phone)
			throws TimeoutException, InterruptedException, MemcachedException, BusinessErrorException {
		
		SupplierUser supplierUser = supplierUserDao.findByUserName(phone);
		
		if(supplierUser == null){
			throw new BusinessErrorException(ErrorCodes.PARAM_ERROR, "该用户不存在，请重新输入");
		}
		
		String code = SmsUtil.getRandomStr(true, 6);
		
		// 发送短信信息体
		String msg = "%s为您的验证码，3分钟内有效。【善融大宗】";
		SmsUtil.sendSms(phone, String.format(msg, code));
		cacheClient.set(phone, 180, code);

		return code;
	}

}
