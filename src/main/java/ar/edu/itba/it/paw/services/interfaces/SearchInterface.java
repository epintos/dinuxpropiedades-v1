package ar.edu.itba.it.paw.services.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.exceptions.NotExistsPropertyIdException;

/**
 * Provides an interface for searching properties.
 * 
 */
public interface SearchInterface {

	/**
	 * Get the properties by operation, property type and between prices.
	 * 
	 * @param operation
	 *            Rent or sale.
	 * @param propertyType
	 *            House or apartment.
	 * @param priceFrom
	 * @param priceTo
	 * @return List of properties
	 * @throws InternalServerError 
	 * @throws InformationMissingException 
	 */
	public List<Property> getProperties(String operation, String propertyType,
			int priceFrom, int priceTo) throws InternalServerError, InformationMissingException;

	/**
	 * Returns a property by it's id.
	 * 
	 * @param id
	 *            of the property.
	 * @return property
	 * @throws NotExistsPropertyIdException
	 *             If the property id does not exist.
	 * @throws InformationMissingException 
	 * @throws BadInformationException 
	 */
	public Property getProperty(int id) throws NotExistsPropertyIdException, InternalServerError, InformationMissingException, BadInformationException;

	/**
	 * Returns a property services.
	 * 
	 * @param propertyId
	 * @return List of String with the services name.
	 */
	public List<Service> getServices(int propertyId) throws InternalServerError;

	/**
	 * Returns propertiesList ordered.
	 * 
	 * @param propertiesList
	 *            Original list
	 * @param order
	 *            (ASC or DES)
	 * @return List of properties ordered.
	 */
	public List<Property> getOrderedList(List<Property> propertiesList,
			String order) throws InternalServerError;
}
