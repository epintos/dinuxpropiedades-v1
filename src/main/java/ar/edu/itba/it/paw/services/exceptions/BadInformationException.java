package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class BadInformationException extends RuntimeException {
	public BadInformationException() {
	}

	public BadInformationException(String msg) {
		super(msg);
	}

}
