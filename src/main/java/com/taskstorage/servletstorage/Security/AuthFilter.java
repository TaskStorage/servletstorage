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

@WebFilter("/tasklist")
//@WebFilter("/*")
public class AuthFilter implements Filter {
//    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
//            Arrays.asList("", "/login", "/logout", "/register")));

    private UserRepository userRepository;

    public AuthFilter() {
        userRepository = new UserRepository();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        //Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final Role role = (Role) session.getAttribute("role");

            moveToMenu(req, res, role);


        } else if (userRepository.userIsExist(login, password)) {

            final Role role = UserRepository.getRoleByLoginPassword(login, password);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, Role.UNKNOWN);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final Role role)
            throws ServletException, IOException {


        if (role.equals(Role.USER)) {
            req.getRequestDispatcher("/tasklist").forward(req, res);

        } else {
            req.getRequestDispatcher("/userJSP/login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }
}
