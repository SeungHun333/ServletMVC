package org.kim.container.controller;

import org.kim.container.dao.MemberDao;
import org.kim.container.dao.factory.DaoFactory;
import org.kim.container.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkId")
public class MemberCheckIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDao dao = new DaoFactory().memberDao();

        String id = req.getParameter("id");
        MemberService service = new MemberService(dao);
        boolean isDuplicate = service.isAvailableId(id);

        resp.setContentType("text/plain");
        resp.getWriter().write(isDuplicate ? "duplicate" : "available");
    }
}
