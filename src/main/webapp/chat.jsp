<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.vin.messaging.dao.MessageDao, java.util.List, com.vin.messaging.models.Messages, com.vin.messaging.dao.UserDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

 <%
       HttpSession s = request.getSession(false);
	    MessageDao msg = new MessageDao();
	    UserDao u = new UserDao();
        int friendId = Integer.parseInt(request.getParameter("friendId"));
        int userId = (int)s.getAttribute("userId");
	    String friendName = u.getUsername(friendId);

        List<Messages> messages = msg.getMessagesBwUs(userId, friendId);
    %>
    <h2>Chat with <%= friendName %></h2>
    <div class="messages">
        <%
            // Display messages from oldest to newest
            for (Messages message : messages) {
        %>
                    <div class="message <%= (message.getSenderId() == userId) ? "sent" : "received" %>">
                        <p><%= message.getContent() %></p>
                    </div>
        <%
            }
        %>
    </div>
  
    <form action="sendmessage" method="post">
        <input type="hidden" name="receiverId" value="<%= friendId %>">
        <textarea name="content" placeholder="Type your message here..."></textarea>
        <button type="submit">Send</button>
    </form>
</body>
</html>
"<%-- Force update for language detection --%>" 
