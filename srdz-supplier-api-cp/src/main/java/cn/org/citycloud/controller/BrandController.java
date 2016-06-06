package cn.org.citycloud.controller;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.Brand;
import cn.org.citycloud.repository.BrandDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 品牌接口
 * @author Allen
 *
 */
@RestController
@Api(tags="品牌", value = "/brand",  description = "品牌接口", consumes="application/json")
public class BrandController extends BaseController{

	@Autowired
	private BrandDao brandDao;
	
	
	/**
	 * 品牌列表
	 * @return
	 */
	@RequestMapping(value = "/brand", method = RequestMethod.GET)
	@ApiOperation(value = "品牌列表", notes = "品牌列表信息")
	public Object getStadiumList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "品牌名称", required = false) @RequestParam(value = "brandName", required = false)  String brandName) throws RuntimeException, Exception {

		Sort sort = new Sort(Direction.DESC, "brandId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<Brand> page = brandDao.findAll(new Specification<Brand>() {
			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				if(StringUtils.isNotBlank(brandName)){
					Path<String> brandNameQuery = root.get("brandName");  
					where = cb.and(where, cb.like(brandNameQuery, "%"+brandName+"%"));
					
				}
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}
	
	
	/**
	 * 查看品牌列表
	 * @param bean
	 */
	@RequestMapping(value = "/brandall", method = RequestMethod.GET)
	@ApiOperation(value = "查看品牌列表", notes = "查看品牌列表")
	public Object getBrankList() {
		
		List<Brand> list = brandDao.findAll();

		return list;
		
	}

	

}
