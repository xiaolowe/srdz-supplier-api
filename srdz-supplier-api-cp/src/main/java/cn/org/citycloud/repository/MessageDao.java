package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.Message;



public interface MessageDao extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {

	
	
}
