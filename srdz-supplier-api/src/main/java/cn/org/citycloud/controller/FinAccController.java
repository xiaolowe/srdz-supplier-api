package cn.org.citycloud.controller;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.CashBean;
import cn.org.citycloud.constants.Constants;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.Cash;
import cn.org.citycloud.entity.FinAcc;
import cn.org.citycloud.entity.Supplier;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.CashDao;
import cn.org.citycloud.repository.FinAccDao;
import cn.org.citycloud.repository.SupplierDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 财务管理接口
 * @author Allen
 *
 */
@RestController
@Api(tags="财务模块", value = "/finacc",  description = "财务管理接口", consumes="application/json")
public class FinAccController extends BaseController{

	@Autowired
	private FinAccDao finAccDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private CashDao cashDao;

    private Logger logger = LoggerFactory.getLogger(FinAccController.class);

	/**
	 * 查看供应商财务信息
	 * @param bean
	 */
	@RequestMapping(value = "/finacc", method = RequestMethod.GET)
	@ApiOperation(value = "查看供应商财务信息", notes = "查看供应商财务信息")
	public Object getSupplierInfo() {
		
		Supplier supplier = supplierDao.findOne(getSupplierId());
		
		FinAcc finAcc = finAccDao.findByAccountNoAndAccountType(getSupplierId(), Constants.ACC_TYPE_SUPPLIER);   
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map.put("finAcc", finAcc);

		return map;
		
	}

	/**
	 * 提款记录列表
	 * @return
	 */
	@RequestMapping(value = "/cash", method = RequestMethod.GET)
	@ApiOperation(value = "提款记录列表", notes = "提款记录列表信息")
	public Object getStadiumList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "审核状态 ( 0 未审核  1 审核通过  2 已打款  3 已驳回 ) ", required = false) @RequestParam(value = "confirmStatus", defaultValue = "-1", required = false) int confirmStatus) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "cashId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
	    Page<Cash> page = cashDao.findAll(new Specification<Cash>() {
			@Override
			public Predicate toPredicate(Root<Cash> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				
				Path<String> supplierIdQuery = root.get("supplierId");  
				where = cb.and(where, cb.equal(supplierIdQuery, supplierId));
				
				if(confirmStatus > -1){
					Path<String> confirmStatussQuery = root.get("confirmStatus");  
					where = cb.and(where, cb.equal(confirmStatussQuery, confirmStatus));
					
				}
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	}

	
	/**
	 * 申请提款
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/getcash", method = RequestMethod.POST)
	@ApiOperation(value = " 申请提款", notes = " 申请提款")
    @Transactional(rollbackFor = Exception.class)
	public Object getCash(@Valid @RequestBody CashBean bean) throws BusinessErrorException {
        logger.info("添加供应商提款申请---------------------");
		int supplierId = getSupplierId();
		
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		List<Cash> cashcheck = cashDao.findBySupplierIdAndConfirmStatus(supplierId, 0);	// 0 未审核的 提款信息
		if(cashcheck != null && cashcheck.size() > 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "存在未审核的提款信息，请等待审核后提款！");
		}
		
		Supplier supplier = supplierDao.findOne(supplierId);

		FinAcc finAcc = finAccDao.findByAccountNoAndAccountType(supplierId, Constants.ACC_TYPE_SUPPLIER);
        logger.info("修改账户金额，账户：{}， 账户类型：{}", finAcc.getAccountNo(), finAcc.getAccountType());
        logger.info("账户当前财产状况, 收入：{}，支出：{}，余额：{}", finAcc.getAccountIncome(), finAcc.getAccountPay(), finAcc.getAccountBal());
		BigDecimal accountBal = finAcc.getAccountBal();
		if(accountBal.compareTo(bean.getApplyMoney()) == -1){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "余额不足 !");
		}
		
	    BigDecimal accountSurplus = accountBal.subtract(bean.getApplyMoney());
	    finAcc.setAccountBal(accountSurplus);
	    BigDecimal finaccPay = finAcc.getAccountPay();
        logger.info("账户余额增加：{}，支出增加：{}", bean.getApplyMoney(), bean.getApplyMoney());
	    if(finaccPay == null){
	    	finaccPay = new BigDecimal(0);
	    }
	    BigDecimal accountPay = bean.getApplyMoney().add(finaccPay);
	    finAcc.setAccountPay(accountPay);
	    finAccDao.save(finAcc);
		
	    int userId = getUserId();
		String phone = getUserName();
		
		Cash cash = new Cash();
		Date now = new Date();
		cash.setApplyTime(now);
		cash.setConfirmStatus(0);  //未审核
		cash.setApplyType(1); 	//取款
		cash.setSupplierId(supplierId);
		cash.setApplyUserId(userId);
		cash.setApplyUserName(phone);
		cash.setCompanyName(supplier.getComanyName());
		cash.setAccountType((byte)Constants.ACC_TYPE_SUPPLIER);	
		cash.setBankAccName(supplier.getAccountOwner());
		cash.setBankAccNumber(supplier.getCardNo());
		cash.setBankName(supplier.getBankName() + supplier.getAccountBank());
		cash.setApplyMoney(bean.getApplyMoney());
		cash.setAccountSurplus(accountSurplus);
		cash.setBankOwner(supplier.getAccountOwner());
		cash.setCreateTime(now);
		cash.setUpdateTime(now);
		
		return cashDao.save(cash);

	}
	
	/**
	 * 提款明细
	 * @param bean
	 */
	@RequestMapping(value = "/cashinfo", method = RequestMethod.GET)
	@ApiOperation(value = "提款明细信息", notes = "提款明细信息")
	public Object getCashInfo() {
		
		
		FinAcc finAcc = finAccDao.findByAccountNoAndAccountType(getSupplierId(), Constants.ACC_TYPE_SUPPLIER);   
		

		return finAcc;
		
	}
	
	
}
