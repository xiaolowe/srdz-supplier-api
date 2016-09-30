package cn.org.citycloud.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.org.citycloud.entity.FlowCity;
import cn.org.citycloud.entity.GoodsBanner;
import cn.org.citycloud.repository.GoodsBannerDao;


/**
 * @author Allen
 *
 */
@Component
@Transactional
public class GoodsBannerService {

	@Autowired
	private GoodsBannerDao goodsBannerDao;
	
	
	
	public void removeGoodsBanner(int goodsId){
		
	     List<GoodsBanner> list = goodsBannerDao.findByGoodsId(goodsId);
	     if(list != null && list.size() > 0){
	    	 for(int i = 0; i < list.size(); i++){
	    		 GoodsBanner goodsBanner = list.get(i);
	    		 goodsBannerDao.delete(goodsBanner);
	    	 }
	     }
	}
	
}
