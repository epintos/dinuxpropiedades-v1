package ar.edu.itba.it.paw.daos.db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.ConnectionManager;
import ar.edu.itba.it.paw.daos.db.interfaces.PhotosDAOManager;
import ar.edu.itba.it.paw.services.beans.Photos;
import ar.edu.itba.it.paw.services.beans.Property;

public class PhotosDAOManagerImple implements PhotosDAOManager {

    private static PhotosDAOManagerImple dao;
    private ConnectionManager cm;

    public static PhotosDAOManagerImple getInstance() {
	if (dao == null) {
	    dao = new PhotosDAOManagerImple();
	}

	return dao;
    }

    private PhotosDAOManagerImple() {
	cm = new PostgreSQLConnectionManager();
    }

    public void addPhotosTo(Property prop, List<Photos> photos) throws InternalServerError {
	Connection c = cm.getConnection();

	for (Photos p : photos) {
	    try {
		PreparedStatement ps = c
			.prepareStatement("insert into photos(propertyId, photo) values(?, ?)");
		ps.setInt(1, prop.getId());
		ps.setBytes(2, p.getFile());
		ps.execute();
	    } catch (SQLException e) {
		throw new InternalServerError();
	    }
	}
	try {
	    c.close();
	} catch (SQLException e) {
	    throw new InternalServerError();
	}

    }

    public List<Photos> getPhotosFor(int propertyId) throws InternalServerError {
	List<Photos> photos = new LinkedList<Photos>();

	Connection c = cm.getConnection();

	PreparedStatement stmt;
	try {
	    stmt = c.prepareStatement("select * from photos where propertyid = ?");
	    stmt.setInt(1, propertyId);

	    ResultSet results = stmt.executeQuery();

	    while (results.next()) {
		byte[] aux = results.getBytes("photo");
		Photos p = new Photos(aux, "fruta");
		photos.add(p);
	    }

	} catch (SQLException e) {
	    throw new InternalServerError();
	}
	try {
	    c.close();
	} catch (SQLException e) {
	    throw new InternalServerError();
	}
	return photos;
    }

    public List<Integer> getPhotosIdsFor(int propertyId) throws InternalServerError {
	
	List<Integer> ret = new LinkedList<Integer>();
	
	Connection c = cm.getConnection();

	PreparedStatement stmt;
	try {
	    stmt = c.prepareStatement("select * from photos where propertyid = ?");
	    stmt.setInt(1, propertyId);

	    ResultSet results = stmt.executeQuery();

	    while (results.next()) {
		ret.add(results.getInt("id"));
	    }

	} catch (SQLException e) {
	    throw new InternalServerError();
	}
	
	try {
	    c.close();
	} catch (SQLException e) {
	    throw new InternalServerError();
	}
	return ret;
    }
    
    public Photos getPhotoById(int id) throws InternalServerError {
	Connection c = cm.getConnection();

	PreparedStatement stmt;
	try {
	    stmt = c.prepareStatement("select * from photos where id = ?");
	    stmt.setInt(1, id);

	    ResultSet results = stmt.executeQuery();
	    
	    if(results.next()) {
		c.close();
		return new Photos(results.getBytes("photo"), "Img");
	    }


	} catch (SQLException e) {
	    throw new InternalServerError();
	}
	
	try {
	    c.close();
	} catch (SQLException e) {
	    throw new InternalServerError();
	}
	return null;
    }
    
    public void deletePhoto(int id) throws InternalServerError {
	Connection c = cm.getConnection();

	PreparedStatement stmt;
	try {
	    stmt = c.prepareStatement("delete from photos where id = ?");
	    stmt.setInt(1, id);

	    stmt.execute();
	} catch(SQLException e) {
	    throw new InternalServerError();
	}
	    
    }
}
