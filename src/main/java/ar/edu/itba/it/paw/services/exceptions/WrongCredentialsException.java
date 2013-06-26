package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class WrongCredentialsException extends Exception {

	public WrongCredentialsException() {
	    super();
	}

	public WrongCredentialsException(String msg) {
	    super(msg);
	}

}
