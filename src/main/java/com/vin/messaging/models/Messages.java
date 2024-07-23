package com.vin.messaging.models;

import java.sql.Timestamp;

public class Messages {
  private int id;
  private int senderId;
  private int receiverId;
  private String content;
  private Timestamp timestamp;
  
  public Messages(int id, int senderId, int receiverId, String content,Timestamp timestamp) {
	  this.id = id;
	  this.senderId = senderId;
	  this.receiverId = receiverId;
	  this.content = content;
	  this.timestamp = timestamp;
}
  
  public int getId() {
	  return id;
  }
  
  public int getSenderId() {
	  return senderId;
  }
  
  public int getReceiverId() {
	  return receiverId;
  }
  
  public String getContent() {
	  return content;
  }
  public Timestamp getTimestamp() {
	  return timestamp;
  }
}
