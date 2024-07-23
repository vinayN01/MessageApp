<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.HashSet" %>
<%@ page import = "com.vin.messaging.dao.FriendRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sendRequest</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

  <form action="send" method="post">
    <label for="username">Enter Friend's Username:</label>
    <input type="text" id="username" name="friendusername" required><br/>
    <button type="submit">Send Request</button>
  </form>
    
  <h2>Users You May Know, Try sending Requests To Them....</h2>
  <ul>
    <%
    HttpSession s = request.getSession(false);
    List<String> usernames = (List<String>) s.getAttribute("usernames");
    String myUsername = (String) s.getAttribute("username");
    HashSet<String> friends = (HashSet<String>) s.getAttribute("myfriends");

    if (usernames != null) {
        for (String username : usernames) {
            boolean isFriend = false;
            for (String friend : friends) {
                if (username.equals(friend)) {
                    isFriend = true;
                    break;
                }
            }
            if (!username.equals(myUsername) && !isFriend) {
    %>
                <li><%= username %></li>
    <%
            }
        }
    } else {
        out.println("<p>No users available.</p>");
    }
    %>
  </ul>
  
</body>
</html>
