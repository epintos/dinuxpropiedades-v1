package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.interfaces.MyPropertiesInterface;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;
import ar.edu.itba.it.paw.services.services.MyPropertiesService;
import ar.edu.itba.it.paw.services.services.PhotoServerService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class MyProperties extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserManager manager = new SessionUserManager(req, resp);
		MyPropertiesInterface ps = new MyPropertiesService();
		List<Property> propertiesList = null;

		try {
			propertiesList = ps.getProperties(manager.getUser().getId());
			loadMainPhotos(propertiesList);
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		req.setAttribute("propertiesList", propertiesList);
		req.setAttribute("operationType", "all");
		req.setAttribute("propertyType", "all");
		req.setAttribute("sessionManager", manager);
		req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/myProperties.jsp")).forward(req,
				resp);
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
