package com.vin.messaging.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.vin.messaging.connection.ConnectionFactory;
import com.vin.messaging.models.Messages;

public class MessageDao {
public void sendMessage(int myId, int friendId, String content) {
	try {
		Connection con = ConnectionFactory.requestConnection();
		String query = "insert into messages (`sender_id`,`receiver_id`,`content`) values (?,?,?)";
		 PreparedStatement pstmt = con.prepareStatement(query);
         pstmt.setInt(1, myId);
         pstmt.setInt(2, friendId);
         pstmt.setString(3, content);
         pstmt.executeUpdate();
         pstmt.close();
         con.close();
	} catch (ClassNotFoundException | SQLException  e) {
		e.printStackTrace();
	} 
}
public List<Messages> getMessagesBwUs(int myId, int friendId) {
	List<Messages> msgs = new ArrayList<>();
	try {
		Connection con = ConnectionFactory.requestConnection();
		String query = "select * from messages where (sender_id = ? and receiver_id = ?) or (sender_id = ? and receiver_id = ?) ORDER BY timestamp ASC";
		 PreparedStatement pstmt = con.prepareStatement(query);
         pstmt.setInt(1, myId);
         pstmt.setInt(2, friendId);
         pstmt.setInt(3, friendId);
         pstmt.setInt(4, myId);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
        	 int id = rs.getInt("id");
        	 int senderId = rs.getInt("sender_id");
        	 int receiverId = rs.getInt("receiver_id");
        	 String content = rs.getString("content");
        	 Timestamp timestamp = rs.getTimestamp("timestamp");
        	 Messages msg = new Messages(id,senderId,receiverId,content,timestamp);
             msgs.add(msg);
         }
         rs.close();
         pstmt.close();
         con.close();
         } catch (ClassNotFoundException | SQLException  e) {
		e.printStackTrace();
	} 
	return msgs;
}
}
