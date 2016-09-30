package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.org.citycloud.entity.Goods;



public interface GoodsDao extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor<Goods> {

	
	
}
