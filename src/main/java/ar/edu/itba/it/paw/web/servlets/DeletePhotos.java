package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;
import ar.edu.itba.it.paw.services.services.PhotoServerService;

@SuppressWarnings("serial")
public class DeletePhotos extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Set<String> params = req.getParameterMap().keySet();
		List<Integer> delete = new LinkedList<Integer>();
		int propertyId = Integer.parseInt(req.getParameter("propertyId"));

		for (String param : params) {
			if (param.contains("photoId=")) {
				if (req.getParameter(param) != null) {
					delete.add(Integer.parseInt(param.split("=")[1]));
				}
			}
		}

		PhotoServerInterf photoSever = new PhotoServerService();
		try {
			photoSever.deletePhotos(delete);
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		resp.sendRedirect(resp.encodeURL("../public/viewAdvertisement?id="
				+ propertyId));

	}

}
