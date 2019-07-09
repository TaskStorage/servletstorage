package com.taskstorage.servletstorage.Security;

import com.taskstorage.servletstorage.model.Role;
import com.taskstorage.servletstorage.model.User;
import com.taskstorage.servletstorage.repository.UserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = {"/tasklist/*", "/userlist/*"})
public class AuthFilter implements Filter {

    private UserRepository userRepository;

    public AuthFilter() {
        userRepository = new UserRepository();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        User user;

        //3. Logged user - grab data from session.
        if (nonNull(session) &&
                nonNull(session.getAttribute("username")) &&
                nonNull(session.getAttribute("password"))) {

            final Role role = Role.valueOf(session.getAttribute("role").toString());

            moveToMenu(req, res, role, filterChain);
            //2. Retrive data from login.jsp (req.getParameter("username")...) and store to session -> redirect to destination
        } else if ((user = userRepository.selectByUsername(username)) != null && user.getPassword().equals(password)) {

            final Role role = user.getRole();

            req.getSession().setAttribute("password", user.getPassword());
            req.getSession().setAttribute("username", user.getUsername());
            req.getSession().setAttribute("role", user.getRole().name());

            moveToMenu(req, res, role, filterChain);
            //1. Not logged - go to login.jsp
        } else {
            moveToMenu(req, res, Role.UNKNOWN, filterChain);
        }
    }

    /**
     * If access 'admin' move anywhere.
     * If access 'user' move to tasks.
     * If user try to access wrong page - display "notfound"
     * If not logged - move to login.jsp
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final Role role, FilterChain filterChain)
            throws ServletException, IOException {

        if (role.equals(Role.ADMIN)) {
            filterChain.doFilter(req, res);
        } else if (role.equals(Role.USER) && req.getServletPath().equals("/tasklist")) {
            req.getRequestDispatcher("/tasklist").forward(req, res);
        } else if (role.equals(Role.USER)) {
            //If user try to access wrong page
            req.getRequestDispatcher("/notfound.jsp").forward(req, res);
        } else {
            //If not USER / ADMIN -> UNKNOWN -> Login
            req.getRequestDispatcher("/userJSP/login.jsp").forward(req, res);
        }
    }
}
