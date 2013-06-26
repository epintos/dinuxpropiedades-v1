package ar.edu.itba.it.paw.services.beans;

public class ErrorBean {

	private String message;
	private String errorField;

	public ErrorBean(String message, String errorField) {
		setMessage(message);
		setErrorField(errorField);
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField + "Error";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorField() {
		return errorField;
	}

	public String getMessage() {
		return message;
	}
}
