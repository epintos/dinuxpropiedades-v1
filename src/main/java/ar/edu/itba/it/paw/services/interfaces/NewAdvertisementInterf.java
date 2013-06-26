package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides an interface to generate a new advertisement
 * 
 */
public interface NewAdvertisementInterf {

	/**
	 * Generates a new advertisement for property and user. Also adds the
	 * services for that property.
	 * 
	 * @param property
	 * @param services
	 *            List of serveces.
	 * @param user
	 *            Owner of the property.
	 * @throws BadInformationException
	 *             If the information expected has wrong a type.
	 * @throws InformationMissingException
	 *             If it is missing some information.
	 * @throws InternalServerError 
	 */
	public void addAdvertisement(Property property, User user)
			throws BadInformationException, InformationMissingException, InternalServerError;

}
