package cn.org.citycloud.controller;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.DiscountGoodsBean;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.DiscountGoods;
import cn.org.citycloud.entity.Goods;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.DiscountGoodsDao;
import cn.org.citycloud.repository.GoodsDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 特惠商品接口
 * @author Allen
 *
 */
@RestController
@Api(tags="特惠商品", value = "/discountgoods",  description = "特惠商品接口", consumes="application/json")
public class DiscountGoodsController extends BaseController{

	 @Autowired
	 private GoodsDao goodsDao;
	
	 @Autowired
	 private DiscountGoodsDao discountGoodsDao;
	

	/**
	 * 特惠商品列表
	 * @return
	 */
	@RequestMapping(value = "/discountgoods", method = RequestMethod.GET)
	@ApiOperation(value = "特惠商品列表", notes = "特惠商品列表信息")
	public Object getStadiumList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "商品名称", required = false) @RequestParam(value = "goodsName", required = false)  String goodsName ) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "goodsId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<DiscountGoods> page = discountGoodsDao.findAll(new Specification<DiscountGoods>() {
			@Override
			public Predicate toPredicate(Root<DiscountGoods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				
				Path<String> supplierIdQuery = root.get("supplierId");  
				where = cb.and(where, cb.equal(supplierIdQuery, supplierId));
				
				if(StringUtils.isNotBlank(goodsName)){
					Path<String> goodsNameQuery = root.get("goods").get("goodsName");  
					where = cb.and(where, cb.like(goodsNameQuery, "%"+goodsName+"%"));
					
				}
				
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}
	
	 @RequestMapping(value={"/discountgoods/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	 @ApiOperation(value="特惠商品上架", notes="特惠商品上架")
	 public Object modifyGoods(@ApiParam(value="商品Id", required=true) @PathVariable int id, @ApiParam(value="特惠商品上架", required=true) @Valid @RequestBody DiscountGoodsBean bean) throws BusinessErrorException{
		 
		 int supplierId = getSupplierId();
		 
		 if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		 }
		 
		 Goods goods = goodsDao.findOne(id);
	     goods.setDiscountFlg((byte)1);
	     goods.setGoodsStatus(0);		//商品状态（ 0 待上架  1 上架   2  下架  10 禁售）
	     
	     goodsDao.save(goods);
			
	     DiscountGoods discountGoods = discountGoodsDao.findOne(id);
	    

	    if(discountGoods == null){
	    	discountGoods = new DiscountGoods();
	    }
	    BeanUtils.copyProperties(bean, discountGoods);
	    
	    discountGoods.setGoodsId(id);
	    discountGoods.setSupplierId(supplierId);
	    discountGoods.setSurplus(bean.getGoodsAddCount());
	    Date now = new Date();
	    discountGoods.setCreateTime(now);
	    discountGoods.setUpdateTime(now);
	    return discountGoodsDao.save(discountGoods);
	  }

}
