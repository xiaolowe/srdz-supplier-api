package cn.org.citycloud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.entity.MailMessage;



public interface MailMessageDao extends JpaRepository<MailMessage, Integer>, JpaSpecificationExecutor<MailMessage> {

	
	
}
