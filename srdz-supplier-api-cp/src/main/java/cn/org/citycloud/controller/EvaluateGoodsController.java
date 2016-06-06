package cn.org.citycloud.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.EvaluateGoods;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.EvaluateGoodsDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 评价商品接口
 * @author Allen
 *
 */
@RestController
@Api(tags="评价商品", value = "/evaluategoods",  description = "评价商品接口", consumes="application/json")
public class EvaluateGoodsController extends BaseController{

	@Autowired
	private EvaluateGoodsDao evaluateGoodsDao;
	


	/**
	 * 评价商品列表
	 * @return
	 */
	@RequestMapping(value = "/evaluategoods", method = RequestMethod.GET)
	@ApiOperation(value = "评价商品列表", notes = "评价商品列表信息")
	public Object getStadiumList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "开始时间", required = false) @RequestParam(value = "startTime", required = false)  String startTime,
			@ApiParam(value = "结束时间", required = false) @RequestParam(value = "endTime", required = false)  String endTime,
			@ApiParam(value = "商品名称", required = false) @RequestParam(value = "goodsName", required = false)  String goodsName) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "gevalId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<EvaluateGoods> page = evaluateGoodsDao.findAll(new Specification<EvaluateGoods>() {
			@Override
			public Predicate toPredicate(Root<EvaluateGoods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				if(supplierId > 0){
					Path<String> supplierIdQuery = root.get("supplierId");  
					where = cb.and(where, cb.equal(supplierIdQuery, supplierId));
					
				}
				if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
					Path<Date> addTime = root.get("gevalAddtime"); 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = new Date();
					Date end = new Date();
					String startTime1 = startTime + " 00:00:00";
					String endTime1 = endTime + " 23:59:59";
					try {
						start = sdf.parse(startTime1);
						end = sdf.parse(endTime1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					where = cb.and(where, cb.between(addTime, start, end)); 
					
				}
				if(StringUtils.isNotBlank(goodsName)){
					Path<String> goodsNameQuery = root.get("goodsName");  
					where = cb.and(where, cb.like(goodsNameQuery, "%"+goodsName+"%"));
					
				}
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}

	/**
	 * 屏蔽/取消屏蔽 评论
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/evaluategoods/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "屏蔽/取消屏蔽 评论", notes = "屏蔽/取消屏蔽 评论")
	public Object modifyGoods(@ApiParam(value = "评论Id", required = true) @PathVariable int id,
			@ApiParam(value = "评论状态（0  正常     1  屏蔽 ）", required = true) @RequestParam(value = "gevalStatus")  int gevalStatus) throws BusinessErrorException {
		
		
		EvaluateGoods evaluateGood = evaluateGoodsDao.findOne(id);
		
		evaluateGood.setGevalStatus(gevalStatus);		
		 
		return evaluateGoodsDao.save(evaluateGood);
		
		
	}
	
	/**
	 * 回复评论
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/replyevaluate/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "回复评论", notes = "回复评论")
	public Object replyEvaluate(@ApiParam(value = "评论Id", required = true) @PathVariable int id,
			@ApiParam(value = "回复内容", required = true) @RequestParam(value = "replyContent")  String replyContent) throws BusinessErrorException {
		
		
		EvaluateGoods evaluateGood = evaluateGoodsDao.findOne(id);
		
		evaluateGood.setReplyContent(replyContent);
		 
		return evaluateGoodsDao.save(evaluateGood);
		
		
	}
	
}
