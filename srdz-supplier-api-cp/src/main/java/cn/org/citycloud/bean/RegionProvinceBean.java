package cn.org.citycloud.bean;

import java.util.ArrayList;
import java.util.List;

import cn.org.citycloud.entity.RegionInfo;

public class RegionProvinceBean {
	
	
	private Integer regionCode;
	
	private Integer regionLevel;
	
	private String regionName;
	
	private List<RegionInfo> city = new ArrayList<RegionInfo>();

	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<RegionInfo> getCity() {
		return city;
	}

	public void setCity(List<RegionInfo> city) {
		this.city = city;
	}

	
	
	

	
}
