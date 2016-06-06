package cn.org.citycloud.controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.GoodsClassSearch;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.GoodsClass;
import cn.org.citycloud.repository.GoodsClassDao;
import cn.org.citycloud.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 商品分类接口
 * @author Allen
 *
 */
@RestController
@Api(tags="商品分类", value = "/goodsclass",  description = "商品分类接口", consumes="application/json")
public class GoodsClassController extends BaseController{

	@Autowired
	private GoodsClassDao goodsclassDao;
	
	@Autowired
	private GoodsService goodsService;
	
	@PersistenceContext
    private EntityManager em;
	

	
	
	/**
	 * 查看一级商品分类
	 * @param bean
	 */
	@RequestMapping(value = "/goodsclassone", method = RequestMethod.GET)
	@ApiOperation(value = "查看一级商品分类列表", notes = "查看一级商品分类列表")
	public Object getGoodsClassOne() {
		
		List<GoodsClass> list = goodsclassDao.findByParentId(0);	// 0 代表一级 父ID

		return list;
		
	}

	/**
	 * 查看二、三级商品分类列表
	 * @param bean
	 */
	@RequestMapping(value = "/goodsclasslit/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查看二、三级商品分类列表", notes = "查看二、三级商品分类列表")
	public Object getGoodsClasslit(@ApiParam(value = "商品分类Id", required = true) @PathVariable int id) {
		
		List<GoodsClass> list = goodsclassDao.findByParentId(id);	

		return list;
		
	}
	
	/**
	 * 查看三级商品分类
	 * @param bean
	 */
	@RequestMapping(value = "/goodsclassthird/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查看三级商品分类", notes = "查看三级商品分类")
	public Object getGoodsClassThird(@ApiParam(value = "商品分类Id", required = true) @PathVariable int id) {
		
		GoodsClass goodsClass = goodsclassDao.findOne(id);	

		return goodsClass;
		
	}
	
	
	@RequestMapping(value = "/goodsclass", method = RequestMethod.GET)
	@ApiOperation(value="获取一~三级分类列表",notes="获取一~三级分类列表",consumes="application/json",produces="application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="pageNo",value="页数",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="pageSize",value="每页大小",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="pid",value="父分类id",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="className",value="三级分类名称",required=false,dataType="string",paramType="query")
    })
    public Object getGoodClassList(@ApiIgnore GoodsClassSearch classSearch) {
        return goodsService.getGoodClassList(classSearch);
    }

}
