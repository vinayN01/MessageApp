<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.vin.messaging.dao.MessageDao, java.util.List, com.vin.messaging.models.Messages, com.vin.messaging.dao.UserDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<%
    HttpSession s = request.getSession(false);
    MessageDao msg = new MessageDao();
    UserDao u = new UserDao();
    int friendId = Integer.parseInt(request.getParameter("friendId"));
    int userId = (int) s.getAttribute("userId");
    String friendName = u.getUsername(friendId);
%>
<h2>Chat with <%= friendName %></h2>
<div class="messages" id="messages">
    <jsp:include page="messages.jsp">
        <jsp:param name="userId" value="<%= userId %>" />
        <jsp:param name="friendId" value="<%= friendId %>" />
    </jsp:include>
</div>

<form id="sendMessageForm" action="sendmessage" method="post">
    <input type="hidden" name="receiverId" value="<%= friendId %>">
    <textarea name="content" placeholder="Type your message here..."></textarea>
    <button type="submit">Send</button>
</form>

<script>
$(document).ready(function() {
    function fetchMessages() {
        $.ajax({
            url: 'fetchMessages',
            type: 'GET',
            data: {
                userId: '<%= userId %>',
                friendId: '<%= friendId %>'
            },
            success: function(data) {
                $('#messages').html(data);
            }
        });
    }

    setInterval(fetchMessages, 3000); // Fetch new messages every 3 seconds

    $('#sendMessageForm').submit(function(event) {
        event.preventDefault(); // Prevent the form from submitting the traditional way
        $.ajax({
            url: 'sendmessage',
            type: 'POST',
            data: $(this).serialize(),
            success: function() {
                fetchMessages(); // Fetch new messages after sending a message
                $('textarea[name="content"]').val(''); // Clear the textarea
            }
        });
    });
});
</script>

</body>
</html>
