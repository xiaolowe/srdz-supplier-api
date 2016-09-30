package cn.org.citycloud.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the reply_message database table.
 * 
 */
@Entity
@Table(name="reply_message")
@NamedQuery(name="ReplyMessage.findAll", query="SELECT r FROM ReplyMessage r ")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ReplyMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="reply_message_id")
	private int replyMessageId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="mail_message_id")
	private int mailMessageId;

	@Column(name="member_id")
	private int memberId;

	private String receiver;

	@Column(name="receiver_id")
	private int receiverId;

	private String remark;

	@Column(name="reply_content")
	private String replyContent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reply_time")
	private Date replyTime;

	private String sender;

	@Column(name="sender_id")
	private int senderId;

	private int status;
	
	@Column(name="msg_flag")
	private int msgFlag;

	@Column(name="trigger_event")
	private String triggerEvent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="mail_message_id", insertable = false, updatable = false)
	@JsonIgnore
    private MailMessage mailMessage;

	public ReplyMessage() {
	}

	public int getReplyMessageId() {
		return this.replyMessageId;
	}

	public void setReplyMessageId(int replyMessageId) {
		this.replyMessageId = replyMessageId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getMailMessageId() {
		return this.mailMessageId;
	}

	public void setMailMessageId(int mailMessageId) {
		this.mailMessageId = mailMessageId;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getSenderId() {
		return this.senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTriggerEvent() {
		return this.triggerEvent;
	}

	public void setTriggerEvent(String triggerEvent) {
		this.triggerEvent = triggerEvent;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public MailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public int getMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(int msgFlag) {
		this.msgFlag = msgFlag;
	}

	
}