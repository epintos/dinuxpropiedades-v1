package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.beans.Comment;
import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.NotExistsPropertyIdException;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;
import ar.edu.itba.it.paw.services.interfaces.PostComment;
import ar.edu.itba.it.paw.services.interfaces.SearchInterface;
import ar.edu.itba.it.paw.services.interfaces.Validator;
import ar.edu.itba.it.paw.services.services.CommentAdder;
import ar.edu.itba.it.paw.services.services.ErrorManagerImpl;
import ar.edu.itba.it.paw.services.services.PhotoServerService;
import ar.edu.itba.it.paw.services.services.SearchService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class ViewAdvertisement extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name, email, telephone, comment;

		String id = req.getParameter("id");
		name = req.getParameter("name");
		email = req.getParameter("email");
		telephone = req.getParameter("phone");
		comment = req.getParameter("comment");
		SearchInterface ss = new SearchService();
		Property property = null;
		List<Service> services = null;
		List<Integer> photos = null;
		PhotoServerInterf photoServer = new PhotoServerService();
		try {
			int propertyId = Integer.parseInt(id);
			property = ss.getProperty(propertyId);
			services = ss.getServices(propertyId);
			photos = photoServer.getPhotosId(propertyId);
		} catch (NotExistsPropertyIdException e) {
			req.setAttribute("errorType", 404);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		} catch (NumberFormatException e) {
			req.setAttribute("errorType", 404);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}
		if (!photos.isEmpty()) {
			req.setAttribute("mainPhoto", photos.remove(0));
			req.setAttribute("photos", photos);
		}
		req.setAttribute("property", property);
		req.setAttribute("servicesList", services);

		UserManager manager = new SessionUserManager(req, resp);
		req.setAttribute("sessionManager", manager);
		setAttributes(id, name, email, telephone, property, comment, null, req);
		req.getRequestDispatcher(
				resp.encodeURL("/WEB-INF/jsp/viewAdvertisement.jsp")).forward(
				req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name, email, telephone, comment, operationType;

		String id = req.getParameter("id");
		name = req.getParameter("name");
		email = req.getParameter("email");
		telephone = req.getParameter("phone");
		comment = req.getParameter("comment");
		operationType = req.getParameter("operationType");
		int propertyId = Integer.parseInt(req.getParameter("propertyId"));

		SearchInterface ss = new SearchService();
		PostComment pc = new CommentAdder();
		Property property;
		try {
			property = ss.getProperty(propertyId);
			property.setOperationType(operationType);
		} catch (NotExistsPropertyIdException e) {
			req.setAttribute("errorType", 404);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		UserManager manager = new SessionUserManager(req, resp);
		req.setAttribute("sessionManager", manager);

		try {
			ErrorManager errorManager = new ErrorManagerImpl();
			Validator v = new ValidatorImpl();
			Comment c = v.validateCommentParameters(name, email, telephone,
					comment, errorManager);
			if (errorManager.hasErrors()) {
				setAttributes(id, name, email, telephone, property, comment,
						errorManager.getErrors(), req);
				req.getRequestDispatcher(
						resp.encodeURL("/WEB-INF/jsp/viewAdvertisement.jsp"))
						.forward(req, resp);
				return;
			}

			User user = pc.postComment(c, propertyId);
			req.getRequestDispatcher(
					resp.encodeURL("userInfo?userId=" + user.getId())).forward(
					req, resp);

		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

	}

	private void setAttributes(String id, String name, String email,
			String telephone, Property property, String comment,
			List<ErrorBean> error, HttpServletRequest req) {
		req.setAttribute("name", name);
		req.setAttribute("email", email);
		req.setAttribute("phone", telephone);
		req.setAttribute("property", property);
		req.setAttribute("comment", comment);
		req.setAttribute("id", id);
		if (error != null)
			for (ErrorBean e : error) {
				req.setAttribute(e.getErrorField(), e.getMessage());
			}
	}
}
