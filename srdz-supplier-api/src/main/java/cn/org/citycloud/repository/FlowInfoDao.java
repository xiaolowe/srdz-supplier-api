package cn.org.citycloud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.FlowInfo;



public interface FlowInfoDao extends JpaRepository<FlowInfo, Integer>, JpaSpecificationExecutor<FlowInfo> {

	 List<FlowInfo> findByFlowTemplateId(int flowTemplateId);
	
}
