package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.interfaces.ModifyStatusInterface;
import ar.edu.itba.it.paw.services.services.ModifyStatusService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class ModifyStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String propertyId = req.getParameter("id");
		String status = req.getParameter("status");

		ModifyStatusInterface mss = new ModifyStatusService();

		try {
			mss.modifyPropertyStatus(Integer.parseInt(propertyId), status);
		} catch (NumberFormatException e) {
			req.setAttribute("errorType", 404);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		UserManager manager = new SessionUserManager(req, resp);
		req.setAttribute("sessionManager", manager);
		resp.sendRedirect(resp.encodeURL("myProperties"));
	}
}
