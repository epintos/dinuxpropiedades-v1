package ar.edu.itba.it.paw.services.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.services.beans.ErrorBean;

public interface ErrorManager {

	public void addError(ErrorBean error);

	public List<ErrorBean> getErrors();

	public void setErrors(List<ErrorBean> errors);
	
	public boolean hasErrors();
	
	public void resetErrors();
}
