package ar.edu.itba.it.paw.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;
import ar.edu.itba.it.paw.services.beans.Comment;
import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.beans.Property;
import ar.edu.itba.it.paw.services.beans.Service;
import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.UnauthorizedException;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;
import ar.edu.itba.it.paw.services.interfaces.MyPropertiesInterface;
import ar.edu.itba.it.paw.services.interfaces.Validator;
import ar.edu.itba.it.paw.services.services.MyPropertiesService;

public class ValidatorImpl implements Validator {

	private static final String INFORMATION_MISSING_MSG = "Campo vacio.";
	private static final String BAD_FORMAT = "Formato inv‡lido.";
	private static final String VERY_BIG_NUMBER = "Nœmero debe ser menor a 2147483647.";
	private static final String PASSWORD_NOT_MATCH = "Las contrase–as no coinciden.";
	private static final String VERY_LONG_STRING = "Dato debe ser menor a ";

	private final static int maxRegisterFieldLenght = 50;
	private final static int maxNeighbourhoodLenght = 50;
	private final static int maxDescriptionLenght = 512;
	private final static int maxStreetLenght = 50;
	private final static int maxApartmentLenght = 5;
	private final static int maxCommentLenght = 300;

	public boolean validateEmail(String email) {

		if(email == null)
			return false;
		return email.matches("[a-z0-9.-_]*@[a-z0-9.-_]*");
	}

	public boolean validatePhone(String phone) {
		if(phone == null)
			return false;
		return phone.matches("4-[0-9]{3}-[0-9]{4}");
	}

	public Property validateProperty(Map<String, String> data,
			ErrorManager manager) {
		Integer coveredSurf, uncoveredSurf, rooms, age, numbering, floor, price;
		String neighbourhood = data.get("neighbourhood");
		String description = data.get("description");
		String street = data.get("street");
		String propertyType = data.get("propertyType");
		String apartment = null;

		// Analizes if there is an empty field.
		Set<String> keys = data.keySet();
		for (String key : keys) {
			if (data.get(key).isEmpty() && !key.equals("apartment")
					&& !key.equals("floor") && !key.equals("description")) {
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, key));
			}
		}

		// Analizes data base constraint
		if (!validateLenght(data.get("neighbourhood"), maxNeighbourhoodLenght)) {
			manager.addError(new ErrorBean(VERY_LONG_STRING
					+ maxNeighbourhoodLenght + " caracteres.", "neighbourhood"));
		}
		if (!validateLenght(data.get("description"), maxDescriptionLenght)) {
			manager.addError(new ErrorBean(VERY_LONG_STRING
					+ maxDescriptionLenght + " caracteres.", "description"));
		}
		if (!validateLenght(data.get("apartment"), maxApartmentLenght)) {
			manager.addError(new ErrorBean(VERY_LONG_STRING
					+ maxApartmentLenght + " caracteres.", "apartment"));
		}

		if (!validateLenght(data.get("street"), maxStreetLenght)) {
			manager.addError(new ErrorBean(VERY_LONG_STRING + maxStreetLenght
					+ " caracteres.", "street"));
		}

		// Analizes if the data is a valid integer
		coveredSurf = validateInteger(data.get("coveredSurface"),
				"coveredSurface", manager);
		uncoveredSurf = validateInteger(data.get("uncoveredSurface"),
				"uncoveredSurface", manager);
		rooms = validateInteger(data.get("rooms"), "rooms", manager);
		age = validateInteger(data.get("age"), "age", manager);
		numbering = validateInteger(data.get("numbering"), "numbering", manager);
		price = validateInteger(data.get("price"), "price", manager);

		// Analizes if the floor and apartment is not empty when the property is
		// an apartment
		if (propertyType.equals("apartment")) {
			String floorString = data.get("floor");
			if (floorString.equals("")) {
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, "floor"));
			}
			floor = validateInteger(data.get("floor"), "floor", manager);
			apartment = data.get("apartment");
			if (apartment.equals(""))
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG,
						"apartment"));
		} else {
			floor = null;
		}
		if (manager.hasErrors()) {
			return null;
		}
		return new Property(neighbourhood, coveredSurf, uncoveredSurf, rooms,
				description, age, street, numbering, floor, price, apartment);
	}

	public int validatePrice(String price, int defaultPrice,
			ErrorManager manager, String field) {
		
		if(price == null)
			return -1;
		
		int ret = -1;
		if (!price.matches("[0-9]*") && !price.equals("")) {
			manager.addError(new ErrorBean(BAD_FORMAT, field));
			return ret;
		}
		try {
			ret = Integer.parseInt(price);
		} catch (NumberFormatException e) {
			if (price.equals(""))
				return defaultPrice;
			else
				manager.addError(new ErrorBean(VERY_BIG_NUMBER, field));
		}
		return ret;

	}

	public int validateInteger(String integerString, String field,
			ErrorManager manager) {
		int ret = 0;
		if (!integerString.matches("[0-9]*") && !integerString.equals("")) {
			manager.addError(new ErrorBean(BAD_FORMAT, field));
			return ret;
		}
		try {
			ret = Integer.parseInt(integerString);
		} catch (NumberFormatException e) {
			if (integerString.equals("")) {
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, field));
			} else
				manager.addError(new ErrorBean(VERY_BIG_NUMBER, field));
		}
		return ret;
	}

	public User validateRegisterParameters(Map<String, String> info,
			ErrorManager manager) {

		Set<String> set = info.keySet();
		for (String key : set) {
			if (info.get(key).equals("")) {
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, key));
			} else if (!validateLenght(info.get(key), maxRegisterFieldLenght))
				manager.addError(new ErrorBean(VERY_LONG_STRING
						+ maxRegisterFieldLenght + " caracteres.", key));
		}

		if (!info.get("password").equals(info.get("passwordConfirm"))) {
			manager.addError(new ErrorBean(PASSWORD_NOT_MATCH,
					"passwordConfirm"));
		}

		String email = info.get("emailRegister");
		if (!email.equals("") && !this.validateEmail(email)) {
			manager.addError(new ErrorBean(BAD_FORMAT, "emailRegister"));
		}

		String phone = info.get("phone");
		if (!phone.equals("") && !this.validatePhone(phone)) {
			manager.addError(new ErrorBean(BAD_FORMAT, "phone"));
		}

		if (manager.hasErrors()) {
			return null;
		}
		return new User(info.get("name"), info.get("surname"),
				info.get("emailRegister"), info.get("username"),
				info.get("password"), info.get("phone"));

	}

	public Comment validateCommentParameters(String name, String email,
			String phone, String comment, ErrorManager manager) {

		if (name != null && name.isEmpty()) {
			manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, "name"));
		}

		if (email != null) {
			if (email.isEmpty()) {
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, "email"));
			} else if (!this.validateEmail(email)) {
				manager.addError(new ErrorBean(BAD_FORMAT, "email"));
			}
		}

		if (phone != null) {
			if (phone.isEmpty()) {
				manager.addError(new ErrorBean(INFORMATION_MISSING_MSG, "phone"));
			} else if (!this.validatePhone(phone)) {
				manager.addError(new ErrorBean(BAD_FORMAT, "phone"));
			}

		}

		if (!validateLenght(comment, maxCommentLenght))
			manager.addError(new ErrorBean(VERY_LONG_STRING + maxCommentLenght
					+ " caracteres.", "comment"));

		if (manager.hasErrors()) {
			return null;
		}
		return new Comment(comment, email, phone, name);

	}

	public List<Service> addCorrectServices(Map<String, String> services) {

		List<Service> ret = new LinkedList<Service>();
		Set<String> set = services.keySet();

		for (String key : set) {
			if (isService(key)) {
				String value = services.get(key);
				if (value != null) {
						ret.add(new Service(key));
				}
			}
		}
		return ret;
	}

	public boolean isService(String s) {
		if (s.equals("cable") || s.equals("telefono") || s.equals("salon")
				|| s.equals("canchaDePaddle") || s.equals("quincho")
				|| s.equals("pileta")|| s.equals("cancha de paddle"))
			return true;
		return false;
	}

	public void analizeUserAuthorization(String propertyId, User user)
			throws InternalServerError, UnauthorizedException {
		int id = -1;
		try {
			id = Integer.parseInt(propertyId);
		} catch (NumberFormatException e) {
			throw e;
		}
		MyPropertiesInterface pi = new MyPropertiesService();
		List<Property> properties = null;
		try {
			properties = pi.getProperties(user.getId());
		} catch (InternalServerError e) {
			throw e;
		}
		boolean myProperty = false;
		for (Property property : properties) {
			if (property.getId() == id && !myProperty) {
				myProperty = true;
			}
		}
		if (!myProperty) {
			throw new UnauthorizedException();
		}

	}

	public boolean validateLenght(String tovalidate, int n) {
		return !(tovalidate.length() > n);
	}

	public boolean between(int tocheck, int floor, int roof) {
		return (tocheck >= floor) && (tocheck <= roof);

	}

}
