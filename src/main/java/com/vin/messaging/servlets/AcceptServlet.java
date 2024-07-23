package com.vin.messaging.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.vin.messaging.dao.FriendRequest;

@WebServlet("/acceptreq")
public class AcceptServlet extends HttpServlet {
	FriendRequest reqs = new FriendRequest();

 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	int userId = (int)session.getAttribute("userId");
	ArrayList<String> friends = reqs.getFriends(userId);
	session.setAttribute("friendList", friends);
	req.getRequestDispatcher("acceptrequest.jsp").forward(req, resp);
	}
 
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    int myId = Integer.parseInt(req.getParameter("myId"));
	    int senderId = Integer.parseInt(req.getParameter("senderId"));

		String response = req.getParameter("response");
        if(response.equals("accept")) {
        	String resp1 = "accepted";
    		reqs.updateStatus(resp1, myId, senderId);

        }else {
        	String resp2 = "rejected";
    		reqs.updateStatus(resp2, myId, senderId);

        }
//		req.getRequestDispatcher("userpage.jsp").forward(req, resp);
		resp.sendRedirect("userpage.jsp");
	}
    	 
	 
}

