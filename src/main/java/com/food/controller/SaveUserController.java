package com.food.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.UserDao;
import com.food.dto.User;

@WebServlet("/signup")
public class SaveUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	 static User user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long phone = Long.parseLong(req.getParameter("phone"));
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        UserDao dao = new UserDao();
        User existingUser = dao.getUserByEmail(email);

        if (existingUser != null) {
           
            req.setAttribute("error", "User with this email already exists. Please log in.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
            dispatcher.forward(req, resp);
        } else {
            
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setAddress(address);
            user.setRole(role);

            User savedUser = dao.saveUser(user);

            if (savedUser != null) {
                req.setAttribute("message", "Successfully Signed Up");
                RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("error", "Something went wrong. Please try again.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
