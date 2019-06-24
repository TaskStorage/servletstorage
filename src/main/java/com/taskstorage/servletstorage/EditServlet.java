package com.taskstorage.servletstorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Task task = TaskDBWorker.selectOne(id);
            if (task != null) {
                request.setAttribute("task", task);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String description = request.getParameter("description");
            String content = request.getParameter("content");

            Task task = new Task(id, description, content);
            TaskDBWorker.update(task);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}