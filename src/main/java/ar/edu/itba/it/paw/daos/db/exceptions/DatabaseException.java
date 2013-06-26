package ar.edu.itba.it.paw.daos.db.exceptions;


@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

	public DatabaseException(String msg, Throwable clause) {
		super(msg, clause);
	}

}
