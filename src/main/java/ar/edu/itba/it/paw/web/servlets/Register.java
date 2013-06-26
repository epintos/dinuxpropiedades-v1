package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.Utils;
import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;
import ar.edu.itba.it.paw.services.interfaces.RegisterInterface;
import ar.edu.itba.it.paw.services.interfaces.UserInterface;
import ar.edu.itba.it.paw.services.interfaces.Validator;
import ar.edu.itba.it.paw.services.services.ErrorManagerImpl;
import ar.edu.itba.it.paw.services.services.RegisterService;
import ar.edu.itba.it.paw.services.services.UserService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class Register extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserManager manager = new SessionUserManager(req, resp);
		req.setAttribute("sessionManager", manager);
		if (manager.existsUser()) {
			resp.sendRedirect(resp.encodeURL("../public/welcome"));
		} else
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/register.jsp")).forward(req,
					resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RegisterInterface rs = new RegisterService();
		Utils utils = new Utils();
		
		Map<String, String> info = utils.flatten(req.getParameterMap());

		try {
			ErrorManager errorManager = new ErrorManagerImpl();
			Validator v = new ValidatorImpl();
			User user = v.validateRegisterParameters(info, errorManager);
			if (errorManager.hasErrors()) {
				setParameters(info, req, errorManager.getErrors());
				req.setAttribute("errorsList", errorManager.getErrors());
				req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/register.jsp")).forward(
						req, resp);
				return;
			}
			rs.registerUser(user, errorManager);
			if (errorManager.hasErrors()) {
				setParameters(info, req, errorManager.getErrors());
				req.setAttribute("errorsList", errorManager.getErrors());
				req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/register.jsp")).forward(
						req, resp);
				return;
			}

			UserManager manager = new SessionUserManager(req, resp);
			UserInterface ui = new UserService();
			int userId = ui.getUser(user.getUsername()).getId();
			user.setId(userId);
			manager.setUser(user);

		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		resp.sendRedirect(resp.encodeURL("../public/welcome"));
	}

	private void setParameters(Map<String, String> info,
			HttpServletRequest req, List<ErrorBean> errors) {
		Set<String> set = info.keySet();
		for (String key : set) {
			req.setAttribute(key, info.get(key));
		}

		for (ErrorBean error : errors) {
			req.setAttribute(error.getErrorField(), error.getMessage());
		}

	}
	

}
