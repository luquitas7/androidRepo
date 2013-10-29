package com.rd.communication;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class TicketSesionInterceptor implements ClientHttpRequestInterceptor {

	public static final String USERID_PARAM = "usuarioId";
	public static final String PASS_PARAM = "pass";
	
	private TicketSesion ticketSesion;
	
	/**
	 * Crea una nueva instancia que usa los valores del {@link TicketSesion}
	 * indicado para setear los campos del header.
	 * 
	 * @param ticketSesion
	 *            Información de sesión de sincronización
	 */
	public TicketSesionInterceptor(TicketSesion ticketSesion) {
		this.ticketSesion = ticketSesion;
	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		return execution.execute(addHeaderParams(request), body);
	}

	private HttpRequest addHeaderParams(HttpRequest request) {
		request.getHeaders().add(USERID_PARAM, ticketSesion.getUsuarioId());
		request.getHeaders().add(PASS_PARAM, ticketSesion.getPass());
		return request;
	}

}
