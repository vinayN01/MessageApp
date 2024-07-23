<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, com.vin.messaging.dao.UserDao" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AcceptRequest></title>
<link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>
<h2>View Friend Requests: </h2>
<%
HttpSession sessionTemp = request.getSession(false);

ArrayList<String> friends = (ArrayList<String>)sessionTemp.getAttribute("friendList");
UserDao user = new UserDao();
if (friends != null){
	for(String friend  : friends){
	%>
		 <p>Request from: <%= friend %></p>
         <form action="acceptreq" method="post">
             <input type="hidden" name="myId" value="<%= sessionTemp.getAttribute("userId") %>">
             <input type="hidden" name="senderId" value="<%= user.getId(friend) %>">
             <button type="submit" name="response" value="accept">Accept</button>
             <button type="submit" name="response" value="reject">Reject</button>
         </form>
        <%
            }
        } else {
            out.println("<p>No friend requests found.</p>");
        }
    %>


</body>
</html>