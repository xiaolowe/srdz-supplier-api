package cn.org.citycloud.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.org.citycloud.bean.IndexOrderBean;
import cn.org.citycloud.entity.Order;
import cn.org.citycloud.repository.OrderDao;

@Component
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@PersistenceContext
    private EntityManager em;
	
	/**
	 * 动态查询订单列表
	 * @param store
	 * @return
	 */
	public List<Order> getOrderList(IndexOrderBean orderBean){
		
		List<Order> list = orderDao.findAll(new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				
				Path<Integer> supplierId = root.get("supplierId");
				where = cb.and(where,cb.equal(supplierId, orderBean.getSupplierId()));
					
				if(orderBean.getStartTime().length()>0&&orderBean.getEndTime().length()>0){
					Path<Date> orderTime = root.get("orderTime");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date stime=new Date();
					Date etime=new Date();
					try {
						stime = df.parse(orderBean.getStartTime()+" 00:00:00");
						etime = df.parse(orderBean.getEndTime()+" 23:59:59");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
					}
					
					where = cb.and(where,cb.between(orderTime, stime, etime));
					
				}
				query.where(where);
				  
			    return null;   
			}
		});
		
	    return list;
	}
	
	
	/**
	 * 动态查找列表
	 * @param order
	 * @param startime
	 * @param endtime
	 * @return
	 */
	public List<Order> getExcelOrderList(Order order, String startime, String endtime){
		
		return orderDao.findAll(new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate where = cb.conjunction(); 
				
				Path<String> supplierId = root.get("supplierId");  
				where = cb.and(where, cb.equal(supplierId, order.getSupplierId()));
					
				if(order != null && StringUtils.isNotBlank(startime) && StringUtils.isNotBlank(endtime)){
					Path<Date> addTime = root.get("orderTime"); 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = new Date();
					Date end = new Date();
					String startime1 = startime + " 00:00:00";
					String endtime1 = endtime + " 23:59:59";
					try {
						start = sdf.parse(startime1);
						end = sdf.parse(endtime1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					where = cb.and(where, cb.between(addTime, start, end)); 
					
				}
				if(order != null &&  StringUtils.isNotBlank(order.getMemberPhone())){
					Path<String> memberPhone = root.get("memberPhone");  
					where = cb.and(where, cb.like(memberPhone, "%"+order.getMemberPhone()+"%"));
				}
				if(order != null && order.getOrderId() > -1 ){
					Path<String> orderId = root.get("orderId");  
					where = cb.and(where, cb.equal(orderId, order.getOrderId()));
				}
				if(order != null && order.getOrderStatus() > -1 ){
					Path<String> orderStatus = root.get("orderStatus");  
					where = cb.and(where, cb.equal(orderStatus, order.getOrderStatus()));
				}
				if(order != null && order.getBackOrderStatus() > -1 ){
					Path<String> backOrderStatus = root.get("backOrderStatus");  
					where = cb.and(where, cb.equal(backOrderStatus, order.getBackOrderStatus()));
				}
				query.where(where);
				  
			    return null;   
			}
		});
	}
	
}
