package ar.edu.itba.it.paw.services.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PhotosDAOManager;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PhotosDAOManagerImple;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.Photos;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.PhotoServerInterf;

public class PhotoServerService implements PhotoServerInterf {

	public void addPhotos(List<Photos> photos, int propertyId)
			throws InternalServerError, InformationMissingException, BadInformationException {

		PhotosDAOManager dao = PhotosDAOManagerImple.getInstance();
		PropertyDAOManager propertyDao = PropertyDAOManagerImpl.getInstance();

		dao.addPhotosTo(propertyDao.getProperty(propertyId), photos);
	}

	public Photos getPhoto(int photoId) throws InternalServerError {

		PhotosDAOManager dao = PhotosDAOManagerImple.getInstance();
		return dao.getPhotoById(photoId);
	}

	public List<Integer> getPhotosId(int propertyId) throws InternalServerError {
		PhotosDAOManager dao = PhotosDAOManagerImple.getInstance();
		return dao.getPhotosIdsFor(propertyId);
	}

	public void deletePhotos(List<Integer> ids) throws InternalServerError {
		PhotosDAOManager dao = PhotosDAOManagerImple.getInstance();
		for (Integer id : ids) {
			dao.deletePhoto(id);
		}
	}
}
