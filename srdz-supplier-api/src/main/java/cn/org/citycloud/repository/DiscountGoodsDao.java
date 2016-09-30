package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.DiscountGoods;



public interface DiscountGoodsDao extends JpaRepository<DiscountGoods, Integer>, JpaSpecificationExecutor<DiscountGoods> {

	
	
}
