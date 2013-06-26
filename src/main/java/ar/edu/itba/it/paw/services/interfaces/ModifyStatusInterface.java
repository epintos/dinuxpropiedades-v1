package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;

/**
 * Provides an interface to change the status of a property.
 * 
 */
public interface ModifyStatusInterface {

	/**
	 * Modifies the status of the property with propertyId and status.
	 * 
	 * @param propertyId
	 * @param status
	 *            (available or finished)
	 * @throws InternalServerError 
	 */
	public void modifyPropertyStatus(int propertyId, String status) throws InternalServerError;

}
