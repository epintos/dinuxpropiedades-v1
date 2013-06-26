package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.WrongCredentialsException;
import ar.edu.itba.it.paw.services.interfaces.LogInManager;
import ar.edu.itba.it.paw.services.services.LogInService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LogInManager login = new LogInService();
		UserManager manager = new SessionUserManager(req, resp);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String on = "on";
		try {
			User user = login.logInWith(username, password);
			manager.setUser(user);
			if (on.equals(req.getParameter("rememberUsername"))) {
				manager.setRememberMe();
			}

			if (on.equals(req.getParameter("rememberLogin"))) {
				manager.setKeepMeLogged();
			}

		} catch (WrongCredentialsException e) {
			URL url = new URL(req.getHeader("referer"));
			String dir = url.toString();
			if (url.getQuery() != null) {
				if (!dir.contains("wrongCredentials="))
					dir += "&wrongCredentials=1";
			} else {
				dir += "?wrongCredentials=1";
			}
			resp.sendRedirect(resp.encodeURL(dir));
			return;
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		URL url = new URL(req.getHeader("referer"));
		String dir = url.toString();

		// This is to erase the WrongCredentials error.
		if (url.getQuery() != null) {
			if (dir.contains("&wrongCredentials=")) {
				dir = (String) dir.subSequence(0, dir.length()
						- new String("&wrongCredentials=1").length());
				dir = dir + "&wrongCredentials=0";
			} else if (dir.contains("wrongCredentials=")) {
				dir = (String) dir.subSequence(0, dir.length()
						- new String("wrongCredentials=1").length());
				dir = dir + "wrongCredentials=0";
			}
		}
		resp.sendRedirect(resp.encodeURL(dir));
	}
}
