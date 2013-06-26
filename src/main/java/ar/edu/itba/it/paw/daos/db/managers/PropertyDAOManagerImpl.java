package ar.edu.itba.it.paw.daos.db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.ConnectionManager;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.interfaces.UserDAOManager;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

public class PropertyDAOManagerImpl implements PropertyDAOManager {

	private static PropertyDAOManagerImpl dao;
	private ConnectionManager cm;

	public static PropertyDAOManagerImpl getInstance() {
		if (dao == null) {
			dao = new PropertyDAOManagerImpl();
		}

		return dao;
	}

	private PropertyDAOManagerImpl() {
		cm = new PostgreSQLConnectionManager();
	}

	public List<Property> getProperties(String operation, String propertyType,
			int priceFrom, int priceTo) throws InternalServerError {
		List<Property> properties = new ArrayList<Property>();
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select * from property,property_operation,operation where property.id=property_operation.propertyId and operation.id=property_operation.operationId and operation.operationName like ? and price between ? and ?");
			stmt.setString(1, operation);
			stmt.setInt(2, priceFrom);
			stmt.setInt(3, priceTo);

			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Integer floor = results.getInt(10);
				floor = results.wasNull() ? null : results.getInt(10);
				Property property = new Property(results.getString(2),
						results.getInt(3), results.getInt(4),
						results.getInt(5), results.getString(6),
						results.getInt(7), results.getString(8),
						results.getInt(9), floor, results.getInt(12),
						results.getString(11));
				property.setOperationType(results.getString(19));
				property.setId(results.getInt(1));
				property.setStatus(results.getString(17));
				properties.add(property);
			}

			connection.close();
		} catch (BadInformationException e) {
			throw new InternalServerError();
		} catch (InformationMissingException e) {
			throw new InternalServerError();
		} catch (SQLException e) {
			throw new InternalServerError();
		}
		return properties;
	}

	public List<Property> getProperties(int userId) throws InternalServerError, InformationMissingException, BadInformationException {
		List<Property> properties = new ArrayList<Property>();
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select * from property,operation,property_operation where operation.id=property_operation.operationId and property.id=property_operation.propertyId and property_operation.userId=?");
			stmt.setInt(1, userId);

			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Integer floor = results.getInt(10);
				floor = results.wasNull() ? null : results.getInt(10);
				Property property = new Property(results.getString(2),
						results.getInt(3), results.getInt(4),
						results.getInt(5), results.getString(6),
						results.getInt(7), results.getString(8),
						results.getInt(9), floor, results.getInt(12),
						results.getString(11));
				property.setOperationType(results.getString(14));
				property.setId(results.getInt(1));
				property.setStatus(results.getString(19));
				properties.add(property);
			}
			connection.close();
		} catch (SQLException e) {
			throw new InternalServerError();
		}
		return properties;
	}

	public Property getProperty(int id) throws InternalServerError,
			InformationMissingException, BadInformationException {
		Property property = null;
		try {
			Connection connection = cm.getConnection();
			System.out.println();
			PreparedStatement stmt = connection
					.prepareStatement("select * from property, property_operation,operation where operation.id=property_operation.operationId and property.id=property_operation.propertyId and property.id=?");
			stmt.setInt(1, id);

			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				Integer floor = results.getInt(10);
				floor = results.wasNull() ? null : results.getInt(10);
				property = new Property(results.getString(2),
						results.getInt(3), results.getInt(4),
						results.getInt(5), results.getString(6),
						results.getInt(7), results.getString(8),
						results.getInt(9), floor, results.getInt(12),
						results.getString(11));
				property.setId(results.getInt(1));
				property.setUserId(results.getInt(16));
				property.setOperationType(results.getString(19));
			}
			connection.close();
		} catch (SQLException e) {
			throw new InternalServerError();
		}
		return property;
	}

	public List<Service> getServices(int propertyId) throws InternalServerError {
		List<Service> services = new ArrayList<Service>();
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select serviceName from property,service,service_provided where service.id=service_provided.serviceId and service_provided.propertyId=property.id and property.id=?");
			stmt.setInt(1, propertyId);

			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				String service = results.getString(1);
				services.add(new Service(service));
			}
			connection.close();
		} catch (SQLException e) {
			throw new InternalServerError();
		}
		return services;
	}

	public void modifyPropertyStatus(int propertyId, String status)
			throws InternalServerError {
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("update property_operation set status = ? where propertyId=?");
			stmt.setString(1, status);
			stmt.setInt(2, propertyId);
			stmt.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new InternalServerError();
		}

	}

	public void addProperty(Property prop, User user)
			throws InternalServerError {

		Connection c = cm.getConnection();

		try {
			c.setAutoCommit(false);
			addProp(prop, c);
			addServices(prop, c);
			addOperation(prop, c, user);
			c.commit();
			c.setAutoCommit(true);
			c.close();
		} catch (SQLException e) {
			throw new InternalServerError();
		}

	}

	private void addProp(Property prop, Connection c)
			throws InternalServerError {

		try {
			PreparedStatement ps = c
					.prepareStatement(
							"INSERT into PROPERTY(neighbourhood, coveredSurface, uncoveredSurface, rooms, description, age, street, numbering, floor, apartment, price) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, prop.getNeighbourhood());
			ps.setInt(2, prop.getCoveredsurface());
			ps.setInt(3, prop.getUncoveredsurface());
			ps.setInt(4, prop.getRooms());
			ps.setString(5, prop.getDescription());
			ps.setInt(6, prop.getAge());
			ps.setString(7, prop.getStreet());
			ps.setInt(8, prop.getNumbering());

			if (prop.getFloor() == null) {
				ps.setNull(9, java.sql.Types.INTEGER);
			} else
				ps.setInt(9, prop.getFloor());
			ps.setString(10, prop.getApartment());
			ps.setInt(11, prop.getPrice());

			ps.execute();
			ResultSet key = ps.getGeneratedKeys();
			if (key.next()) {
				prop.setId(key.getInt(1));
			}

		} catch (SQLException e) {
			throw new InternalServerError();
		}
	}

	private void addServices(Property prop, Connection c)
			throws InternalServerError {

		List<Service> services = prop.getServices();
		if (services == null)
			return;
		for (Service service : services) {
			int id;
			try {
				id = getServiceID(service.getName());
				PreparedStatement ps = c
						.prepareStatement("insert into service_provided(serviceid, propertyid) values(?, ?)");
				ps.setInt(1, id);
				ps.setInt(2, prop.getId());
				ps.execute();
			} catch (SQLException e) {
				throw new InternalServerError();
			}
		}

	}

	private int getServiceID(String service) throws SQLException,
			InternalServerError {
		Connection c = cm.getConnection();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement("select id from service where servicename = ?");
			ps.setString(1, service);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				c.close();
				return result.getInt(1);
			}
		} catch (SQLException e) {
			throw new InternalServerError();
		}
		c.close();
		return -1;

	}

	private void addOperation(Property prop, Connection c, User u)
			throws InternalServerError {
		UserDAOManager dao = UserDAOManagerImpl.getInstance();
		int userId = dao.getUserId(u);
		int opId = getOperationId(prop.getOperationType());

		PreparedStatement ps;
		try {
			ps = c.prepareStatement("insert into property_operation(operationid, propertyid, userid, status) values(?, ?, ?, ?)");
			ps.setInt(1, opId);
			ps.setInt(2, prop.getId());
			ps.setInt(3, userId);
			ps.setString(4, "available");
			ps.execute();

		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

	private int getOperationId(String operationType) throws InternalServerError {
		Connection c = cm.getConnection();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement("select id from operation where operationname = ?");
			ps.setString(1, operationType);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				c.close();
				return result.getInt(1);
			}
		} catch (Exception e) {
			throw new InternalServerError();
		}
		try {
			c.close();
		} catch (SQLException e) {
			throw new InternalServerError();
		}
		return -1;
	}

	public void editProperty(Property property, User user)
			throws InternalServerError {
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("update property set neighbourhood=?, coveredSurface=? , uncoveredSurface=? , rooms=? , description=? , age=? , street=? , numbering=? , floor=? , apartment=? , price=? where id=?");
			stmt.setString(1, property.getNeighbourhood());
			stmt.setInt(2, property.getCoveredsurface());
			stmt.setInt(3, property.getUncoveredsurface());
			stmt.setInt(4, property.getRooms());
			stmt.setString(5, property.getDescription());
			stmt.setInt(6, property.getAge());
			stmt.setString(7, property.getStreet());
			stmt.setInt(8, property.getNumbering());
			if (property.getFloor() == null) {
				stmt.setNull(9, java.sql.Types.INTEGER);
			} else
				stmt.setInt(9, property.getFloor());
			stmt.setString(10, property.getApartment());
			stmt.setInt(11, property.getPrice());
			stmt.setInt(12, property.getId());
			stmt.executeUpdate();

			PreparedStatement stmt2 = connection
					.prepareStatement("update property_operation set operationId=? where propertyId=?");
			stmt2.setInt(1, getOperationId(property.getOperationType()));
			stmt2.setInt(2, property.getId());
			stmt2.executeUpdate();

			connection.close();

		} catch (SQLException e) {
			throw new InternalServerError();
		}

	}

	public void editServices(int propertyId, List<Service> services)
			throws InternalServerError {
		try {
			Connection connection = cm.getConnection();
			PreparedStatement stmt1 = connection
					.prepareStatement("delete from service_provided where propertyId=?");
			stmt1.setInt(1, propertyId);
			stmt1.execute();

			for (Service service : services) {

				PreparedStatement stmt2 = connection
						.prepareStatement("insert into service_provided(serviceId,propertyId) values(?,?)");
				stmt2.setInt(1, getServiceID(service.getName()));
				stmt2.setInt(2, propertyId);
				stmt2.execute();

			}
			connection.close();

		} catch (SQLException e) {
			throw new InternalServerError();
		}

	}

	public void addComment(int propertyId, String comment)
			throws InternalServerError {

		return;
		// Connection c = cm.getConnection();
		//
		// PreparedStatement ps;
		// try {
		// ps =
		// c.prepareStatement("insert into comments(propertyId, comment) values(?, ?)");
		// ps.setInt(1, propertyId);
		// ps.setString(2, comment);
		// ps.execute();
		// c.close();
		// } catch (Exception e) {
		// throw new InternalServerError();
		// }
	}

}
