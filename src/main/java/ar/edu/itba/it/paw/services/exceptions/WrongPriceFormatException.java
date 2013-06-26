package ar.edu.itba.it.paw.services.exceptions;


@SuppressWarnings("serial")
public class WrongPriceFormatException extends Exception {

	public WrongPriceFormatException() {
	    super();
	}

	public WrongPriceFormatException(String msg) {
	    super(msg);
	}

}
