package ar.edu.itba.it.paw.services.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides an interface that returns a user properties.
 * 
 */
public interface MyPropertiesInterface {

	/**
	 * Returns the properties of the user with userId.
	 * 
	 * @param userId
	 * @return List of properties.
	 * @throws InternalServerError 
	 * @throws InformationMissingException 
	 * @throws BadInformationException 
	 */
	public List<Property> getProperties(int userId) throws InternalServerError, InformationMissingException, BadInformationException;
}
