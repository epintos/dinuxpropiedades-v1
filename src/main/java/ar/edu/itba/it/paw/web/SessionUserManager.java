package ar.edu.itba.it.paw.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.services.beans.User;

public class SessionUserManager implements UserManager {

    private User user;
    private HttpServletRequest request;
    private HttpSession session;
    private HttpServletResponse response;

    public SessionUserManager(HttpServletRequest request,
	    HttpServletResponse response) {
	this.request = request;
	session = request.getSession();
	User u = (User) session.getAttribute("USERNAME");
	user = u;
	this.response = response;
    }

    public SessionUserManager(HttpSession session) {
	this.session = session;
    }

    public boolean existsUser() {
	return user != null;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
	session.setAttribute("USERNAME", user);

    }

    public void logout() {
	session.invalidate();
	Cookie keepMeLogged = new Cookie("KeepMeLogged", " ");
	keepMeLogged.setMaxAge(0);
	keepMeLogged.setPath(request.getContextPath());
	response.addCookie(keepMeLogged);
    }

    public void setKeepMeLogged() {
	User user = (User) session.getAttribute("USERNAME");
	Cookie logged = new Cookie("KeepMeLogged", user.getUsername() + "/"
		+ user.getPassword());
	logged.setMaxAge(60 * 60 * 24);
	logged.setPath(request.getContextPath());
	response.addCookie(logged);
    }

    public void setRememberMe() {
	User user = (User) session.getAttribute("USERNAME");
	Cookie rememberMe = new Cookie("RememberMePlz", user.getUsername());
	rememberMe.setMaxAge(60 * 60 * 24);
	rememberMe.setPath(request.getContextPath());
	response.addCookie(rememberMe);
    }

}
