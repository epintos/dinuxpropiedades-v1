package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.exceptions.WrongCredentialsException;

public interface LogInManager {

	/**
	 * Logs in a user given the email and password. If any of them are
	 * incorrect, WrongCredentialsException is thrown
	 * 
	 * @param email
	 * @param password
	 * @throws InternalServerError
	 * @throws BadInformationException
	 * @throws InformationMissingException 
	 * 
	 * @throws WrongCredentialException
	 * */

    public User logInWith(String email, String password)
	    throws WrongCredentialsException, InternalServerError, BadInformationException, InformationMissingException;

}
