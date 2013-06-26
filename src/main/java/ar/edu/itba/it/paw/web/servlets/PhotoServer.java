package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Photos;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;
import ar.edu.itba.it.paw.services.services.PhotoServerService;

@SuppressWarnings("serial")
public class PhotoServer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int photoId = Integer.parseInt(req.getParameter("photoId"));

		PhotoServerInterf server = new PhotoServerService();

		Photos photo;
		try {
			photo = server.getPhoto(photoId);
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		resp.setContentType("image/jpeg");
		OutputStream stream = resp.getOutputStream();

		byte[] bytes = photo.getFile();
		stream.write(bytes);
	}

}
