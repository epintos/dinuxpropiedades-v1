package ar.edu.itba.it.paw.services.beans;

import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.Validator;

public class Comment {

	private String comment;
	private String email;
	private String phone;
	private String name;
	private Validator v = new ValidatorImpl();

	public Comment(String comment, String email, String phone, String name)
			throws InformationMissingException, BadInformationException {
		if (email.isEmpty() || phone.isEmpty() || name.isEmpty())
			throw new InformationMissingException();
		this.setComment(comment);
		this.setEmail(email);
		this.setPhone(phone);
		this.setName(name);
	}

	public void setComment(String comment) throws BadInformationException {

		int maxCommentLenght = 300;
		if (v.validateLenght(comment, maxCommentLenght))
			this.comment = comment;
		else
			throw new BadInformationException();
	}

	public void setEmail(String email) throws BadInformationException {
		if (v.validateEmail(email))
			this.email = email;
		else
			throw new BadInformationException();
	}

	public void setPhone(String phone) throws BadInformationException {
		if (v.validatePhone(phone))
			this.phone = phone;
		else
			throw new BadInformationException();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

}
