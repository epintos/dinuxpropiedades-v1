package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides an interface for getting a User.
 * 
 */
public interface UserInterface {
	/**
	 * Returns an user by an user id.
	 * 
	 * @param userId
	 *            Id of the user required.
	 * @return User
	 * @throws InternalServerError 
	 * @throws BadInformationException 
	 * @throws InformationMissingException 
	 */
	public User getUserById(int userId) throws InternalServerError, BadInformationException, InformationMissingException;

	/**
	 * Returns an user by a username.
	 * 
	 * @param username
	 *            username of the user required.
	 * @return User
	 * @throws InternalServerError 
	 * @throws BadInformationException 
	 * @throws InformationMissingException 
	 */
	public User getUser(String username) throws InternalServerError, BadInformationException, InformationMissingException;

}
