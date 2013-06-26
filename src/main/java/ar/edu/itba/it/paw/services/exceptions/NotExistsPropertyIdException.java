package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class NotExistsPropertyIdException extends Exception {
	public NotExistsPropertyIdException() {
	    super();
	}

	public NotExistsPropertyIdException(String msg) {
	    super(msg);
	}

}

