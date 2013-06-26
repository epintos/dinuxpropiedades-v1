package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends Exception {
	public EmailAlreadyExistsException() {
	    super();
	}

	public EmailAlreadyExistsException(String msg) {
	    super(msg);
	}

}
