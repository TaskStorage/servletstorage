package com.taskstorage.servletstorage.controller;

import com.taskstorage.servletstorage.model.Task;
import com.taskstorage.servletstorage.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/tasklist")
public class TaskController extends HttpServlet {

    private TaskRepository taskRepository;

    public TaskController() {
        taskRepository = new TaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("create")) {
            createTask(request, response);
        } else if (action != null && action.equalsIgnoreCase("edit")) {
            editTask(request, response);
        } else {
            listTask(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("create")) {
            createTask(request, response);
        } else if (action != null && action.equalsIgnoreCase("edit")) {
            editTask(request, response);
        } else if (action != null && action.equalsIgnoreCase("delete")) {
            deleteTask(request, response);
        } else {
            listTask(request, response);
        }
    }

    private void listTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Task> tasks = taskRepository.selectAll();
        request.setAttribute("tasks", tasks);
        getServletContext().getRequestDispatcher("/taskJSP/listTask.jsp").forward(request, response);
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            getServletContext().getRequestDispatcher("/taskJSP/createTask.jsp").forward(request, response);
        } else {
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

    private void editTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                Task task = taskRepository.selectOne(id);
                if (task != null) {
                    request.setAttribute("task", task);
                    getServletContext().getRequestDispatcher("/taskJSP/editTask.jsp").forward(request, response);
                } else {
                    notFound(request, response);
                }
            } catch (Exception ex) {
                notFound(request, response);
            }
        } else {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                String description = request.getParameter("description");
                String content = request.getParameter("content");

                Task task = new Task(id, description, content);
                taskRepository.update(task);
                response.sendRedirect(request.getContextPath() + "/tasklist");
            } catch (Exception ex) {
                notFound(request, response);
            }
        }
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                taskRepository.delete(id);
                response.sendRedirect(request.getContextPath() + "/tasklist");
            } catch (Exception ex) {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } else {
            notFound(request, response);
        }

    }

    private void notFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/taskJSP/createTask.jsp").forward(request, response);
    }
}
