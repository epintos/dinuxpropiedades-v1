package ar.edu.itba.it.paw.services.beans;

import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.interfaces.Validator;

public class Service {

	private String name;
	private Validator v = new ValidatorImpl();

	private static final int maxNameLenght = 50;

	public Service(String name) {

		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!v.isService(name))
			throw new BadInformationException();
		if (name != null && v.validateLenght(name, maxNameLenght))
			this.name = name;
	}

}
