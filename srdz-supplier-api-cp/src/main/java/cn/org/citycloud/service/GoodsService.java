package cn.org.citycloud.service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.org.citycloud.bean.GoodsClassListBean;
import cn.org.citycloud.bean.GoodsClassSearch;

@Component
public class GoodsService {
	
	
	@PersistenceContext
    private EntityManager em;
	
	
    public Object getGoodClassList(GoodsClassSearch classSearch) {
        StringBuffer sql = new StringBuffer("select t1.goods_class_id id1,t2.goods_class_id id2,t3.goods_class_id id3, t1.goods_class_name name1,t2.goods_class_name name2,t3.goods_class_name name3,t3.class_image, t3.goods_desc ");
        sql.append(" from goods_class t1");
        sql.append(" left join goods_class t2 on t1.goods_class_id = t2.parent_id ");
        sql.append(" inner join goods_class t3 on t3.parent_id = t2.goods_class_id where t1.parent_id = 0");
        if(classSearch.getPid() != -1) {
            sql.append(" and (t3.parent_id = " + classSearch.getPid() + " or t2.parent_id = " + classSearch.getPid() + ")");
        }
        if (!StringUtils.isEmpty(classSearch.getClassName())) {
            sql.append(" and t3.goods_class_name like '%" + classSearch.getClassName() + "%'");
        }
        int page = classSearch.getPageNo();
        int pageSize = classSearch.getPageSize();
        int start = (page - 1) * pageSize;
        sql.append(" order by t3.sort limit " + start + "," + pageSize);
        Query query = em.createNativeQuery(sql.toString());
        List<Object[]> tempList = query.getResultList();
        List<GoodsClassListBean> content = new ArrayList<>();
        for (Object[] objArr : tempList) {
            if(objArr.length == 8) {
                GoodsClassListBean goodsClassListBean = new GoodsClassListBean();
                goodsClassListBean.setFirstClassId((Integer) objArr[0]);
                goodsClassListBean.setSecondClassId((Integer) objArr[1]);
                goodsClassListBean.setThirdClassId((Integer) objArr[2]);
                goodsClassListBean.setFirstClassName((String) objArr[3]);
                goodsClassListBean.setSecondClassName((String) objArr[4]);
                goodsClassListBean.setThirdClassName((String) objArr[5]);
                goodsClassListBean.setClassImage((String) objArr[6]);
                goodsClassListBean.setGoodsDesc((String) objArr[7]);
                content.add(goodsClassListBean);
            }
        }
        StringBuffer countSql = new StringBuffer("SELECT count(1) FROM goods_class t1 LEFT JOIN goods_class t2 ON t1.goods_class_id = t2.parent_id INNER JOIN goods_class t3 ON t3.parent_id = t2.goods_class_id WHERE t1.parent_id = 0");
        if(classSearch.getPid() != -1) {
            countSql.append(" and (t3.parent_id = " + classSearch.getPid() + " or t2.parent_id = " + classSearch.getPid() + ")");
        }
        if (!StringUtils.isEmpty(classSearch.getClassName())) {
            countSql.append(" and t3.goods_class_name like '%" + classSearch.getClassName() + "%'");
        }
        Query countQuery = em.createNativeQuery(countSql.toString());
        BigInteger total = (BigInteger) countQuery.getSingleResult();
        Pageable pageable = new PageRequest(page - 1, pageSize);
        Page<GoodsClassListBean> resultPage = new PageImpl<GoodsClassListBean>(content, pageable, total.longValue());
        return resultPage;
    }
}
