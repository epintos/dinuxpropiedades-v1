package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class PasswordsDoesNotMatchException extends Exception {

	public PasswordsDoesNotMatchException() {
	    super("Las contrase–as no coinciden");
	}

	public PasswordsDoesNotMatchException(String msg) {
	    super(msg);
	}

}
