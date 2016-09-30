package cn.org.citycloud.controller;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

import cn.org.citycloud.bean.GoodsBannerBean;
import cn.org.citycloud.bean.GoodsBean;
import cn.org.citycloud.bean.GoodsModifyBean;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.DistGoods;
import cn.org.citycloud.entity.Goods;
import cn.org.citycloud.entity.GoodsBanner;
import cn.org.citycloud.entity.ShoppingCart;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.DistGoodsDao;
import cn.org.citycloud.repository.GoodsBannerDao;
import cn.org.citycloud.repository.GoodsDao;
import cn.org.citycloud.repository.ShoppingCartDao;
import cn.org.citycloud.service.GoodsBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 商品接口
 * @author Allen
 *
 */
@RestController
@Api(tags="商品模块", value = "/goods",  description = "商品接口", consumes="application/json")
public class GoodsController extends BaseController{

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private DistGoodsDao distGoodsDao;
	@Autowired
	private GoodsBannerDao goodsBannerDao;
	@Autowired
	private GoodsBannerService goodsBannerService;
	@Autowired
	private ShoppingCartDao shoppingCartDao;

	
	/**
	 * 商品列表
	 * @return
	 */
	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	@ApiOperation(value = "商品列表", notes = "商品列表信息")
	public Object getStadiumList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "商品分类ID", required = false) @RequestParam(value = "goodsClassId", defaultValue = "0", required = false)  int goodsClassId,
			@ApiParam(value = "商品二级分类ID", required = false) @RequestParam(value = "goodsClassTwoId", required = false, defaultValue = "0")  int goodsClassTwoId,
			@ApiParam(value = "商品三级分类ID", required = false) @RequestParam(value = "goodsClassThreeId", required = false, defaultValue = "0")  int goodsClassThreeId,
			@ApiParam(value = "商品名称", required = false) @RequestParam(value = "goodsName", required = false)  String goodsName,
			@ApiParam(value = "商品状态（ 0 正常  1 上架   2  下架  10 禁售）", required = false) @RequestParam(value = "goodsStatus", defaultValue = "-1", required = false) int goodsStatus,
			@ApiParam(value = "是否特惠商品（ 1  特惠     0 普通）", required = false) @RequestParam(value = "discountFlg", defaultValue = "-1", required = false) int discountFlg) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "goodsId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<Goods> page = goodsDao.findAll(new Specification<Goods>() {
			@Override
			public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				if(supplierId > 0){
					Path<String> supplierIdQuery = root.get("supplierId");  
					where = cb.and(where, cb.equal(supplierIdQuery, supplierId));
					
				}
				
				if(StringUtils.isNotBlank(goodsName)){
					Path<String> goodsNameQuery = root.get("goodsName");  
					where = cb.and(where, cb.like(goodsNameQuery, "%"+goodsName+"%"));
					
				}
				if(goodsClassId > 0){
					Path<String> goodsClassIdQuery = root.get("goodsClassId");  
					where = cb.and(where, cb.equal(goodsClassIdQuery, goodsClassId));
					
				}
				if(goodsClassTwoId > 0){
					Path<String> goodsClassTwoIdQuery = root.get("goodsClassTwoId");  
					where = cb.and(where, cb.equal(goodsClassTwoIdQuery, goodsClassTwoId));
					
				}
				if(goodsClassThreeId > 0){
					Path<String> goodsClassThreeIdQuery = root.get("goodsClassThreeId");  
					where = cb.and(where, cb.equal(goodsClassThreeIdQuery, goodsClassThreeId));
					
				}
				if(goodsStatus > -1){
					Path<String> goodsStatusQuery = root.get("goodsStatus");  
					where = cb.and(where, cb.equal(goodsStatusQuery, goodsStatus));
					
				}
				if(discountFlg > -1){
					Path<String> discountFlgQuery = root.get("discountFlg");  
					where = cb.and(where, cb.equal(discountFlgQuery, (byte)discountFlg));
					
				}
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}
	
	/**
	 * 上架商品信息
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/goods", method = RequestMethod.POST)
	@ApiOperation(value = "添加商品", notes = "添加商品")
	public Object addGoods(@ApiParam(value = "添加商品信息", required = true) @Valid @RequestBody GoodsBean bean) throws BusinessErrorException {
		
		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Goods goods = new Goods();
		
		BeanUtils.copyProperties(bean, goods);
		Date gnow = new Date();
		goods.setCreateTime(gnow);
		goods.setUpdateTime(gnow);
		goods.setGoodsAddtime(gnow);
		goods.setSupplierId(supplierId);
		goods.setSurplus(bean.getGoodsAddCount());
		Goods ga = goodsDao.save(goods);
		
		DistGoods distGoods = distGoodsDao.findOne(ga.getGoodsId());
		if(distGoods == null){
			distGoods = new DistGoods();
		}
		if(bean.getSalerFlg() == 1){
			
			distGoods.setGoodsId(ga.getGoodsId());
			distGoods.setRangeHigh(bean.getRangeHigh());
			distGoods.setRangeLow(bean.getRangeLow());
			
			Date now = new Date();
			distGoods.setCreateTime(now);
			distGoodsDao.save(distGoods);
		}
		List<GoodsBannerBean> listBanner = bean.getGoodsBannerList();

		if(listBanner != null && listBanner.size() > 0){
			for(int i = 0; i < listBanner.size(); i++){
				GoodsBannerBean goodsBannerBean = listBanner.get(i);
				GoodsBanner  goodsBanner = new GoodsBanner();
				goodsBanner.setGoodsId(ga.getGoodsId());
				goodsBanner.setBannerImage(goodsBannerBean.getBannerImage());
				goodsBannerDao.save(goodsBanner);
			}
		}
		
		
		return goodsDao.save(goods);
		
		
	}
	
	/**
	 * 修改商品信息
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/goods/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "修改商品", notes = "修改商品")
	public Object modifyGoods(@ApiParam(value = "商品Id", required = true) @PathVariable int id, @ApiParam(value = "修改商品信息", required = true) @Valid @RequestBody GoodsModifyBean bean) throws BusinessErrorException {
		
		Goods goods = goodsDao.findOne(id);
		
		BeanUtils.copyProperties(bean, goods);

		// 根据商品的上架数及已售出的数量计算库存
		goods.setSurplus(goods.getGoodsAddCount() - goods.getAlreadySale());

		DistGoods distGoods = distGoodsDao.findOne(id);
		
		if(bean.getSalerFlg() == 1){
			
			if(distGoods == null){
				distGoods = new DistGoods();
			}
			distGoods.setGoodsId(id);
			distGoods.setRangeHigh(bean.getRangeHigh());
			distGoods.setRangeLow(bean.getRangeLow());
			
			Date now = new Date();
			distGoods.setCreateTime(now);
			distGoodsDao.save(distGoods);
		}
		Date now = new Date();
		goods.setUpdateTime(now);
		
		List<GoodsBannerBean> listBanner = bean.getGoodsBannerList();

		if(listBanner != null && listBanner.size() > 0){
			goodsBannerService.removeGoodsBanner(id);
			
			for(int i = 0; i < listBanner.size(); i++){
				GoodsBannerBean goodsBannerBean = listBanner.get(i);
				GoodsBanner  goodsBanner = new GoodsBanner();
				goodsBanner.setGoodsId(id);
				goodsBanner.setBannerImage(goodsBannerBean.getBannerImage());
				goodsBannerDao.save(goodsBanner);
			}
		}
		//修改商品购物车价格
		List<ShoppingCart> listCart = shoppingCartDao.findByGoodsId(id);
		if(listCart != null && listCart.size() > 0){
			for(int i = 0; i < listCart.size(); i++){
				ShoppingCart shoppingCart = listCart.get(i);
				shoppingCart.setGoodsPrice(bean.getSalePrice());
				shoppingCart.setGoodsPayPrice(bean.getSalePrice().multiply(new BigDecimal(shoppingCart.getGoodsNum())));
				shoppingCartDao.save(shoppingCart);
			}
		}
		
		return goodsDao.save(goods);
		
		
	}
	
	
	/**
	 * 上架/下架
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/goodsStatus/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "上架/下架", notes = "上架/下架")
	public Object modifyGoodsStatus(@ApiParam(value = "商品Id", required = true) @PathVariable int id,
			@ApiParam(value = "商品状态（ 0 待上架   1 上架   2  下架  10 禁售） ", required = true) @RequestParam(value = "goodsStatus")  int goodsStatus) throws BusinessErrorException {
		
		
		Goods goods = goodsDao.findOne(id);
		
		goods.setGoodsStatus(goodsStatus);
		Date now = new Date();
		goods.setUpdateTime(now);
		
		return goodsDao.save(goods);
		
		
	}
	

	/**
	 * 查看商品信息
	 * @param bean
	 */
	@RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查看商品信息", notes = "查看商品信息")
	public Object getStadium(@ApiParam(value = "场馆Id", required = true) @PathVariable int id) {
		
		Goods goods = goodsDao.findOne(id);

		return goods;
		
	}

}
