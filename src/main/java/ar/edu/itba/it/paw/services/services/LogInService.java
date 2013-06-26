package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.DatabaseException;
import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.UserDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.UserDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.exceptions.WrongCredentialsException;
import ar.edu.itba.it.paw.services.interfaces.LogInManager;

public class LogInService implements LogInManager {

	public User logInWith(String username, String password)
			throws WrongCredentialsException, InternalServerError, BadInformationException, InformationMissingException {

		UserDAOManager dao = UserDAOManagerImpl.getInstance();

		try {
			if (dao.existsUser(username)) {
				User user = dao.getUser(username);
				if (!user.getPassword().equals(password)) {
					throw new WrongCredentialsException();
				}
				return user;
			} else
				throw new WrongCredentialsException();
		} catch (DatabaseException e) {
			throw new InternalServerError();
		}

	}

}
