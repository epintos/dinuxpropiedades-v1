package ar.edu.itba.it.paw.services.interfaces;

import java.util.List;
import java.util.Map;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Comment;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.exceptions.UnauthorizedException;

/**
 * Provides an interface to validate different things.
 * 
 */
public interface Validator {

	/**
	 * Validates an email format.
	 * 
	 * @param email
	 * @return true if the email format is correct. false if it is not correct.
	 */
	public boolean validateEmail(String email);

	/**
	 * Validates a phone format.
	 * 
	 * @param phone
	 * @return true if the phone format is correct. false if it is not correct.
	 */
	public boolean validatePhone(String phone);

	/**
	 * Validates a property's information.
	 * 
	 * @param data
	 *            Data to validate.
	 * @param services
	 * @param errorManager
	 * @return Property
	 */
	public Property validateProperty(Map<String, String> data, ErrorManager errorManager);

	/**
	 * Validates a price format.
	 * 
	 * @param price
	 * @param defaultPrice
	 *            Default price in case the price is empty.
	 * @param errorManager
	 * @param field
	 * @return price in an int format.
	 */
	public int validatePrice(String price, int defaultPrice,
			ErrorManager errorManager, String field);

	/**
	 * Validates if the data to register is correct.
	 * 
	 * @param info
	 *            Information to validate.
	 * @param errorManager
	 * @return
	 */
	public User validateRegisterParameters(Map<String, String> info,
			ErrorManager errorManager);

	/**
	 * Validates if the information when commenting is correct.
	 * 
	 * @param name
	 *            Name of the user commenting.
	 * @param email
	 *            Email of the user commenting.
	 * @param phone
	 *            Phone of the user commenting.
	 * @param errorManager
	 * @return
	 * @throws BadInformationException
	 *             If the information expected has wrong a type.
	 * @throws InformationMissingException
	 *             If it is missing some information.
	 */
	public Comment validateCommentParameters(String name, String email,
			String phone, String comment, ErrorManager errorManager);

	/**
	 * Translates some services name to a correct form.
	 * 
	 * @param services
	 * @return
	 */
	public List<Service> addCorrectServices(Map<String, String> services);

	/**
	 * Analize if a user can edit an advertisement.
	 * 
	 * @param propertyId
	 * @param user
	 * @throws InternalServerError
	 * @throws UnauthorizedException
	 *             If the user can't edit that property.
	 */
	public void analizeUserAuthorization(String propertyId, User user)
			throws InternalServerError, UnauthorizedException;

	public boolean validateLenght(String tovalidate, int n);

	public boolean between(int tocheck, int floor, int roof);

	public int validateInteger(String integerString, String field,
			ErrorManager manager);
	
	public boolean isService(String s);

}
