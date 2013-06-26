package ar.edu.itba.it.paw.services.services;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.services.beans.ErrorBean;
import ar.edu.itba.it.paw.services.interfaces.ErrorManager;

public class ErrorManagerImpl implements ErrorManager {

	List<ErrorBean> errors = new ArrayList<ErrorBean>();

	public void addError(ErrorBean error) {
		errors.add(error);
	}

	public List<ErrorBean> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorBean> errors) {
		this.errors = errors;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public void resetErrors() {
		errors.clear();
	}
}
