package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
	    super("El usuario ingresado ya existe");
	}

	public UserAlreadyExistsException(String msg) {
	    super(msg);
	}

}
