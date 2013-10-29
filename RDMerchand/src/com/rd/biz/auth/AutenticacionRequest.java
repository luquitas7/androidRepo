package com.rd.biz.auth;

import com.google.gson.annotations.SerializedName;

public class AutenticacionRequest {

	@SerializedName("UsuarioId")
	private String usuarioId;
	@SerializedName("Pass")
	private String pass;
	

	public AutenticacionRequest(String usuarioId, String pass) {
		super();
		this.usuarioId = usuarioId;
		this.pass = pass;
	}
	
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("usuarioId: ").append(this.usuarioId)
				.append("pass: ").append(this.pass)
				.toString();
	}
}
