package cn.org.citycloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.org.citycloud.entity.RegionInfo;
import cn.org.citycloud.repository.RegionInfoDao;
/**
 * @author Allen
 *
 */
@Component
@Transactional
public class RegionInfoService {

	@Autowired
	private RegionInfoDao regionInfoDao;
	
	
	public List<RegionInfo> getRegionProvince(){
		
		return regionInfoDao.findByRegionLevel(1);		//省默认类型是 1
	}
	
	public List<RegionInfo> getRegionCity(Integer regionCode){
		
		return regionInfoDao.findCityOrAreaRegionCode(regionCode, 2);
	}
	
	public List<RegionInfo> getRegionArea(Integer regionCode){
		
		return regionInfoDao.findCityOrAreaRegionCode(regionCode, 3);
	}
	
	
	
}
