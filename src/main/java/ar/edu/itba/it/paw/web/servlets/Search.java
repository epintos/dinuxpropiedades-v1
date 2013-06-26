package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;
import ar.edu.itba.it.paw.services.interfaces.SearchInterface;
import ar.edu.itba.it.paw.services.interfaces.Validator;
import ar.edu.itba.it.paw.services.services.ErrorManagerImpl;
import ar.edu.itba.it.paw.services.services.PhotoServerService;
import ar.edu.itba.it.paw.services.services.SearchService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class Search extends HttpServlet {

	private final static int MIN_PRICE = 0;
	private final static int MAX_PRICE = Integer.MAX_VALUE;
	private final static int INVALID_PRICE = -1;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserManager manager = new SessionUserManager(req, resp);
		req.setAttribute("sessionManager", manager);

		String operation = req.getParameter("operation");
		String propertyType = req.getParameter("property");
		String priceFrom = req.getParameter("priceFrom");
		String priceTo = req.getParameter("priceTo");
		String order = req.getParameter("order");
		
		int priceFromInt, priceToInt;

		ErrorManager errorManager = new ErrorManagerImpl();
		Validator v = new ValidatorImpl();
		priceFromInt = v.validatePrice(priceFrom, MIN_PRICE, errorManager,
				"priceFrom");
		priceToInt = v.validatePrice(priceTo, MAX_PRICE, errorManager,
				"priceTo");
		if (errorManager.hasErrors()) {
			setAttributes(errorManager.getErrors(), req);
		}
		SearchInterface ss = new SearchService();
		List<Property> propertiesOrdered = null;
		try {
			propertiesOrdered = ss.getOrderedList(ss.getProperties(operation,
					propertyType, priceFromInt, priceToInt), order);
			loadMainPhotos(propertiesOrdered);
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}
		if (priceToInt != INVALID_PRICE && priceFromInt != INVALID_PRICE)
			req.setAttribute("propertiesList", propertiesOrdered);
		req.setAttribute("sessionManager", manager);
		setAttributes(operation, propertyType, priceFrom, priceTo, req);
		req.getRequestDispatcher(
				resp.encodeURL("/WEB-INF/jsp/searchResults.jsp")).forward(req,
				resp);

	}

	private void setAttributes(String operation, String propertyType,
			String priceFrom, String priceTo, HttpServletRequest req) {
		req.setAttribute("operationType", operation);
		req.setAttribute("propertyType", propertyType);
		req.setAttribute("priceFrom", priceFrom);
		req.setAttribute("priceTo", priceTo);
	}

	private void setAttributes(List<ErrorBean> errors, HttpServletRequest req) {
		for (ErrorBean error : errors) {
			req.setAttribute(error.getErrorField(), error.getMessage());
		}
	}

	private void loadMainPhotos(List<Property> propertiesList)
			throws InternalServerError {
		PhotoServerInterf ps = new PhotoServerService();

		for (Property property : propertiesList) {
			List<Integer> photos = ps.getPhotosId(property.getId());
			if (!photos.isEmpty()) {
				property.setMainPhotoId(ps.getPhotosId(property.getId()).get(0));

			}
		}

	}

}
