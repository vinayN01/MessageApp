package com.vin.messaging.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.vin.messaging.dao.MessageDao;

@WebServlet("/sendmessage")
public class sendmessage extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		MessageDao msg = new MessageDao();
		int myId = (int) session.getAttribute("userId");
		int friendId = Integer.parseInt(req.getParameter("receiverId"));
		String content = req.getParameter("content");
	    msg.sendMessage(myId, friendId, content);
	    resp.sendRedirect("chat.jsp?friendId="+friendId);


	}

}
