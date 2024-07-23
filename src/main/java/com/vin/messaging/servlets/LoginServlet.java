package com.vin.messaging.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.vin.messaging.dao.FriendRequest;
import com.vin.messaging.dao.UserDao;
import com.vin.messaging.models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String username = req.getParameter("username");
		  String password = req.getParameter("password");
		  UserDao user = new UserDao();
		  int i = user.authentication(username, password);
		  User userTemp = user.getUser(username, password);
		  List<String> usernames = user.getUsernames();
	      HttpSession session = req.getSession();
		    FriendRequest u = new FriendRequest();

		  if(i==1) {
	      session.setAttribute("userId", userTemp.getId());
	      session.setAttribute("username", username);
	      session.setAttribute("usernames", usernames);
	      HashSet<String> friendsL = u.getAllFriends(userTemp.getId());
	      session.setAttribute("myfriends", friendsL);
           req.getRequestDispatcher("userpage.jsp").forward(req, resp);
		  }else {
			  resp.sendRedirect("invalidlogin.html");
		  }
	}

}
