package cn.org.citycloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.SupplierUser;



public interface SupplierUserDao extends JpaRepository<SupplierUser, Integer>, JpaSpecificationExecutor<SupplierUser> {

	
	SupplierUser findByUserNameAndUserPwd(String userName, String userPwd);

	SupplierUser findByUserName(String userName);
	
	SupplierUser findBySupplierId(int supplierId);
}
