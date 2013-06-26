package ar.edu.itba.it.paw.services.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Photos;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * This interface provides methods to add or retrive photos from a given
 * property
 * */

public interface PhotoServerInterf {

    /**
     * Adds the given list of photos to a property.
     * 
     * @param photos
     *            List of photos to add
     * @param propertyId
     *            Id of the property owner of the photos
     * @throws InternalServerError 
     * @throws BadInformationException 
     * @throws InformationMissingException 
     * */

    public void addPhotos(List<Photos> photos, int propertyId) throws InternalServerError, InformationMissingException, BadInformationException;

    /**
     * Given a certain id, this method returns the corresponding photo
     * 
     * @param Id
     *            of the photo to retrieve
     * 
     * @return The requested photo or null if there is no such photo
     * */

    public Photos getPhoto(int photoId) throws InternalServerError;

    /**
     * Returns the ids of the photos corresponding to a certain property.
     * 
     * @param propertyId
     *            The id of the property
     * 
     * @retun A list with the ids of the photos corresponding to the given
     *        property
     * */
    public List<Integer> getPhotosId(int propertyId) throws InternalServerError;
    
    public void deletePhotos(List<Integer> ids) throws InternalServerError;
    

}
