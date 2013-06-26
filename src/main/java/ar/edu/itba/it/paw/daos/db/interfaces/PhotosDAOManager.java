package ar.edu.itba.it.paw.daos.db.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Photos;
import ar.edu.itba.it.paw.services.beans.Property;

/**
 * Provides an interface for Photos.
 * 
 */
public interface PhotosDAOManager {

	/**
	 * Returns all the photos of a property.
	 * 
	 * @param propertyId
	 *            Id of the property.
	 * @return List of Photos.
	 * @throws InternalServerError 
	 */
	public List<Photos> getPhotosFor(int propertyId) throws InternalServerError;

	/**
	 * Adds photos to a property.
	 * 
	 * @param property
	 * @param photos
	 *            List of photos to add.
	 * @throws InternalServerError 
	 */
	public void addPhotosTo(Property property, List<Photos> photos) throws InternalServerError;

	/**
	 * Returns a list of the id of the photos of a property.
	 * 
	 * @param propertyId
	 *            Id of the property.
	 * @return List of ids.
	 * @throws InternalServerError 
	 */
	public List<Integer> getPhotosIdsFor(int propertyId) throws InternalServerError;

	/**
	 * Returns a photo by it's id.
	 * 
	 * @param id
	 *            Id of the photo.
	 * @return Photo
	 * @throws InternalServerError 
	 */
	public Photos getPhotoById(int id) throws InternalServerError;
	
	/**
	 * Deletes a giv
	 * */
	public void deletePhoto(int id) throws InternalServerError;

}
