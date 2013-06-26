package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.EditInformationInterf;

public class EditInformationService implements EditInformationInterf {


	public void editAdvertisement(Property property, User u)
			throws BadInformationException, InformationMissingException,
			InternalServerError {


		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		dao.editProperty(property, u);

		dao.editServices(property.getId(), property.getServices());

	}

	
}
