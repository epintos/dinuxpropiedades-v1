package ar.edu.itba.it.paw.daos.db.interfaces;

import java.sql.Connection;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;

/**
 * Provides a JDBC connection to a database.
 * 
 */
public interface ConnectionManager {

	/**
	 * Returns a connection.
	 * 
	 * @return Connection
	 * @throws InternalServerError 
	 */
	public Connection getConnection() throws InternalServerError;

	/**
	 * Releases a connection.
	 * 
	 * @return ConnectionManager
	 * @throws IllegalStateException
	 *             If trying to release a connection that is not in use.
	 */
	public ConnectionManager release();

	/**
	 * Holds a connection
	 * @return
	 */
	public Connection hold();

	/**
	 * Closes a connection.
	 * @throws InternalServerError 
	 */
	public void close() throws InternalServerError;

}
