package com.sergiopla.users.dto;

public class Response {
	private boolean haveError;
	private String errorMsn;
	private Object object;

	public Response(boolean haveError, String errorMsn, Object object) {
		super();
		this.haveError = haveError;
		this.errorMsn = errorMsn;
		this.object = object;
	}

	public Response() {
		this(false, "", null);
	}

	public boolean isHaveError() {
		return haveError;
	}

	public void setHaveError(boolean haveError) {
		this.haveError = haveError;
	}

	public String getErrorMsn() {
		return errorMsn;
	}

	public void setErrorMsn(String errorMsn) {
		this.errorMsn = errorMsn;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Error [haveError=" + haveError + ", errorMsn=" + errorMsn + ", object=" + object + "]";
	}
}
