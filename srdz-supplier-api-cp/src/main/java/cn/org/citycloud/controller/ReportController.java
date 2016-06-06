package cn.org.citycloud.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.bean.ReportSearch;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 报表controller.
 *
 */
@RestController
@Api(tags="报表", value = "/report",  description = "报表导出接口", consumes="application/json")
@RequestMapping("/report")
public class ReportController extends BaseController{

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value="获取进出账目",notes="获取进出账目",consumes="application/json",produces="application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="year",value="年",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="month",value="月",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="classfyId",value="商品分类Id",required=false,dataType="int",paramType="query")
    })
    public Object getReport(@ApiIgnore ReportSearch reportSearch) throws BusinessErrorException {
    	
    	int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
    	reportSearch.setSupplierId(supplierId);
    	
        return reportService.getReport(reportSearch);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ApiOperation(value="获取报表详情",notes="获取报表详情",consumes="application/json",produces="application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="pageNo",value="页数",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="pageSize",value="每页大小",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="classfyId",value="商品分类Id",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="year",value="年",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="month",value="月",required=false,dataType="int",paramType="query")
    })
    public Object getReportItemList(@ApiIgnore ReportSearch reportSearch) throws BusinessErrorException {
    	
    	int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
    	reportSearch.setSupplierId(supplierId);
    	
        return reportService.getItemList(reportSearch);
    }

    @RequestMapping(value = "/downExcel", method = RequestMethod.GET)
    @ApiOperation(value="下载报表",notes="下载报表",consumes="application/json",produces="application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="year",value="年",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="month",value="月",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="classfyId",value="商品分类Id",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="supplierId",value="供应商Id",required=true,dataType="int",paramType="query")
    })
    public void downExcel(@ApiIgnore ReportSearch reportSearch, HttpServletResponse response) throws IOException, BusinessErrorException {
    	
    	int supplierId = reportSearch.getSupplierId();
		if(supplierId == -1){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "参数错误！");
		}
    	reportSearch.setSupplierId(supplierId);
    	
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        reportService.downReportExcel(reportSearch, os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("财务报表.xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(out);
        byte[] buff = new byte[2048];
        int bytesRead;
        // Simple read/write loop.
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bos.close();
    }
}
