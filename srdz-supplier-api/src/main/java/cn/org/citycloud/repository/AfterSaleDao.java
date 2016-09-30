package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.AfterSale;



public interface AfterSaleDao extends JpaRepository<AfterSale, Integer>, JpaSpecificationExecutor<AfterSale> {

	AfterSale findByOrderId(int orderId);
	
}
