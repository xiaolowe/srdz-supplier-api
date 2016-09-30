package cn.org.citycloud.repository;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.org.citycloud.entity.PayInfo;


public interface PayInfoDao extends JpaRepository<PayInfo, Integer>, JpaSpecificationExecutor<PayInfo> {

	
}
