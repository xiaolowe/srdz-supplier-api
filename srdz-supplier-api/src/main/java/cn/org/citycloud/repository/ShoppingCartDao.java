package cn.org.citycloud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.ShoppingCart;



public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer>, JpaSpecificationExecutor<ShoppingCart> {

	List<ShoppingCart> findByGoodsId(int goodsId);
	
}
