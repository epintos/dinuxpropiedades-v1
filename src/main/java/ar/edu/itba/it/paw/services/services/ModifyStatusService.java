package ar.edu.itba.it.paw.services.services;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.daos.db.interfaces.PropertyDAOManager;
import ar.edu.itba.it.paw.daos.db.managers.PropertyDAOManagerImpl;
import ar.edu.itba.it.paw.services.interfaces.ModifyStatusInterface;

public class ModifyStatusService implements ModifyStatusInterface {

	public void modifyPropertyStatus(int propertyId, String status) throws InternalServerError {
		PropertyDAOManager dao = PropertyDAOManagerImpl.getInstance();
		dao.modifyPropertyStatus(propertyId,
				status.equals("available") ? "finished" : "available");
	}

}
