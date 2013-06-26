package ar.edu.itba.it.paw.daos.db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import ar.edu.itba.it.paw.daos.db.exceptions.DatabaseException;
import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.ConnectionManager;
import ar.edu.itba.it.paw.daos.db.interfaces.UserDAOManager;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

public class UserDAOManagerImpl implements UserDAOManager {

	private static UserDAOManagerImpl dao;
	private ConnectionManager cm;

	public static UserDAOManagerImpl getInstance() {
		if (dao == null) {
			dao = new UserDAOManagerImpl();
		}

		return dao;
	}

	private UserDAOManagerImpl() {
		cm = new PostgreSQLConnectionManager();
	}

	public boolean existsUser(String username) throws InternalServerError {
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select * from users where username=?");
			stmt.setString(1, username);

			ResultSet results = stmt.executeQuery();
			if (!results.next()) {
				connection.close();
				return false;
			}
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new InternalServerError();
		}
	}

	public boolean existsEmail(String email) throws InternalServerError {
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select * from users where email=?");
			stmt.setString(1, email);

			ResultSet results = stmt.executeQuery();
			if (!results.next()) {
				connection.close();
				return false;
			}
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new InternalServerError();
		}
	}

	public User getUser(String username) throws InternalServerError,
			BadInformationException, InformationMissingException {
		User user = null;
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select * from users where username=?");
			stmt.setString(1, username);

			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				user = new User(results.getString(2), results.getString(3),
						results.getString(6), results.getString(7),
						results.getString(4), results.getString(5));
				user.setId(results.getInt(1));
			}
			connection.close();
			return user;
		} catch (SQLException e) {
			throw new InternalServerError();
		}
	}

	public void newUser(User user) throws InternalServerError {
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("insert into USERS(name ,surname ,password ,phone ,email,username) values(?,?,?,?,?,?)");

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurname());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getUsername());

			stmt.execute();

			connection.close();
		} catch (PSQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}

	}

	public int getUserId(User u) throws InternalServerError {
		if (u.getId() != -1)
			return u.getId();
		else {
			Connection connection = cm.getConnection();
			PreparedStatement stmt;
			try {
				stmt = connection
						.prepareStatement("select id from users where email = ?");
				stmt.setString(1, u.getEmail());

				ResultSet result = stmt.executeQuery();
				if (result.next()) {
					connection.close();
					return result.getInt(1);
				} else {
					connection.close();
					System.out.println("No deberia pasar - getUserID!");
					return -1;
				}
			} catch (SQLException e) {
				throw new InternalServerError();
			}
		}
	}

	public User getUserById(int id) throws InternalServerError,
			BadInformationException, InformationMissingException {
		User user = null;
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select * from users where id=?");
			stmt.setInt(1, id);

			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				user = new User(results.getString(2), results.getString(3),
						results.getString(6), results.getString(7),
						results.getString(4), results.getString(5));
				user.setId(results.getInt(1));
			}
			connection.close();
			return user;
		} catch (SQLException e) {
			throw new InternalServerError();
		}
	}

}
