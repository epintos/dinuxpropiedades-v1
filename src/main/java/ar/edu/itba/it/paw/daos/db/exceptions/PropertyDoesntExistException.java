package ar.edu.itba.it.paw.daos.db.exceptions;

@SuppressWarnings("serial")
public class PropertyDoesntExistException extends Exception { 
    
    public PropertyDoesntExistException() {
	super();
    }
    
    public PropertyDoesntExistException(String message) {
	super(message);
    }
}
