package com.rd.communication;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

	@SerializedName("CodigoError")
	private String codigoError;
	@SerializedName("Mensaje")
	private String mensaje;
	
	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Determina se hay seteado un mensaje de error.
	 * 
	 * @return {@code true} si hay un error; {@code false} en caso contrario.
	 */
	public boolean errorOccurred() {
		return codigoError != null && !codigoError.isEmpty();
	}
}
