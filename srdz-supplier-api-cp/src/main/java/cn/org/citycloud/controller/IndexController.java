package cn.org.citycloud.controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.IndexOrderBean;
import cn.org.citycloud.bean.chat.ChartData;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.Order;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.OrderDao;
import cn.org.citycloud.repository.PayInfoDao;
import cn.org.citycloud.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 首页
 * @author allen
 *
 */
@RestController
@Api(tags = "首页", value = "/index", description = "供应商首页", consumes = "application/json")
public class IndexController extends BaseController{
	
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderService orderService;
	

	/**
	 * 今日交易额
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/todayincome", method = RequestMethod.GET)
	@ApiOperation(value = "今日交易额", notes = "今日交易额")
	public Object getIncome() throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		BigDecimal money = orderDao.findTodayOrderMoney(supplierId);
		if(money == null){
			return 0;
		}
		
		return money;
		
	}
	
	/**
	 * 今日订单数
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/todayorder", method = RequestMethod.GET)
	@ApiOperation(value = "今日订单数", notes = "今日订单数")
	public Object getTodayOrder() throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		return orderDao.findTodayOrder(supplierId);
		
	}
	
	
	@RequestMapping(value="/chart",method=RequestMethod.GET)
	@ApiOperation(value = "首页图表数据", notes = "首页图标数据")
	public Object getChart() throws BusinessErrorException{
		
		int supplierId = getSupplierId();
		
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ChartData xAxis = getxaxis();
		resultMap.put("xAxis", xAxis);
		
		List<ChartData> series = new ArrayList<ChartData>();
		//获取图表数据
		IndexOrderBean orderBean = new IndexOrderBean();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
		Date now = new Date();
		orderBean.setEndTime(df.format(now));
		Calendar cl = Calendar.getInstance();
		cl.setTime(now);
		cl.add(Calendar.DAY_OF_MONTH, -6);
		orderBean.setStartTime(df.format(cl.getTime()));
		orderBean.setSupplierId(supplierId);
		
		List<Order> orders = orderService.getOrderList(orderBean);
		//end
		ChartData ordersData = new ChartData();
		ChartData amounts = new ChartData();
		ordersData.setName("订单数");
		ordersData.setType("line");
		amounts.setName("成交额");
		amounts.setType("line");
		List<Object> orderList = new ArrayList<Object>();
		List<Object> amountList = new ArrayList<Object>();
		List<Date> startTime = getStartData();
		List<Date> endTime = getEndData();
		for(int i=0;i<startTime.size();i++){
			int size=0;
			BigDecimal price = new BigDecimal(0);
			for(Order order:orders){
				Date orderTime = order.getOrderTime();
				if(orderTime.after(startTime.get(i))&&orderTime.before(endTime.get(i))){
					size++;
					if(order.getOrderStatus()>10){
						price = price.add(order.getPayPrice());
					}
					
				}
			}
			orderList.add(size);
			amountList.add(price);
		}
		ordersData.setData(orderList);
		amounts.setData(amountList);
		series.add(ordersData);
		series.add(amounts);
		resultMap.put("series", series);
		return resultMap;
		
	}
	
	
	private ChartData getxaxis(){
		List<Object> datas = new ArrayList<Object>();
		ChartData xAxis = new ChartData();
		xAxis.setName("x");
		xAxis.setType("category");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
		Date now = new Date();
		datas.add(df.format(now));
		Calendar cl = Calendar.getInstance();
		cl.setTime(now);
		for(int i=1;i<7;i++){
			cl.add(Calendar.DAY_OF_MONTH, -1);
			datas.add(df.format(cl.getTime()));
		}
		List<Object> dst = reverseOb(datas);
		xAxis.setData(dst);
		return xAxis;
	}
	
	private List<Date> getStartData(){
		List<Date> dates = new ArrayList<Date>();
		Date now = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(now);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		dates.add(cl.getTime());
		for(int i=1;i<7;i++){
			cl.add(Calendar.DAY_OF_MONTH, -1);
			dates.add(cl.getTime());
		}
		List<Date> dst = reverse(dates);
		return dst;
	}
	
	private List<Date> getEndData(){
		List<Date> dates = new ArrayList<Date>();
		Date now = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(now);
		cl.set(Calendar.HOUR_OF_DAY, 23);
		cl.set(Calendar.MINUTE, 59);
		cl.set(Calendar.SECOND, 59);
		dates.add(cl.getTime());
		for(int i=1;i<7;i++){
			cl.add(Calendar.DAY_OF_MONTH, -1);
			dates.add(cl.getTime());
		}
		List<Date> dst = reverse(dates);
		return dst;
	}
	
	private List<Date> reverse(List<Date> source){
		List<Date> dest = new ArrayList<Date>();
		for(int i=0;i<source.size();i++){
			int k = source.size()-1-i;
			dest.add(source.get(k));
		}
		return dest;
	}
	
	private List<Object> reverseOb(List<Object> source){
		List<Object> dest = new ArrayList<Object>();
		for(int i=0;i<source.size();i++){
			int k = source.size()-1-i;
			dest.add(source.get(k));
		}
		return dest;
	}
	
}
