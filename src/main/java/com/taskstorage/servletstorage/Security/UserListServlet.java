package com.taskstorage.servletstorage.Security;

import com.taskstorage.servletstorage.Security.model.User;
import com.taskstorage.servletstorage.Security.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {
    private UserRepository userRepository;

    public UserListServlet() {
        userRepository = new UserRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = userRepository.selectAll();
        request.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/userJSP/listUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}