<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.HashSet, com.vin.messaging.dao.UserDao, com.vin.messaging.dao.FriendRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message App</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>

  <a href = "sendrequest.jsp">Send Request</a>
  <a href = "acceptreq">Accept Request</a>
  <h2>Friends List</h2>
    <ul>
        <%
        HttpSession s = request.getSession(false);

        String username = (String) s.getAttribute("username");

        if (username != null) {
            UserDao u = new UserDao();
            FriendRequest user = new FriendRequest();
            HashSet<String> friends = (HashSet<String>)s.getAttribute("myfriends");
            for (String friend : friends) {
            	if(!username.equals(friend)){
        %>
            <li><a href="chat.jsp?friendId=<%= u.getId(friend) %>"><%= friend %></a></li>
        <%
            	}
            }
        } else {
            out.println("<p>No user logged in.</p>");
        }
        %>
    </ul>
 </body>
</html>