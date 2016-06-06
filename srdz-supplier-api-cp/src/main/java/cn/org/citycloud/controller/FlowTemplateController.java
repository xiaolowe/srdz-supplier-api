package cn.org.citycloud.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.org.citycloud.bean.FlowCityBean;
import cn.org.citycloud.bean.FlowInfoBean;
import cn.org.citycloud.bean.FlowProvinceBean;
import cn.org.citycloud.bean.FlowTemplateListBean;
import cn.org.citycloud.bean.SupplierAddressBean;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.FlowCity;
import cn.org.citycloud.entity.FlowInfo;
import cn.org.citycloud.entity.FlowProvince;
import cn.org.citycloud.entity.FlowTemplate;
import cn.org.citycloud.entity.Supplier;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.FlowCityDao;
import cn.org.citycloud.repository.FlowInfoDao;
import cn.org.citycloud.repository.FlowProvinceDao;
import cn.org.citycloud.repository.FlowTemplateDao;
import cn.org.citycloud.repository.SupplierDao;
import cn.org.citycloud.service.FlowCityService;
import cn.org.citycloud.service.FlowInfoService;
import cn.org.citycloud.service.FlowProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物流模板接口
 * @author Allen
 *
 */
@RestController
@Api(tags="物流模板", value = "/flowtemplate",  description = "物流模板接口", consumes="application/json")
public class FlowTemplateController extends BaseController{

	@Autowired
	private FlowTemplateDao flowTemplateDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private FlowCityDao flowCityDao;
	@Autowired
	private FlowProvinceDao flowProvinceDao;
	@Autowired
	private FlowInfoDao flowInfoDao;
	@Autowired
	private FlowInfoService flowInfoService;
	@Autowired
	private FlowCityService flowCityService;
	@Autowired
	private FlowProvinceService flowPovinceService;
	

	
	/**
	 * 添加物流模板
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/flowtemplate", method = RequestMethod.POST)
	@ApiOperation(value = "添加物流模板", notes = "添加物流模板")
	public Object addFlowTemplate( @Valid @RequestBody FlowTemplateListBean bean) throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		List<FlowInfo> listadd = new ArrayList<FlowInfo>();
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		Date now = new Date();
		
		//添加模板
		FlowTemplate df = flowTemplateDao.findOne(bean.getFlowTemplate().getFlowTemplateId());
		if(df != null){
			df.setSupplierId(supplierId);
			df.setCreateTime(now);
			df.setUpdateTime(now);
		}else{
			df = new FlowTemplate();
			df.setSupplierId(supplierId);
			df.setCreateTime(now);
			df.setUpdateTime(now);
		}
		BeanUtils.copyProperties(bean.getFlowTemplate(), df);
		
		FlowTemplate ftadd = flowTemplateDao.save(df);
		map.put("flowtemplate", ftadd);
		
		List<FlowInfoBean> listBeanFlowInfo = bean.getFlowInfolist();
		
		
		List<FlowInfo> listFlowInfo = flowInfoDao.findByFlowTemplateId(ftadd.getFlowTemplateId());
		//删除物流城市信息
		if(listFlowInfo != null && listFlowInfo.size() > 0){
			for(int i = 0; i < listFlowInfo.size(); i++){
				FlowInfo flowInfo = listFlowInfo.get(i);
				flowCityService.removeFlowCity(flowInfo.getFlowInfoId());
			}
		}
		//删除省信息
		if(listFlowInfo != null && listFlowInfo.size() > 0){
			for(int i = 0; i < listFlowInfo.size(); i++){
				FlowInfo flowInfo = listFlowInfo.get(i);
				flowPovinceService.removeFlowProvince(flowInfo.getFlowInfoId());
			}
		}
		
		//删除物流信息
		flowInfoService.removeFlowInfo(ftadd.getFlowTemplateId());
		
		//添加默认物流信息
		FlowInfo fi = new FlowInfo();
		BeanUtils.copyProperties(bean.getFlowInfo(), fi);
		
		fi.setFlowTemplateId(ftadd.getFlowTemplateId());
		fi.setCreateTime(now);
		fi.setUpdateTime(now);
		
		FlowInfo fiadd = flowInfoDao.save(fi);
		map.put("flowinfo", fiadd);
		
		if(listBeanFlowInfo != null && listBeanFlowInfo.size() > 0){
			
			for(int i = 0; i < listBeanFlowInfo.size(); i++){
				FlowInfoBean flowInfoBean = listBeanFlowInfo.get(i);
				//添加物流信息
				FlowInfo flowInfo = new FlowInfo();
				flowInfo.setFlowTemplateId(ftadd.getFlowTemplateId());
				flowInfo.setFlowGoods(flowInfoBean.getFlowGoods());
				flowInfo.setFlowPrice(flowInfoBean.getFlowPrice());
				flowInfo.setAddFlowGoods(flowInfoBean.getAddFlowGoods());
				flowInfo.setAddGoodsPrice(flowInfoBean.getAddGoodsPrice());
				flowInfo.setCreateTime(now);
				flowInfo.setUpdateTime(now);
				//BeanUtils.copyProperties(bean, flowTemplate);
				
				FlowInfo flowInfo2 = flowInfoDao.save(flowInfo);
				
				List<FlowCityBean> listC = flowInfoBean.getFlowCityList();
				List<FlowProvinceBean> listP = flowInfoBean.getProvince();
				
				// 添加城市信息
				if(listC != null && listC.size() > 0){
					for(int j = 0; j < listC.size(); j++){
					  FlowCity flowCity = new FlowCity();
					  FlowCityBean fb = listC.get(j);
					  flowCity.setFlowInfoId(flowInfo2.getFlowInfoId());
					  flowCity.setFlowCityName(fb.getFlowCityName());
					  flowCity.setFlowCityCode(fb.getFlowCityCode());
					  flowCityDao.save(flowCity);
					}
				}
				
				if(listP != null && listP.size() > 0){
					for(int j = 0; j < listP.size(); j++){
					  FlowProvince flowProvince = new FlowProvince();
					  FlowProvinceBean fb = listP.get(j);
					  flowProvince.setFlowInfoId(flowInfo2.getFlowInfoId());
					  flowProvince.setRegionName(fb.getRegionName());
					  flowProvince.setRegionCode(fb.getRegionCode());
					  flowProvinceDao.save(flowProvince);
					}
				}
				
				listadd.add(flowInfo2);
				
				
				
			}
			return listadd;
		}
		
		return map;
		
		
	}
	
	/**
	 * 物流模板信息
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/flowtemplate", method = RequestMethod.GET)
	@ApiOperation(value = "物流模板信息", notes = "物流模板信息")
	public Object flowTemplateInfo() throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		
		List<FlowTemplate> listft = flowTemplateDao.findBySupplierId(supplierId);
		
		
		return listft;
		
		
	}
	
	/**
	 * 物流模板列表
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/choicetemplate", method = RequestMethod.GET)
	@ApiOperation(value = "物流模板列表", notes = "物流模板列表")
	public Object flowTemplateList() throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		List<FlowTemplate> listft = flowTemplateDao.findBySupplierId(supplierId);
		
		
		return listft;
		
		
	}
	

	/**
	 * 修改送货地址
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/supplieraddress", method = RequestMethod.PUT)
	@ApiOperation(value = "修改送货信息", notes = "修改送货信息")
	public Object modifyAddress( @Valid @RequestBody SupplierAddressBean bean) throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		Supplier supplier = supplierDao.findOne(supplierId);
		
		BeanUtils.copyProperties(bean, supplier);
		
		
		
		return supplierDao.save(supplier);
		
		
	}
	
	
}
