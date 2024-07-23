package com.vin.messaging.models;

public class friendrequests {
private int id;
private int senderId;
private int receiverId;
private String status;

public friendrequests(int id, int senderId, int receiverId, String status) {
	  this.id = id;
	  this.senderId = senderId;
	  this.receiverId = receiverId;
	  this.status = status;
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

public String getStatus() {
	  return status;
}

 
}
