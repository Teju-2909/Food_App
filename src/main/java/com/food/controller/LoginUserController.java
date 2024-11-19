package com.food.controller;



import com.food.dao.FoodOrderDao;
import com.food.dao.MenuDao;
import com.food.dao.UserDao;
import com.food.dto.FoodOrder;
import com.food.dto.Menu;
import com.food.dto.User;



import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao dao = new UserDao();
        User user = dao.getUserByEmail(email);
        FoodOrderDao foodOrderDao = new FoodOrderDao();
        MenuDao menuDao = new MenuDao();
        List<Menu> menus = menuDao.getAllMenus();

        HttpSession session = req.getSession();
        Boolean isCustomerLoggedIn = (Boolean) session.getAttribute("isCustomerLoggedIn");

        if (user == null) {
            
            req.setAttribute("message", "User not found");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else if (user.getPassword().equals(password) && user.getRole().equals("manager")) {
           
            req.setAttribute("menus", menus);
            RequestDispatcher dispatcher = req.getRequestDispatcher("menu.jsp");
            dispatcher.forward(req, resp);
        } else if (user.getPassword().equals(password) && user.getRole().equals("customer")) {
            
            session.setAttribute("id", user.getId());
            session.setAttribute("isCustomerLoggedIn", true); 

         
            for (FoodOrder foodOrder : foodOrderDao.getFoodOrder()) {
                if (user.getId() == foodOrder.getUser().getId()) {
                    req.setAttribute("foodorder", foodOrder);
                }
            }

            SaveUserController.user = user;
            req.setAttribute("menus", menus);
            RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
            dispatcher.forward(req, resp);
        } else if (user.getPassword().equals(password) && user.getRole().equals("staff")) {
           
            if (isCustomerLoggedIn != null && isCustomerLoggedIn) {
                
                req.setAttribute("menus", menus);
                RequestDispatcher dispatcher = req.getRequestDispatcher("staff.jsp");
                dispatcher.forward(req, resp);
            } else {
               
                req.setAttribute("message", "Customer must log in first. Please login as a customer.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            
            req.setAttribute("message", "Invalid Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
