package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.interfaces.UserDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.daos.db.managers.UserDAOManagerImpl;
import ar.edu.itba.it.paw.services.beans.Comment;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.EmailSender;
import ar.edu.itba.it.paw.services.interfaces.PostComment;

public class CommentAdder implements PostComment {

    public User postComment(Comment c, int propertyId)
	    throws InformationMissingException, BadInformationException,
	    InternalServerError {

	PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
	Property prop = dao.getProperty(propertyId);

	UserDAOManager userDAO = UserDAOManagerImpl.getInstance();
	User u = userDAO.getUserById(prop.getUserId());
//	u.setName("");
//	u.setPassword("");
//	u.setSurname("");
//	u.setUsername("");

	String mail = "Hay un usuario interesado en su propiedad ubicada en "
		+ prop.getStreet() + " " + prop.getNumbering()
		+ ". Los datos del usuario interesado son: " + "*Nombre: "
		+ c.getName() + "\n*Email: " + c.getEmail() + "\n*Telefono: " + c.getPhone();
	if(!c.getComment().isEmpty()) {
	    mail += "\n El usuario dejo un comentario: " + c.getComment();
	}

	EmailSender emailSender = new FastEmailSender();
	emailSender.sendEmail(u.getEmail(), mail);

	// if (!c.getComment()comment.isEmpty()) {
	// dao.addComment(propertyId, comment);
	// }

	return u;
    }
}
