package cn.org.citycloud.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.org.citycloud.entity.FlowInfo;
import cn.org.citycloud.repository.FlowInfoDao;


/**
 * @author Allen
 *
 */
@Component
@Transactional
public class FlowInfoService {

	@Autowired
	private FlowInfoDao flowInfoDao;
	
	
	
	public void removeFlowInfo(int templateId){
		
	     List<FlowInfo> list = flowInfoDao.findByFlowTemplateId(templateId);
	     if(list != null && list.size() > 0){
	    	 for(int i = 0; i < list.size(); i++){
	    		 FlowInfo itp = list.get(i);
	    		 flowInfoDao.delete(itp);
	    	 }
	     }
	}
	
}
