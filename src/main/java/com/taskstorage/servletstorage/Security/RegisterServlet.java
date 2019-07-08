package com.taskstorage.servletstorage.Security;

import com.taskstorage.servletstorage.Security.model.Role;
import com.taskstorage.servletstorage.Security.model.User;
import com.taskstorage.servletstorage.Security.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserRepository userRepository;

    public RegisterServlet() {
        userRepository = new UserRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/userJSP/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User(username, password, Role.USER);
            userRepository.create(user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/userJSP/register.jsp").forward(request, response);
        }
    }
}