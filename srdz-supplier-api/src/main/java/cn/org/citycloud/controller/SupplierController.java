package cn.org.citycloud.controller;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.ShopBean;
import cn.org.citycloud.bean.SupplierBean;
import cn.org.citycloud.constants.Constants;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.Message;
import cn.org.citycloud.entity.Supplier;
import cn.org.citycloud.entity.SupplierUser;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.MessageDao;
import cn.org.citycloud.repository.SupplierDao;
import cn.org.citycloud.repository.SupplierUserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 供应商接口
 * @author Allen
 *
 */
@RestController
@Api(tags="供应商", value = "/supplier",  description = "供应商接口", consumes="application/json")
public class SupplierController extends BaseController{

	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private SupplierUserDao supplierUserDao;
	
	@Autowired
	private MessageDao messageDao;
	

	
	/**
	 * 供应商认证
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/supplier", method = RequestMethod.PUT)
	@ApiOperation(value = "供应商认证", notes = "供应商认证")
	public Object modifyGoodsStatus(@Valid @RequestBody SupplierBean bean) throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		
		Supplier supplier = supplierDao.findOne(supplierId);
		
		SupplierUser supplierUser = supplierUserDao.findBySupplierId(supplierId);
		
		BeanUtils.copyProperties(bean, supplier);
		
		supplier.setStatus(1);;   //供应商用户认证  
		
		Message message = new Message();
		message.setSenderId(supplier.getSupplierId());
		message.setSender(supplierUser.getUserName());
		message.setTriggerEvent("申请审核");
		message.setMessageContent("系统消息：您有一条新的供应商审核消息，请查看处理！");
		Date now = new Date();
		message.setSendTime(now);
		message.setCreateDate(now);
		message.setUpdateDate(now);
		message.setSenderPlatform(Constants.PLATFORM_SUPPLIER);
		message.setPlatform(Constants.PLATFORM_ADMIN); 	//1  管理后台   2 供应商后台   3   服务中心    4   用户
		
		messageDao.save(message);
		
		return supplierDao.save(supplier);
		
		
	}
	
	/**
	 * 门店装修
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/suppliershop", method = RequestMethod.PUT)
	@ApiOperation(value = "门店装修", notes = "门店装修")
	public Object editshop(@Valid @RequestBody ShopBean bean) throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		
		Supplier supplier = supplierDao.findOne(supplierId);
		
		BeanUtils.copyProperties(bean, supplier);
		
		return supplierDao.save(supplier);
		
		
	}

	/**
	 * 查看供应商信息
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	@ApiOperation(value = "查看供应商信息", notes = "查看供应商信息")
	public Object getStadium() throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		Supplier supplier = supplierDao.findOne(supplierId);

		return supplier;
		
	}

}
