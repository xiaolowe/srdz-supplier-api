package cn.org.citycloud.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.RefuseOrderBean;
import cn.org.citycloud.constants.Constants;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.AfterSale;
import cn.org.citycloud.entity.Message;
import cn.org.citycloud.entity.Order;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.AfterSaleDao;
import cn.org.citycloud.repository.MessageDao;
import cn.org.citycloud.repository.OrderDao;
import cn.org.citycloud.service.OrderService;
import cn.org.citycloud.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 订单接口
 * @author Allen
 *
 */
@RestController
@Api(tags="订单", value = "/order",  description = "订单接口", consumes="application/json")
public class OrderController extends BaseController{

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AfterSaleDao afterSaleDao;
	
	@Autowired
	private MessageDao messageDao;
	
	

	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	@ApiOperation(value = "订单列表", notes = "订单列表信息")
	public Object getStadiumList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "开始时间", required = false) @RequestParam(value = "startTime", required = false) String startTime,
			@ApiParam(value = "结束时间", required = false) @RequestParam(value = "endTime", required = false) String endTime,
			@ApiParam(value = "会员账号", required = false) @RequestParam(value = "memberPhone", required = false) String memberPhone,
			@ApiParam(value = "订单号", required = false) @RequestParam(value = "orderId", required = false, defaultValue = "0")  int orderId,
			@ApiParam(value = "订单状态：0(已取消)；10(默认):待支付；20:待发货； 40:已发货；50:待评价； 60:已完成", required = false) @RequestParam(value = "orderStatus", defaultValue = "-1", required = false) int orderStatus,
			@ApiParam(value = "退款状态 （100:待退款  110:退款中 120:待汇款 130:拒绝退款 140:已退款）", required = false) @RequestParam(value = "backOrderStatus", defaultValue = "-1", required = false) int backOrderStatus) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "orderId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<Order> page = orderDao.findAll(new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
					Path<Date> addTime = root.get("orderTime"); 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = new Date();
					Date end = new Date();
					String startTime1 = startTime + " 00:00:00";
					String endTime1 = endTime + " 23:59:59";
					try {
						start = sdf.parse(startTime1);
						end = sdf.parse(endTime1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					where = cb.and(where, cb.between(addTime, start, end)); 
					
				}
				if(StringUtils.isNotBlank(memberPhone)){
					Path<String> memberPhoneQuery = root.get("memberPhone");  
					where = cb.and(where, cb.like(memberPhoneQuery, "%"+memberPhone+"%"));
					
				}
				if(orderId > 0){
					Path<String> orderIdQuery = root.get("orderId");  
					where = cb.and(where, cb.equal(orderIdQuery, orderId));
					
				}
				if(supplierId > 0){
					Path<String> supplierIdQuery = root.get("supplierId");  
					where = cb.and(where, cb.equal(supplierIdQuery, supplierId));
					
				}
				if(orderStatus > -1){
					Path<String> orderStatusQuery = root.get("orderStatus");  
					where = cb.and(where, cb.equal(orderStatusQuery, orderStatus));
					
				}
				if(backOrderStatus > -1){
					Path<String> backOrderStatusQuery = root.get("backOrderStatus");  
					where = cb.and(where, cb.equal(backOrderStatusQuery, backOrderStatus));
					
				}
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}
	
	/**
	 * 查看订单信息
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查看订单信息", notes = "查看订单信息")
	public Object getOrderInfo(@ApiParam(value = "订单Id", required = true) @PathVariable int id) throws BusinessErrorException {
		
		Order order = orderDao.findOne(id);

		return order;
		
	}
	
	/**
	 * 确认发货
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/confirmSend/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "确认发货", notes = "确认发货")
	public Object confirmSend(@ApiParam(value = "订单Id", required = false) @PathVariable int id,
			@ApiParam(value = "是否选择物流公司（1 是  0 否）", required = false) @RequestParam(value = "isFlow", defaultValue = "0")  int isFlow,
			@ApiParam(value = "物流公司", required = false) @RequestParam(value = "flowCompanyName",required = false) String flowCompanyName,
			@ApiParam(value = "物流单号", required = false) @RequestParam(value = "flowNumber", defaultValue = "0")  BigInteger flowNumber,
			@ApiParam(value = "订单状态：0(已取消)；10(默认):待支付；20:待发货； 40:已发货；50:待评价； 60:已完成", required = false) @RequestParam(value = "orderStatus",required = false)  int orderStatus) throws BusinessErrorException {
		
		
		Order order = orderDao.findOne(id);
		
		order.setIsFlow(isFlow);
		order.setFlowCompanyName(flowCompanyName);
		order.setFlowNumber(flowNumber);
		order.setOrderStatus(orderStatus);
		
		return orderDao.save(order);
		
		
	}
	
	/**
	 * 确认退款
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/rollback/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "确认退款", notes = "确认退款")
	public Object confirmrollback(@ApiParam(value = "订单Id", required = true) @PathVariable int id) throws BusinessErrorException {
		
		
		Order order = orderDao.findOne(id);
		
		order.setBackOrderStatus(110);	// 退款状态 （100:待退款  110:退款中   120:待汇款  130:拒绝退款 140:已退款）
		
		Message msgmember = new Message();
		msgmember.setSenderId(getSupplierId());
		msgmember.setSender(getUserName());
		msgmember.setReceiverId(order.getMemberId());
		msgmember.setReceiver(order.getMemberPhone());
		msgmember.setTriggerEvent("供应商退款审核通过");
		msgmember.setMessageContent("系统消息：订单"+id+"退款申请卖家已经通过，平台正在审核，请耐心等待！");
		msgmember.setSenderPlatform(Constants.PLATFORM_SUPPLIER);
		msgmember.setPlatform(Constants.PLATFORM_MEMBER); 	//1  管理后台   2 供应商后台   3   服务中心    4   用户
		
		Date now = new Date();
		msgmember.setSendTime(now);
		msgmember.setCreateDate(now);
		msgmember.setUpdateDate(now);
		messageDao.save(msgmember);
		
		
		Message msgsupplier = new Message();
		msgsupplier.setSenderId(getSupplierId());
		msgsupplier.setSender(getUserName());
		msgsupplier.setTriggerEvent("供应商退款审核通过");
		msgsupplier.setMessageContent("系统消息： 订单"+id+" 退款申请，请查看处理。");
		msgmember.setSenderPlatform(Constants.PLATFORM_SUPPLIER);
		msgsupplier.setPlatform(Constants.PLATFORM_ADMIN); 	//1  管理后台   2 供应商后台   3   服务中心    4   用户
		msgsupplier.setSendTime(now);
		msgsupplier.setCreateDate(now);
		msgsupplier.setUpdateDate(now);
		messageDao.save(msgsupplier);
		
		return orderDao.save(order);
		
		
	}
	
	/**
	 * 拒绝退款
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/refuse/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "拒绝退款", notes = "拒绝退款")
	public Object refuse(@ApiParam(value = "订单Id", required = true) @PathVariable int id,@Valid @RequestBody RefuseOrderBean bean ) throws BusinessErrorException {
		
		
		Order order = orderDao.findOne(id);
		
		order.setBackOrderStatus(130);	// 退款状态 （100:待退款  110:退款中   120:待汇款  130:拒绝退款 140:已退款）
		order.setBackOrderCause(bean.getOrderCause());
		
		orderDao.save(order);
		
		AfterSale afterSale =  afterSaleDao.findByOrderId(id);
		
		afterSale.setOrderId(id);
		afterSale.setRollbackTime(order.getOrderTime());
		afterSale.setMemberId(order.getMemberId());
		afterSale.setMemberName(order.getMemberName());
		if(order.getOrderInfo() != null){
			afterSale.setSupplierId(order.getOrderInfo().getSupplierId());
			afterSale.setSupplierName(order.getOrderInfo().getSupplierName());
		}
		
		Message msgmember = new Message();
		msgmember.setSenderId(getSupplierId());
		msgmember.setSender(getUserName());
		msgmember.setReceiverId(order.getMemberId());
		msgmember.setReceiver(order.getMemberPhone());
		msgmember.setTriggerEvent("供应商退款审核不通过");
		msgmember.setMessageContent("系统消息： 订单"+id+" 退款申请卖家被驳回，驳回原因是："+order.getBackOrderCause()+"。s有问题请及时联系。");
		msgmember.setSenderPlatform(Constants.PLATFORM_SUPPLIER);
		msgmember.setPlatform(Constants.PLATFORM_MEMBER); 	//1  管理后台   2 供应商后台   3   服务中心    4   用户
		Date now = new Date();
		msgmember.setSendTime(now);
		msgmember.setCreateDate(now);
		msgmember.setUpdateDate(now);
		messageDao.save(msgmember);
		
		
		Message msgsupplier = new Message();
		msgsupplier.setSenderId(getSupplierId());
		msgsupplier.setSender(getUserName());
		msgsupplier.setTriggerEvent("供应商退款审核不通过");
		msgsupplier.setMessageContent("系统消息： 订单"+id+" 退款申请被卖家驳回，驳回原因是："+order.getBackOrderCause()+"。用户姓名"+order.getMemberName()+"，联系方式"+order.getMemberPhone()+"，请及时协调处理。");
		msgmember.setSenderPlatform(Constants.PLATFORM_SUPPLIER);
		msgsupplier.setPlatform(Constants.PLATFORM_SERVICECENTER); 	//1  管理后台   2 供应商后台   3   服务中心    4   用户
		msgsupplier.setSendTime(now);
		msgsupplier.setCreateDate(now);
		msgsupplier.setUpdateDate(now);
		messageDao.save(msgsupplier);
		
		
		return orderDao.save(order);
		
		
	}
	

	/**
	 * 导出报表
	 */
	@RequestMapping(value="/orderExcel", method = RequestMethod.GET)
    public String download(@ApiParam(value = "供应商ID", required = false) @RequestParam(value = "supplierId", required = true, defaultValue = "0")  int supplierId,
    		@ApiParam(value = "订单状态", required = false) @RequestParam(value = "orderStatus", required = false, defaultValue = "-1")  int orderStatus,
    		@ApiParam(value = "退款状态 ", required = false) @RequestParam(value = "backOrderStatus", defaultValue = "-1", required = false) int backOrderStatus,
    		@ApiParam(value = "会员名称", required = false) @RequestParam(value = "memberPhone", required = false)  String memberPhone,
    		@ApiParam(value = "订单ID", required = false) @RequestParam(value = "orderId", required = false, defaultValue = "-1")  int orderId,
    		@ApiParam(value = "开始时间", required = false) @RequestParam(value = "stime", required = false)  String startTime,
    		@ApiParam(value = "结束时间", required = false) @RequestParam(value = "etime", required = false)  String endTime,
    		HttpServletRequest request,HttpServletResponse response) throws Exception, RuntimeException, IOException{
		
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "参数错误！");
		}
      
	    Order order = new Order();
	    order.setOrderStatus(orderStatus);
	    order.setBackOrderStatus(backOrderStatus);
	    order.setMemberPhone(memberPhone);
	    order.setOrderId(orderId);
	    order.setSupplierId(supplierId);
	    
		List<Order> listExcel = orderService.getExcelOrderList(order, startTime, endTime);
	    
		String fileName="订单信息";
        //填充实体类实体数据
		List<Order> projects= listExcel;
        List<Map<String,Object>> list=createExcelRecord(projects);
        String columnNames[]={"订单号","下单时间","会员手机号","订单金额","使用优惠券金额","供应商收益", "订单状态", "退款状态"};//列名
        String keys[]    =     {"orderId","orderTime","memberPhone","orderPrice","couponPrice","supplierAmount", "orderStatus", "backOrderStatus"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }
	
    private List<Map<String, Object>> createExcelRecord(List<Order> projects) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        Order project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("orderId", project.getOrderId());
	            mapValue.put("orderTime", project.getOrderTime());
	            mapValue.put("memberPhone", project.getMemberPhone());
	            mapValue.put("orderPrice", project.getOrderPrice());
	            mapValue.put("couponPrice", project.getCouponPrice());
	            mapValue.put("supplierAmount", project.getSupplierAmount());
	     //       订单状态：0(已取消)；10(默认):待支付；20:待发货； 40:已发货；50:待评价； 60:已完成
	            
	            if(project.getOrderStatus() == 0){
	            	mapValue.put("orderStatus", "已取消");
	            }
	            if(project.getOrderStatus() == 10){
	            	mapValue.put("orderStatus", "待支付");
	            }
	            if(project.getOrderStatus() == 20){
	            	mapValue.put("orderStatus", "待发货");
	            }
	            if(project.getOrderStatus() == 40){
	            	mapValue.put("orderStatus", "已发货");
	            }
	            if(project.getOrderStatus() == 50){
	            	mapValue.put("orderStatus", "待评价");
	            }
	            if(project.getOrderStatus() == 60){
	            	mapValue.put("orderStatus", "已完成");
	            }
	            
	     //       退款状态 （100:待退款  110:退款中 120:待汇款 130:拒绝退款 140:已退款）
	            
	            if(project.getOrderStatus() == 0){
	            	mapValue.put("backOrderStatus", "");
	            }
	            if(project.getOrderStatus() == 100){
	            	mapValue.put("backOrderStatus", "待退款");
	            }
	            if(project.getOrderStatus() == 110){
	            	mapValue.put("backOrderStatus", "退款中");
	            }
	            if(project.getOrderStatus() == 120){
	            	mapValue.put("backOrderStatus", "待汇款");
	            }
	            if(project.getOrderStatus() == 130){
	            	mapValue.put("backOrderStatus", "拒绝退款");
	            }
	            if(project.getOrderStatus() == 140){
	            	mapValue.put("backOrderStatus", "已退款");
	            }
	           
            listmap.add(mapValue);
        }
        return listmap;
    
    }
	
	 
}
