package ar.edu.itba.it.paw.daos.db.exceptions;

@SuppressWarnings("serial")
public class InternalServerError extends Exception {

	public InternalServerError() {
		super();
	}

	public InternalServerError(String message) {
		super(message);
	}

}
