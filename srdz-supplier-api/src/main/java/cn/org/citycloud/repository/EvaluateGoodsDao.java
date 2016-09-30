package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.EvaluateGoods;



public interface EvaluateGoodsDao extends JpaRepository<EvaluateGoods, Integer>, JpaSpecificationExecutor<EvaluateGoods> {

	
	
}
