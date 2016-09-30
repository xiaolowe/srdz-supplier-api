package cn.org.citycloud.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.RegionProvinceBean;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.RegionInfo;
import cn.org.citycloud.service.RegionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 区域接口
 * @author Allen
 */

@RestController
@Api(tags="区域地址", value = "/regioninfo",  description = "省市区 区域接口", consumes="application/json")
public class RegionInfoController extends BaseController{

	@Autowired
	private RegionInfoService regionInfoService;


	/**
	 * 获取所有省
	 * @return
	 */
	@RequestMapping(value = "/regioninfo/getProvince", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有省", notes = "获取所有省列表", response = RegionInfo.class, responseContainer = "List")
	public Object getProvince() throws Exception, RuntimeException {
		
		List<RegionInfo> list = regionInfoService.getRegionProvince();

		return list;
	}
	
	/**
	 * 获取所有市
	 * @return
	 */
	@RequestMapping(value = "/regioninfo/getCity", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有市", notes = "获取所有市列表", response = RegionInfo.class, responseContainer = "List")
	public Object getCity(@RequestParam("region_code") String regionCode ) throws Exception, RuntimeException {
		
		String code = regionCode.substring(0, 2);
		
		List<RegionInfo> list = regionInfoService.getRegionCity(Integer.valueOf(code));

		return list;
	}
	
	/**
	 * 获取所有区县
	 * @return
	 */
	@RequestMapping(value = "/regioninfo/getArea", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有区", notes = "获取所有区列表", response = RegionInfo.class, responseContainer = "List")
	public Object getArea(@RequestParam("region_code") String regionCode ) throws Exception, RuntimeException {
		
		String code = regionCode.substring(0, 4);
		
		List<RegionInfo> list = regionInfoService.getRegionArea(Integer.valueOf(code));

		return list;
	}
	

	/**
	 * 获取所有省市
	 * @return
	 */
	@RequestMapping(value = "/regioninfo/getProvinceCity", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有省市", notes = "获取所有省市列表", response = RegionInfo.class, responseContainer = "List")
	public Object getProvinceCity() throws Exception, RuntimeException {
		
		List<RegionInfo> listP = regionInfoService.getRegionProvince();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i < listP.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			RegionInfo regionInfo = listP.get(i);
			String code = String.valueOf(regionInfo.getRegionCode()).substring(0, 2);
			List<RegionInfo> listC = regionInfoService.getRegionCity(Integer.valueOf(code));
			RegionProvinceBean province = new RegionProvinceBean();
			province.setRegionCode(regionInfo.getRegionCode());
			province.setRegionLevel(regionInfo.getRegionLevel());
			province.setRegionName(regionInfo.getRegionName());
			province.setCity(listC);
			map.put("province", province);
			list.add(map);
		}

		return list;
	}
	

}
