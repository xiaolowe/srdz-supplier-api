package cn.org.citycloud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.FlowProvince;



public interface FlowProvinceDao extends JpaRepository<FlowProvince, Integer>, JpaSpecificationExecutor<FlowProvince> {

	List<FlowProvince> findByFlowInfoId(int flowInfoId);
}
