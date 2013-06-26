package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.DatabaseException;
import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.UserDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.UserDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;
import ar.edu.itba.it.paw.services.interfaces.RegisterInterface;

public class RegisterService implements RegisterInterface {

	private static final String ALREADY_EXISTS_USER = "El usuario ingresado ya existe";
	private static final String ALREADY_EXISTS_EMAIL = "El email ingresado ya existe";

	public void registerUser(User user, ErrorManager manager)
			throws InternalServerError {

		UserDAOManager dao = UserDAOManagerImpl.getInstance();
		try {
			if (dao.existsUser(user.getUsername())) {
				manager.addError(new ErrorBean(ALREADY_EXISTS_USER, "username"));
			}
			if (dao.existsEmail(user.getEmail())) {
				manager.addError(new ErrorBean(ALREADY_EXISTS_EMAIL,
						"emailRegister"));
			}
			if (manager.hasErrors()) {
				return;
			}
			dao.newUser(user);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}

}
