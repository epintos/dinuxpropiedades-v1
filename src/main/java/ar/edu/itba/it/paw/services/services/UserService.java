package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.UserDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.UserDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.UserInterface;

public class UserService implements UserInterface {

	public User getUserById(int userId) throws InternalServerError,
			BadInformationException, InformationMissingException {
		UserDAOManager dao = UserDAOManagerImpl.getInstance();
		User user = dao.getUserById(userId);
		return user;
	}

	public User getUser(String username) throws InternalServerError,
			BadInformationException, InformationMissingException {
		UserDAOManager dao = UserDAOManagerImpl.getInstance();
		User user = dao.getUser(username);
		return user;
	}
}
