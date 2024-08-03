package com.vin.messaging.servlets;

import java.io.IOException;
import java.util.List;

import com.vin.messaging.dao.MessageDao;
import com.vin.messaging.models.Messages;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fetchMessages")
public class FetchMessages extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int friendId = Integer.parseInt(request.getParameter("friendId"));

            MessageDao msg = new MessageDao();
            List<Messages> messages = msg.getMessagesBwUs(userId, friendId);

            request.setAttribute("messages", messages);
            request.setAttribute("userId", userId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("messages.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid userId or friendId parameter.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching messages.");
        }
    }
}
