package com.rd.communication;

public class TicketSesion {
	
	
	
	public TicketSesion(String usuarioId, String pass) {
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
	
	private String usuarioId;
	private String pass;
	
	
}
