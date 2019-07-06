package com.taskstorage.servletstorage.CRUD.controller;

import com.taskstorage.servletstorage.CRUD.model.Task;
import com.taskstorage.servletstorage.CRUD.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/tasklist")
public class TaskListServlet extends HttpServlet {

    private TaskRepository taskRepository;

    public TaskListServlet() {
        super();
        taskRepository = new TaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Task> tasks = taskRepository.selectAll();
        request.setAttribute("tasks", tasks);

        getServletContext().getRequestDispatcher("/taskJSP/listTask.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}