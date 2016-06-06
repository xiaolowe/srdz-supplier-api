package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.DistGoods;



public interface DistGoodsDao extends JpaRepository<DistGoods, Integer>, JpaSpecificationExecutor<DistGoods> {

	
	
}
