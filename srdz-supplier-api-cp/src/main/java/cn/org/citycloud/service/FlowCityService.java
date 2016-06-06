package cn.org.citycloud.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.org.citycloud.entity.FlowCity;
import cn.org.citycloud.repository.FlowCityDao;


/**
 * @author Allen
 *
 */
@Component
@Transactional
public class FlowCityService {

	@Autowired
	private FlowCityDao flowCityDao;
	
	
	
	public void removeFlowCity(int flowInfoId){
		
	     List<FlowCity> list = flowCityDao.findByFlowInfoId(flowInfoId);
	     if(list != null && list.size() > 0){
	    	 for(int i = 0; i < list.size(); i++){
	    		 FlowCity itp = list.get(i);
	    		 flowCityDao.delete(itp);
	    	 }
	     }
	}
	
}
