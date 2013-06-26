package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.interfaces.UserInterface;
import ar.edu.itba.it.paw.services.services.UserService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class UserInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int userId = Integer.parseInt(req.getParameter("userId"));

		UserInterface ui = new UserService();
		User user = null;
		try {
			user = ui.getUserById(userId);
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		UserManager manager = new SessionUserManager(req, resp);
		req.setAttribute("sessionManager", manager);
		req.setAttribute("user", user);

		req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/userInfo.jsp"))
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
