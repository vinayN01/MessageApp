package com.vin.messaging.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.vin.messaging.dao.FriendRequest;
import com.vin.messaging.dao.UserDao;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		int senderId = (int) s.getAttribute("userId");

      String receiverName = (String) req.getParameter("friendusername");
      UserDao receiver = new UserDao();
      int receiverId = receiver.getId(receiverName);
      FriendRequest reqs = new FriendRequest();
      int i = reqs.friendRequestValidation(senderId, receiverId);
      if(receiverId != 0 && i == 0) {
       reqs.friendRequest(senderId, receiverId);
//       req.getRequestDispatcher("userpage.jsp").forward(req, resp);
		resp.sendRedirect("userpage.jsp");

	}else {
		req.getRequestDispatcher("sendrequest.jsp").forward(req, resp);
	}
	}
}
