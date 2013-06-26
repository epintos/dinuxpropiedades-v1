package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class UnauthorizedException extends Exception {

	public UnauthorizedException() {
	    super();
	}

	public UnauthorizedException(String msg) {
	    super(msg);
	}

}
