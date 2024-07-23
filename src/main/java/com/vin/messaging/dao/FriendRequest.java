package com.vin.messaging.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.vin.messaging.connection.ConnectionFactory;
import com.vin.messaging.models.User;
import com.vin.messaging.models.friendrequests;

public class FriendRequest {
  public void friendRequest(int senderId, int receiverId) {
	  try {
		Connection con = ConnectionFactory.requestConnection();
		String query = "insert into friendrequests (`sender_id`, `receiver_id`) values (?,?)";
		 PreparedStatement pstmt = con.prepareStatement(query);
         pstmt.setInt(1, senderId);
         pstmt.setInt(2, receiverId);
         pstmt.executeUpdate();
         pstmt.close();
         con.close();
	} catch (ClassNotFoundException | SQLException  e) {
		e.printStackTrace();
	} 
  }
  
  public int friendRequestValidation(int senderId, int receiverId) {
	  int i =0;
	  try {
		Connection con = ConnectionFactory.requestConnection();
		String query = "select * from friendrequests where (sender_id = ? and receiver_id = ?) or (sender_id = ? and receiver_id = ?)";
		 PreparedStatement pstmt = con.prepareStatement(query);
         pstmt.setInt(1, senderId);
         pstmt.setInt(2, receiverId);
         pstmt.setInt(3, receiverId);
         pstmt.setInt(4, senderId);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
        	 String status = rs.getString("status");
             if ("accepted".equals(status) || "pending".equals(status)) {
            	 i=1;
        		 break;
        	 }
         }
         rs.close();
         pstmt.close();
         con.close();
	} catch (ClassNotFoundException | SQLException  e) {
		e.printStackTrace();
	} 
	  return i;
  }
  
  public ArrayList<String> getFriends(int id) {
	  UserDao user = new UserDao();
	  ArrayList<String> friends = new ArrayList<>();
	  try {
			Connection con = ConnectionFactory.requestConnection();
			String query = "select sender_id from friendrequests where receiver_id = ? and status = 'pending'";
			 PreparedStatement pstmt = con.prepareStatement(query);
	         pstmt.setInt(1, id);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	        	 int idTemp = rs.getInt("sender_id");
	        	 String friend = user.getUsername(idTemp);
	        	 friends.add(friend);
	         }
	         rs.close();
	            pstmt.close();
	            con.close();
		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		} 
	  return friends;
  }
  
  public void updateStatus(String response, int myId, int senderId ) {
	  try {
		Connection con = ConnectionFactory.requestConnection();
		String query = "update friendrequests set status = ? where sender_id = ? and receiver_id = ? ";
		 PreparedStatement pstmt = con.prepareStatement(query);
         pstmt.setString(1, response);
         pstmt.setInt(2, senderId);
         pstmt.setInt(3, myId);
         pstmt.executeUpdate();
         pstmt.close();
         con.close();
	} catch (ClassNotFoundException | SQLException  e) {
		e.printStackTrace();
	} 
  }
  
  public HashSet<String> getAllFriends(int id) {
	  UserDao user = new UserDao();
	  HashSet<String> set = new HashSet<>();
	  try {
			Connection con = ConnectionFactory.requestConnection();
			String query = "select * from friendrequests where (receiver_id = ? or sender_id = ?) and status = 'accepted'";
			 PreparedStatement pstmt = con.prepareStatement(query);
	         pstmt.setInt(1, id);
	         pstmt.setInt(2, id);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	        	 int idTemp1 = rs.getInt("sender_id");
	        	 int idTemp2 = rs.getInt("receiver_id");
	        	 if (idTemp1 != id) { // Add the friend who is the sender
	                 String friend1 = user.getUsername(idTemp1);
	                 set.add(friend1);
	             }

	             if (idTemp2 != id) { // Add the friend who is the receiver
	                 String friend2 = user.getUsername(idTemp2);
	                 set.add(friend2);
	             }
//	        	 String friend1 = user.getUsername(idTemp1);
//	        	 String friend2 = user.getUsername(idTemp1);
//                 set.add(friend1);
//                 set.add(friend2);
	         }
	         rs.close();
	            pstmt.close();
	            con.close();
		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		} 
	  return set;
  }
}
