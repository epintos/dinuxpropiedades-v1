package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides an interface to edit an advertisement information .
 * 
 */
public interface EditInformationInterf {

	/**
	 * Edits the advertisement of property.
	 * 
	 * @param property
	 * @param services
	 * @param user
	 * @throws BadInformationException
	 *             If the information expected has wrong a type.
	 * @throws InformationMissingException
	 *             If it is missing some information.
	 * @throws InternalServerError 
	 */
	public void editAdvertisement(Property property, User user)
			throws BadInformationException, InformationMissingException, InternalServerError;
}
