package cn.org.citycloud.controller;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.BankBindBean;
import cn.org.citycloud.constants.Constants;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.Supplier;
import cn.org.citycloud.entity.SupplierUser;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.SupplierDao;
import cn.org.citycloud.repository.SupplierUserDao;
import cn.org.citycloud.utils.SmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 银行卡绑定接口
 * @author Allen
 *
 */
@RestController
@Api(tags="银行卡", value = "/bankbind",  description = "银行卡绑定接口", consumes="application/json")
public class BankbindController extends BaseController{

	@Autowired
	private SupplierUserDao supplierUserDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	
	@Autowired
	MemcachedClient memcachedClient;

	
	/**
	 * 银行卡绑定
	 * @throws BusinessErrorException
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	@RequestMapping(value = "/bankbind", method = RequestMethod.POST)
	@ApiOperation(value = "银行卡绑定", notes = "银行卡绑定")
	public Object register(@ApiParam(value = "银行卡绑定", required = true) @Valid @RequestBody BankBindBean bean)
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

		Supplier supplier = supplierDao.findByPhone(bean.getUserName());
		
		BeanUtils.copyProperties(bean, supplier);
		
		return supplierDao.save(supplier);

	}
	
	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@RequestMapping(value = "/auth/{phone}", method = RequestMethod.POST)
	@ApiOperation(value = "发送短信验证码", notes = "短信验证码（万能Code=>88888)")
	public void sendSms(@ApiParam(value = "手机号码", required = true) @PathVariable String phone)
			throws TimeoutException, InterruptedException, MemcachedException {
		String code = SmsUtil.getRandomStr(true, 6);

		SmsUtil.sendSms(phone, code);
		memcachedClient.set(phone, 180, code);

	}
	
	
	

}
