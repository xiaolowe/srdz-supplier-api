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

import cn.org.citycloud.constants.Constants;
import cn.org.citycloud.constants.ErrorCodes;
import cn.org.citycloud.core.BaseController;
import cn.org.citycloud.entity.MailMessage;
import cn.org.citycloud.entity.Message;
import cn.org.citycloud.entity.ReplyMessage;
import cn.org.citycloud.exception.BusinessErrorException;
import cn.org.citycloud.repository.MailMessageDao;
import cn.org.citycloud.repository.MessageDao;
import cn.org.citycloud.repository.ReplyMessageDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 消息接口
 * @author Allen
 *
 */
@RestController
@Api(tags="消息", value = "/message",  description = "消息接口", consumes="application/json")
public class MessageController extends BaseController{

	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private MailMessageDao mailmessageDao;
	
	@Autowired
	private ReplyMessageDao replyMessageDao;
	

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ApiOperation(value = "消息列表", notes = "消息列表信息")
	public Object getMessageList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "开始时间", required = false) @RequestParam(value = "startTime", required = false)  String startTime,
			@ApiParam(value = "结束时间", required = false) @RequestParam(value = "endTime", required = false)  String endTime,
			@ApiParam(value = "消息关键字", required = false) @RequestParam(value = "messageContent", required = false)  String messageContent) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "messageId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<Message> page = messageDao.findAll(new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				
				Path<String> senderId = root.get("senderId");  
				where = cb.and(where, cb.equal(senderId, supplierId));	
				
				Path<String> senderPlatform = root.get("senderPlatform");  
				where = cb.and(where, cb.equal(senderPlatform, Constants.PLATFORM_SUPPLIER));
				
				Path<String> receiverId = root.get("receiverId");  
				where = cb.or(where, cb.equal(receiverId, supplierId));
				
				Path<String> platform = root.get("platform");  
				where = cb.and(where, cb.equal(platform, Constants.PLATFORM_SUPPLIER));	
				
				
				if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
					Path<Date> addTime = root.get("sendTime"); 
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
				
				if(StringUtils.isNotBlank(messageContent)){
					Path<String> messageContentQuery = root.get("messageContent");  
					where = cb.and(where, cb.like(messageContentQuery, "%"+messageContent+"%"));
					
				}
				
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}
	
	
	@RequestMapping(value = "/mailmessage", method = RequestMethod.GET)
	@ApiOperation(value = "站内信列表", notes = "站内信列表信息")
	public Object getmailMessageList(@ApiParam(value = "页数", defaultValue = "1", required = false) @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页大小",defaultValue = "10", required = false) @RequestParam(value = "pageSize", defaultValue = "10")  Integer pageSize,
			@ApiParam(value = "开始时间", required = false) @RequestParam(value = "startTime", required = false)  String startTime,
			@ApiParam(value = "结束时间", required = false) @RequestParam(value = "endTime", required = false)  String endTime,
			@ApiParam(value = "消息关键字", required = false) @RequestParam(value = "messageContent", required = false)  String messageContent) throws RuntimeException, Exception {

		int supplierId = getSupplierId();
		if(supplierId == 0){
			throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该供应商没有认证，请认证！");
		}
		
		Sort sort = new Sort(Direction.DESC, "mailMessageId");
	    Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
	    
		Page<MailMessage> page = mailmessageDao.findAll(new Specification<MailMessage>() {
			@Override
			public Predicate toPredicate(Root<MailMessage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate where = cb.conjunction();  
				
				Path<String> receiverId = root.get("receiverId");  
				where = cb.and(where, cb.equal(receiverId, supplierId));
				
				if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
					Path<Date> addTime = root.get("sendTime"); 
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
					
					if(StringUtils.isNotBlank(messageContent)){
						Path<String> messageContentQuery = root.get("messageContent");  
						where = cb.and(where, cb.like(messageContentQuery, "%"+messageContent+"%"));
						
					}
					
					where = cb.and(where, cb.between(addTime, start, end)); 
					
				}
				
				query.where(where);
				  
			    return null;   
			}
		}, pageable);
	
		return page;
	    
	    
	}
	

	/**
	 * 查看系统消息
	 * @param bean
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查看系统消息", notes = "查看系统消息")
	public Object getMessage(@ApiParam(value = "消息Id", required = true) @PathVariable int id) {
		
		Message message = messageDao.findOne(id);
		message.setStatus(1);  //0 未读    1 已读

		return messageDao.save(message);
		
	}
	
	/**
	 * 查看站内信消息
	 * @param bean
	 */
	@RequestMapping(value = "/mailmessage/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查看站内信消息", notes = "查看站内信消息")
	public Object getMailMessage(@ApiParam(value = "站内信消息Id", required = true) @PathVariable int id) {
		
		MailMessage mailmessage = mailmessageDao.findOne(id);
		mailmessage.setStatus(1);  //0 未读    1 已读

		return mailmessageDao.save(mailmessage);
		
	}
	
	/**
	 * 回复消息
	 * @param bean
	 */
	@RequestMapping(value = "/replymessage/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "回复站内信消息", notes = "回复站内信消息")
	public Object replyMessage(@ApiParam(value = "消息Id", required = true) @PathVariable int id,
			@ApiParam(value = "回复内容") @RequestParam(value = "replyContent")  String replyContent) {
		
		MailMessage mailmsg = mailmessageDao.findOne(id);
		
		ReplyMessage replyMessage = new ReplyMessage();
		replyMessage.setMailMessageId(id);
		replyMessage.setSenderId(mailmsg.getReceiverId());
		replyMessage.setSender(mailmsg.getReceiver());
		replyMessage.setReceiverId(mailmsg.getSenderId());
		replyMessage.setMemberId(mailmsg.getSenderId());
		replyMessage.setReceiver(mailmsg.getSender());
		
		replyMessage.setTriggerEvent(mailmsg.getTriggerEvent());
		replyMessage.setReplyContent(replyContent);
		replyMessage.setMsgFlag(1);  	//供应商回复的信息
		
		Date now = new Date();
		replyMessage.setReplyTime(now);
		replyMessage.setCreateDate(now);
		replyMessage.setUpdateDate(now);
		

		return replyMessageDao.save(replyMessage);
		
	}
	
	/**
	 * 系统消息批量删除
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/message", method = RequestMethod.DELETE)
	@ApiOperation(value = "系统消息批量删除", notes = "系统消息批量删除")
	public void removeMessage(@ApiParam(value = "系统消息批量删除,格式 （1,2,3,4,5）") @RequestParam(value = "messageIds")  String[] messageIds) throws BusinessErrorException {
		
		
		for(int i = 0 ; i < messageIds.length; i ++){
			String msgId = messageIds[i];
			try {
				messageDao.delete(Integer.valueOf(msgId));
			} catch (Exception e) {
				throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该ID不存在！  "+msgId);
			}
			
		}

	}
	
	/**
	 * 站内信消息批量删除
	 * @param bean
	 * @throws BusinessErrorException 
	 */
	@RequestMapping(value = "/mailmessage", method = RequestMethod.DELETE)
	@ApiOperation(value = "站内信消息批量删除", notes = "站内信消息批量删除")
	public void removeMailMessage(@ApiParam(value = "站内信消息批量删除,格式（1,2,3,4,5）") @RequestParam(value = "messageIds")  String[] messageIds) throws BusinessErrorException {
		
		
		for(int i = 0 ; i < messageIds.length; i ++){
			String msgId = messageIds[i];
			try {
				mailmessageDao.delete(Integer.valueOf(msgId));
			} catch (Exception e) {
				throw new BusinessErrorException(ErrorCodes.SYSTEM_ERROR, "该ID不存在！  "+msgId);
			}
			
		}

	}
	
	

}
