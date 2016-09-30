package cn.org.citycloud.repository;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.org.citycloud.entity.Order;



public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

	
	@Query(value="select count(1) from orders o where date(o.order_time) = curdate() and o.supplier_id=?", nativeQuery = true)
	long findTodayOrder(int supplierId);
	
	@Query(value="select sum(o.pay_price) payPrice from orders o where date(o.order_time) = curdate() and o.supplier_id=? and o.order_status >10", nativeQuery = true)
	BigDecimal findTodayOrderMoney(int supplierId);
}
