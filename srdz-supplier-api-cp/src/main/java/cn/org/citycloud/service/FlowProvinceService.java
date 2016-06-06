package cn.org.citycloud.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.org.citycloud.entity.FlowProvince;
import cn.org.citycloud.repository.FlowProvinceDao;


/**
 * @author Allen
 *
 */
@Component
@Transactional
public class FlowProvinceService {

	@Autowired
	private FlowProvinceDao flowProvinceDao;
	
	
	
	public void removeFlowProvince(int flowInfoId){
		
	     List<FlowProvince> list = flowProvinceDao.findByFlowInfoId(flowInfoId);
	     if(list != null && list.size() > 0){
	    	 for(int i = 0; i < list.size(); i++){
	    		 FlowProvince itp = list.get(i);
	    		 flowProvinceDao.delete(itp);
	    	 }
	     }
	}
	
}
