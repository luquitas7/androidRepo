package com.rd.services;

public class AutenticacionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoError;
	
	public AutenticacionException(String mensaje) {
		super(mensaje);
	}

	public AutenticacionException(String mensaje, Exception excepcionOriginal) {
		super(mensaje, excepcionOriginal);
	}

	public AutenticacionException(String codigoError, String mensaje) {
		this(mensaje);
		this.codigoError = codigoError;
	}

	public String getCodigoError() {
		return codigoError;
	}
}
