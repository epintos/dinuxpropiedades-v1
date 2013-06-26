package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.beans.Photos;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.UnauthorizedException;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;
import ar.edu.itba.it.paw.services.interfaces.Validator;
import ar.edu.itba.it.paw.services.services.PhotoServerService;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@SuppressWarnings("serial")
public class EditPhotos extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PhotoServerInterf photoServer = new PhotoServerService();

		String id = req.getParameter("id");

		UserManager manager = new SessionUserManager(req, resp);
		Validator v1 = new ValidatorImpl();
		User user = manager.getUser();
		try {
			v1.analizeUserAuthorization(id, user);
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
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		req.setAttribute("sessionManager", manager);
		List<Integer> photos;
		try {
			photos = photoServer.getPhotosId(Integer.parseInt(id));
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
		req.setAttribute("id", id);
		req.setAttribute("photos", photos);
		req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/editPhotos.jsp"))
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int propertyId = Integer.parseInt(req.getParameter("propertyId"));

		PhotoServerInterf photoEditor = new PhotoServerService();
		try {
			List<Photos> photos = readFile(req);
			if (photos.size() == 1) {
				Photos p = photos.get(0);
				if (!p.getName().isEmpty()) {
					photoEditor.addPhotos(photos, propertyId);
				}
			} else {
				photoEditor.addPhotos(photos, propertyId);
			}
		} catch (InternalServerError e) {
			req.setAttribute("errorType", 500);
			req.getRequestDispatcher(resp.encodeURL("/WEB-INF/jsp/error.jsp"))
					.forward(req, resp);
			return;
		}

		resp.sendRedirect(resp.encodeURL("../public/viewAdvertisement?id="
				+ propertyId));
	}

	private List<Photos> readFile(HttpServletRequest request)
			throws InternalServerError {

		List<Photos> ret = new LinkedList<Photos>();

		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = (List<FileItem>) upload
					.parseRequest(request);

			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					String fileName = item.getName();
					byte[] file = item.get();
					Photos p = new Photos(file, fileName);
					ret.add(p);
				}
			}

		} catch (FileUploadException e1) {
			throw new InternalServerError();
		}

		return ret;
	}
}
