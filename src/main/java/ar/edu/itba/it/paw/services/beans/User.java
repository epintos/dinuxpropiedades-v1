package ar.edu.itba.it.paw.services.beans;

import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.Validator;

public class User {

	private int id = -1;
	private String name;
	private String surname;
	private String email;
	private String username;
	private String password;
	private String phone;
	private Validator v = new ValidatorImpl();

	private final static int maxUsernameLenght = 50;
	private final static int maxNameLenght = 50;
	private final static int maxSurnameLenght = 50;
	private final static int maxPasswordLenght = 50;
	private final static int maxEmailLenght = 50;

	public User(String name, String surname, String email, String username,
			String password, String phone) throws BadInformationException,
			InformationMissingException {

		setEmail(email);
		setName(name);
		setPassword(password);
		setPhone(phone);
		setSurname(surname);
		setUsername(username);

	}

	public void setEmail(String email) throws BadInformationException,
			InformationMissingException {
		if (email.equals(""))
			throw new InformationMissingException();
		if (v.validateEmail(email))
			this.email = email;
		else
			throw new BadInformationException();
		if (!v.validateLenght(email, maxEmailLenght))
			throw new BadInformationException();
	}

	public void setName(String name) throws BadInformationException,
			InformationMissingException {
		if (!v.validateLenght(name, maxNameLenght))
			throw new BadInformationException();

		if (!name.equals(""))
			this.name = name;
		else
			throw new InformationMissingException();
	}

	public void setPassword(String password)
			throws InformationMissingException, BadInformationException {
		if (!v.validateLenght(password, maxPasswordLenght))
			throw new BadInformationException();

		if (!password.equals(""))
			this.password = password;
		else
			throw new InformationMissingException();
	}

	public void setPhone(String phone) throws BadInformationException,
			InformationMissingException {
		if (phone.equals(""))
			throw new InformationMissingException();

		if (v.validatePhone(phone)) {
			this.phone = phone;
		} else
			throw new BadInformationException();
	}

	public void setSurname(String surname) throws BadInformationException,
			InformationMissingException {
		if (!v.validateLenght(surname, maxSurnameLenght))
			throw new BadInformationException();

		if (!surname.equals(""))
			this.surname = surname;
		else
			throw new InformationMissingException();

	}

	public void setUsername(String username) throws BadInformationException,
			InformationMissingException {
		if (!v.validateLenght(username, maxUsernameLenght))
			throw new BadInformationException();

		if (!username.equals(""))
			this.username = username;
		else
			throw new InformationMissingException();

	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public int getId() {
		return id;
	}

}