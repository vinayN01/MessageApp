package com.vin.messaging.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.vin.messaging.dao.UserDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  String username = req.getParameter("username");
  String password = req.getParameter("password");
  UserDao user = new UserDao();
  user.createUser(username, password);
  resp.sendRedirect("login.html");

}

}
