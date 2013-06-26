package ar.edu.itba.it.paw.services.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.exceptions.NotExistsPropertyIdException;
import ar.edu.itba.it.paw.services.interfaces.SearchInterface;

public class SearchService implements SearchInterface {

	public List<Property> getProperties(String operation, String propertyType,
			int priceFrom, int priceTo) throws InternalServerError, InformationMissingException {
		List<Property> properties = new ArrayList<Property>();
		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		if (operation.equals("all")) {
			operation = "%";
		}

		properties = dao.getProperties(operation, propertyType, priceFrom,
				priceTo);

		List<Property> apartments = new ArrayList<Property>();
		List<Property> houses = new ArrayList<Property>();
		List<Property> finished = new ArrayList<Property>();
		
		for (Property property : properties) {
			if (property.getStatus().equals("finished")) {
				finished.add(property);
			}
			if (property.getFloor() != null) {
				property.setPropertyType("apartment");
				apartments.add(property);
			} else {
				property.setPropertyType("house");
				houses.add(property);
			}
		}
		properties.removeAll(finished);
		houses.removeAll(finished);
		apartments.removeAll(finished);
		if (propertyType.equals("apartment")) {
			return apartments;
		} else if (propertyType.equals("house")) {
			return houses;
		}
		return properties;
	}

	public Property getProperty(int id) throws NotExistsPropertyIdException, InternalServerError, InformationMissingException, BadInformationException {
		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		Property property = dao.getProperty(id);
		if (property == null) {
			throw new NotExistsPropertyIdException();
		}
		if (property.getFloor() == null) {
			property.setPropertyType("house");
		} else
			property.setPropertyType("apartment");
		return property;
	}

	public List<Service> getServices(int propertyId) throws InternalServerError {
		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		return dao.getServices(propertyId);
	}

	public List<Property> getOrderedList(List<Property> propertiesList,
			String order) {
		if (order == null) {
			order = "ASC";
		}
		TreeSet<Property> set = null;
		if (order.equals("ASC")) {
			set = new TreeSet<Property>(new Comparator<Property>() {
				public int compare(Property o1, Property o2) {
					if ((o1.getPrice() - o2.getPrice()) == 0)
						return o1.getPrice();
					else
						return o1.getPrice() - o2.getPrice();
				};
			});

		} else {
			set = new TreeSet<Property>(new Comparator<Property>() {
				public int compare(Property o1, Property o2) {
					if ((o1.getPrice() - o2.getPrice()) == 0)
						return o1.getPrice();
					else
						return o2.getPrice() - o1.getPrice();
				};
			});
		}
		set.addAll(propertiesList);
		List<Property> propertiesAscendant = new ArrayList<Property>();
		propertiesAscendant.addAll(set);
		return propertiesAscendant;

	}

}
