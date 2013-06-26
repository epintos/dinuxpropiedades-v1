package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.NewAdvertisementInterf;

public class NewAvertisementService implements NewAdvertisementInterf {


	public void addAdvertisement(Property property, User u)
			throws BadInformationException, InformationMissingException,
			InternalServerError {

		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		dao.addProperty(property, u);
	}

}
