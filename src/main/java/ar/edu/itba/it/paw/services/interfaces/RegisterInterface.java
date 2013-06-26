package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;

/**
 * Provides an interface for a user to register.
 * 
 */
public interface RegisterInterface {

	/**
	 * Register a new user.
	 * 
	 * @param info
	 *            The information of the new user.
	 * @param errorManager
	 * @return User
	 * @throws InternalServerError
	 */
	public void registerUser(User user, ErrorManager errorManager)
			throws InternalServerError;

}
