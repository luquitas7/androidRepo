package com.rd.communication;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rd.biz.auth.AutenticacionResponse;

public class SpringRestTemplateFactory implements ClienteRestFactory {

	/**
	 * Colección de mensajes por defecto
	 */
	private Collection<HttpMessageConverter<?>> defaultMessageConverters;

	private ClientHttpRequestInterceptor ticketSesionInterceptor;
	
	public SpringRestTemplateFactory() {
		Gson gson = new GsonBuilder()
		.registerTypeAdapter(AutenticacionResponse.class, new AutenticacionResponseAdapter())
		.serializeNulls()
		.create();
	defaultMessageConverters = Arrays
			.<HttpMessageConverter<?>> asList(new GsonHttpMessageConverter(
					gson));
	defaultMessageConverters = Collections
			.unmodifiableCollection(defaultMessageConverters);
	}

	@Override
	public RestOperations crear() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().addAll(defaultMessageConverters);
		configurarSesionInterceptor(restTemplate);
		return restTemplate;
	}

	@Override
	public void iniciarSesion(TicketSesion ticket) {
		
		this.ticketSesionInterceptor = new TicketSesionInterceptor(ticket);
		
	}

	@Override
	public void finalizarSesion() {
		this.ticketSesionInterceptor = null;
		
	}
	
	private void configurarSesionInterceptor(RestTemplate restTemplate) {
		if (this.ticketSesionInterceptor != null) {
			restTemplate
					.setInterceptors(Arrays.asList(ticketSesionInterceptor));
		}
	}
	
	/**
	 * Determina si el factory esta siendo utilizado dentro de una sesión de
	 * sincronización.
	 * 
	 * @return {@code true} si está ejecutando en una sesión; {@code false} en
	 *         caso contrario
	 * 
	 */
	public boolean isInSession() {
		return this.ticketSesionInterceptor != null;
	}

}
