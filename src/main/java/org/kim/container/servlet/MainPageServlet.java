package org.kim.container.servlet;

import org.kim.container.domain.Member;
import org.kim.container.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.isLoggedIn(req)) {
            resp.sendRedirect("login.jsp");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
    }
}