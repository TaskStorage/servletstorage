package com.taskstorage.servletstorage.CRUD.controller;

import com.taskstorage.servletstorage.CRUD.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private TaskRepository taskRepository;

    public DeleteServlet() {
        super();
        taskRepository = new TaskRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            taskRepository.delete(id);
            response.sendRedirect(request.getContextPath() + "/tasklist");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}