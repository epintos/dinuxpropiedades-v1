package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class Welcome extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserManager manager = new SessionUserManager(req, resp);

		req.setAttribute("sessionManager", manager);
		req.setAttribute("operationType", "all");
		req.setAttribute("propertyType", "all");
		req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/welcome.jsp")).forward(req, resp);
	}

}
