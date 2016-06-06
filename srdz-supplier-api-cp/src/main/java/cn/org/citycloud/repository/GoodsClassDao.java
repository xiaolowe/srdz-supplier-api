package cn.org.citycloud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.GoodsClass;



public interface GoodsClassDao extends JpaRepository<GoodsClass, Integer>, JpaSpecificationExecutor<GoodsClass> {

	List<GoodsClass> findByParentId(int parentId);
	
}
