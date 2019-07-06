package com.taskstorage.servletstorage.CRUD.controller;

import com.taskstorage.servletstorage.CRUD.model.Task;
import com.taskstorage.servletstorage.CRUD.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    private TaskRepository taskRepository;

    public CreateServlet() {
        super();
        taskRepository = new TaskRepository();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/taskJSP/createTask.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String description = request.getParameter("description");
            String content = request.getParameter("content");
            Task task = new Task(description, content);
            taskRepository.create(task);
            response.sendRedirect(request.getContextPath() + "/tasklist");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/taskJSP/createTask.jsp").forward(request, response);
        }
    }
}