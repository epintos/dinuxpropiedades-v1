package ar.edu.itba.it.paw.services.exceptions;

@SuppressWarnings("serial")
public class InformationMissingException extends RuntimeException {

	public InformationMissingException() {
	    super();
	}

	public InformationMissingException(String msg) {
	    super(msg);
	}

}

