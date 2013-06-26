package ar.edu.itba.it.paw.services.services;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.MyPropertiesInterface;

public class MyPropertiesService implements MyPropertiesInterface {

	public List<Property> getProperties(int userId) throws InternalServerError,
			InformationMissingException, BadInformationException {
		List<Property> properties = new ArrayList<Property>();
		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		properties = dao.getProperties(userId);
		for (Property property : properties) {
			if (property.getFloor() != null) {
				property.setPropertyType("apartment");
			} else {
				property.setPropertyType("house");
			}
		}
		return properties;
	}

}
