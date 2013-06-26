package ar.edu.itba.it.paw.daos.db.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides methods to retrieve information concerning users from database
 * */

public interface UserDAOManager {

    /**
     * Checks if given username exists in the database
     * @param username The username that must be checked
     * 
     * @return true if the username exists
     * 
     * @throws InternalServerError if any error occurs while checking username
     * */
    public boolean existsUser(String username) throws InternalServerError;
    
    /**
     * Checks if given email exists in the database
     * @param username The email that must be checked
     * 
     * @return true if the email exists
     * 
     * @throws InternalServerError if any error occurs while checking email
     * */
    public boolean existsEmail(String email) throws InternalServerError;

	/**
	 * Retrieves the information assosiated with a given username
	 * 
	 * @param username
	 *            The username whose information is requested
	 * 
	 * @return User The class User with all the information
	 * 
	 * @throws InternalServerError
	 *             if any error occurs
	 * @throws BadInformationException
	 * @throws InformationMissingException 
	 * */
    public User getUser(String username) throws InternalServerError, BadInformationException, InformationMissingException;

    /**
     * Inserts all the data in the parameter {@code user} into the database. This method does not check if the username exists.
     * 
     * @param user All the information about a user to be inserted.
     * 
     * @throws InternalServerError if any error occurs
     * 
     * */
    public void newUser(User user) throws InternalServerError;

    /**
     * Retrieves the id associated to a user. The user MUST contain the mail in order to get its id
     * */
    int getUserId(User u) throws InternalServerError;

    /**
     * Retrieves all the user information assosiated with a given id
     * 
     * @param id The id of the user whose information is requested
     * 
     * @return User The class User with all the information
     * 
     * @throws InternalServerError if any error occurs
     * @throws BadInformationException 
     * @throws InformationMissingException 
     * */
    public User getUserById(int id) throws InternalServerError, BadInformationException, InformationMissingException;

}
