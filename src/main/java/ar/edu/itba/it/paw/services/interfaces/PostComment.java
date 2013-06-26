package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Comment;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

/**
 * Provides an interface for adding a comment to a property.
 * 
 */
public interface PostComment {

	/**
	 * Adds a comment to a property and returns the owner of that property.
	 * 
	 * @param Comment
	 * @param propertyId
	 * @return User Owner of the property.
	 * @throws BadInformationException
	 *             If the information expected has wrong a type.
	 * @throws InformationMissingException
	 *             If it is missing some information.
	 * @throws InternalServerError 
	 */
	public User postComment(Comment c, int propertyId) throws InformationMissingException,
			BadInformationException, InternalServerError;
}
