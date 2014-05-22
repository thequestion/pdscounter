package com.wenbyuan.pdscounter.document;

public class Message extends Entry{
	private String messageId;
	
	public Message(String messageId){
		this.messageId = messageId;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	@Override
	public String toString() {
		return "messageId=" + messageId;
	}
	
}
