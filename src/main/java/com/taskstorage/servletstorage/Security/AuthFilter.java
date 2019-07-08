package com.taskstorage.servletstorage.Security;

import com.taskstorage.servletstorage.Security.model.Role;
import com.taskstorage.servletstorage.Security.repository.UserRepository;

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

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        //Logged user - grab data from session.
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final Role role = Role.valueOf(session.getAttribute("role").toString());

            moveToMenu(req, res, role, filterChain);
            //Not logged user but exist (redirect from /userJSP/login.jsp)
        } else if (userRepository.userIsExist(login, password)) {

            final Role role = userRepository.getRoleByLogin(login);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role.name());

            moveToMenu(req, res, role, filterChain);
            //Before login
        } else {
            moveToMenu(req, res, Role.UNKNOWN, filterChain);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move anywhere.
     * If access 'user' move to tasks.
     * If user try to access wrong page - display "notfound"
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
