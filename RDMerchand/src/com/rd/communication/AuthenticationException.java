package com.rd.communication;

public class AuthenticationException extends RuntimeException {

	private String codigoError;
	
	public AuthenticationException(String codigoError, String mensaje) {
		super(mensaje);
		this.codigoError = codigoError;
	}
	
	public AuthenticationException(String mensaje) {
		super(mensaje);
	}
	
	public String getCodigoError() {
		return codigoError;
	}
}
