package com.vin.messaging.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vin.messaging.connection.ConnectionFactory;
import com.vin.messaging.models.User;

public class UserDao {
 
	public void createUser(String username, String password) {
		try {
			Connection con = ConnectionFactory.requestConnection();
            String query = "insert into users (`username`,`password`) values (?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		
	}

	public int authentication(String username, String password) {
		int i = 0;
		try {
			Connection con = ConnectionFactory.requestConnection();
            String query = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	i=1;
            }
            rs.close();
            pstmt.close();
            con.close();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
		return i;
	}
	
	public User getUser(String username, String password) {
		User user = null;
		try {
			Connection con = ConnectionFactory.requestConnection();
            String query = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            int id = rs.getInt("id");
            String usernameTemp = rs.getString("username");
            user = new User(id,usernameTemp);
            }
            rs.close();
            pstmt.close();
            con.close();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	}
		return user;
}
	
	public int getId(String username) {
		int i = 0;
		try {
			Connection con = ConnectionFactory.requestConnection();
            String query = "select * from users where username = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            i = rs.getInt("id");
            }
            rs.close();
            pstmt.close();
            con.close();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	}
		return i;
}
	
	public String getUsername(int id) {
		String username= null;
		try {
			Connection con = ConnectionFactory.requestConnection();
            String query = "select * from users where id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            username = rs.getString("username");
            }
            rs.close();
            pstmt.close();
            con.close();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	}
		return username;
}
	
	public List<String> getUsernames() {
		List<String> usernames = new ArrayList<>();
		try {
			Connection con = ConnectionFactory.requestConnection();
            String query = "select * from users";
             Statement stmnt = con.createStatement();

            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()) {
            String usernameTemp = rs.getString("username");
            usernames.add(usernameTemp);
            }
            rs.close();
            stmnt.close();
            con.close();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	}
		return usernames;
}
}
