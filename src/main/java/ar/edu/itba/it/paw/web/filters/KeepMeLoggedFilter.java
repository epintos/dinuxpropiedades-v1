package ar.edu.itba.it.paw.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.exceptions.WrongCredentialsException;
import ar.edu.itba.it.paw.services.interfaces.LogInManager;
import ar.edu.itba.it.paw.services.services.LogInService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

public class KeepMeLoggedFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = ((HttpServletRequest) request);
		HttpServletResponse resp = ((HttpServletResponse) response);
		LogInManager um = new LogInService();
		UserManager manager = new SessionUserManager(req, resp);
		String[] userAndPass = null;
		String user;
		String pass;

		if (!manager.existsUser()) {
			Cookie[] cookies = req.getCookies();
			Cookie logInfo = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("KeepMeLogged")) {
						logInfo = c;
					}
				}

				if (logInfo != null) {

					userAndPass = logInfo.getValue().split("/");
					user = userAndPass[0];
					pass = userAndPass[1];

					try {
						User u = um.logInWith(user, pass);
						manager.setUser(u);
					} catch (WrongCredentialsException e) {
						// chain.doFilter(request, response);
					} catch (InternalServerError e) {
						request.setAttribute("errorType", 500);
						request.getRequestDispatcher("/WEB-INF/jsp/error.jsp")
								.forward(request, response);
						return;
					} catch (BadInformationException e) {
						// TODO HAY Q CHECKEAR ACA
						e.printStackTrace();
					} catch (InformationMissingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
