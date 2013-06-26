package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.Utils;
import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.NotExistsPropertyIdException;
import ar.edu.itba.it.paw.services.exceptions.UnauthorizedException;
import ar.edu.itba.it.paw.services.interfaces.EditInformationInterf;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;
import ar.edu.itba.it.paw.services.interfaces.SearchInterface;
import ar.edu.itba.it.paw.services.interfaces.Validator;
import ar.edu.itba.it.paw.services.services.EditInformationService;
import ar.edu.itba.it.paw.services.services.ErrorManagerImpl;
import ar.edu.itba.it.paw.services.services.SearchService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class EditInformation extends HttpServlet {

	Property property;
	List<Service> servicesList;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String propertyId = req.getParameter("id");

		Validator v1 = new ValidatorImpl();
		UserManager manager = new SessionUserManager(req, resp);
		User user = manager.getUser();
		SearchInterface ss = new SearchService();
		try {
			v1.analizeUserAuthorization(propertyId, user);
		} catch (NumberFormatException e) {
			req.setAttribute("errorType", 404);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		} catch (UnauthorizedException e) {
			req.setAttribute("errorType", 401);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		try {
			property = ss.getProperty(Integer.parseInt(propertyId));
		} catch (NotExistsPropertyIdException e) {
			req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req,
					resp);
			return;
		} catch (NumberFormatException e) {
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

		try {
			servicesList = ss.getServices(Integer.parseInt(propertyId));
		} catch (NumberFormatException e) {
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
		req.setAttribute("sessionManager", manager);
		setParameters(servicesList, req);
		req.setAttribute("property", property);
		req.getRequestDispatcher(
				resp.encodeURL("/WEB-INF/jsp/editInformation.jsp")).forward(
				req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		EditInformationInterf eis = new EditInformationService();

		Utils utils = new Utils();

		Map<String, String> info = utils.flatten(req.getParameterMap());

		String propertyId = req.getParameter("propertyId");


		try {
			ErrorManager errorManager = new ErrorManagerImpl();
			Validator v = new ValidatorImpl();
			UserManager manager = new SessionUserManager(req, resp);
			req.setAttribute("sessionManager", manager);
			req.setAttribute("property", property);

			Property property = v.validateProperty(info, errorManager);

			if (errorManager.hasErrors()) {
				setErrors(errorManager.getErrors(), req);
				req.getRequestDispatcher(
						resp.encodeURL("/WEB-INF/jsp/editInformation.jsp"))
						.forward(req, resp);
				return;
			}

			property.setOperationType(info.get("operationType"));
			property.setId(Integer.parseInt(propertyId));
			property.setServices(v.addCorrectServices(info));

			eis.editAdvertisement(property, manager.getUser());
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}
		resp.sendRedirect(resp.encodeURL("../private/myProperties"));
	}

	private void setParameters(List<Service> services, HttpServletRequest req) {
		for (Service service : services) {
			if (service.getName().equals("cancha de paddle")) {
				req.setAttribute("canchaDePaddle", service.getName());
			} else
				req.setAttribute(service.getName(), service.getName());
		}
	}

	private void setErrors(List<ErrorBean> errors, HttpServletRequest req) {
		for (ErrorBean error : errors) {
			req.setAttribute(error.getErrorField(), error.getMessage());
		}
	}

}
