<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.vin.messaging.models.Messages" %>
<%@ page import = "com.vin.messaging.dao.MessageDao" %>
<%@ page import = "java.util.List" %>
<%
    int userId = Integer.parseInt(request.getParameter("userId"));
    int friendId = Integer.parseInt(request.getParameter("friendId"));

    MessageDao msg = new MessageDao();
    List<Messages> messages = msg.getMessagesBwUs(userId, friendId);
%>
<% for (Messages message : messages) { %>
    <div class="message <%= (message.getSenderId() == userId) ? "sent" : "received" %>">
        <p><%= message.getContent() %></p>
    </div>
<% } %>
