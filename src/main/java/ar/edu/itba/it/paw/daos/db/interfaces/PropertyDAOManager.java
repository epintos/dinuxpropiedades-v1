package ar.edu.itba.it.paw.daos.db.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides an interface for getting, adding and modifying properties.
 * 
 */
public interface PropertyDAOManager {

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
	 */
	public List<Property> getProperties(String operation, String propertyType,
			int priceFrom, int priceTo) throws InternalServerError;

	/**
	 * Returns a property by it's id.
	 * 
	 * @param id
	 *            of the property.
	 * @return property
	 * @throws InternalServerError 
	 * @throws BadInformationException 
	 * @throws InformationMissingException 
	 */
	public Property getProperty(int id) throws InternalServerError, InformationMissingException, BadInformationException;

	/**
	 * Returns a property services.
	 * 
	 * @param propertyId
	 * @return List of String with the services name.
	 * @throws InternalServerError 
	 */
	public List<Service> getServices(int propertyId) throws InternalServerError;

	/**
	 * Returns the properties of the user with userId.
	 * 
	 * @param userId
	 * @return List of properties.
	 * @throws InternalServerError 
	 * @throws BadInformationException 
	 * @throws InformationMissingException 
	 */
	public List<Property> getProperties(int userId) throws InternalServerError, InformationMissingException, BadInformationException;

	/**
	 * Adds a property of a user.
	 * 
	 * @param property
	 * @param user
	 *            Owner of the property.
	 * @throws InternalServerError 
	 */
	public void addProperty(Property property, User user) throws InternalServerError;

	/**
	 * Modifies the status of the property with propertyId and status.
	 * 
	 * @param propertyId
	 * @param status
	 *            (available or finished)
	 * @throws InternalServerError 
	 */
	public void modifyPropertyStatus(int propertyId, String status) throws InternalServerError;

	/**
	 * Edits a property of a user.
	 * 
	 * @param property
	 * @param user
	 *            Owner of the property.
	 * @throws InternalServerError 
	 */
	public void editProperty(Property property, User user) throws InternalServerError;

	/**
	 * Adds a comment to a property.
	 * 
	 * @param propertyId
	 *            Id of the property.
	 * @param comment
	 *            Comment to add.
	 * @throws InternalServerError 
	 */
	public void addComment(int propertyId, String comment) throws InternalServerError;

	/**
	 * Update the services of a property.
	 * 
	 * @param propertyId
	 *            Id of the property.
	 * @param services
	 *            Updated list of serveces.
	 * @throws InternalServerError 
	 */
	public void editServices(int propertyId, List<Service> services) throws InternalServerError;

}
