package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.org.citycloud.entity.Supplier;



public interface SupplierDao extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {

	@Query("SELECT a FROM Supplier a WHERE a.phone = ?1 ")
	Supplier findByPhone(String phone);
}
