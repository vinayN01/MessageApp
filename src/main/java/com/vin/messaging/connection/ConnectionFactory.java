package com.vin.messaging.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	  static Connection con = null;
	   static String url = "jdbc:mysql://localhost:3306/messages";
	   static String un = "root";
	   static String pw = "Vinayreddy@1234";

	   static public Connection requestConnection() throws ClassNotFoundException,SQLException{

	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection(url,un,pw);
	    return con;
	   }
}
