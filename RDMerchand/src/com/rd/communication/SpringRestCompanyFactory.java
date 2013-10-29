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
import com.rd.biz.company.CompanyResponse;

public class SpringRestCompanyFactory implements ClienteRestFactory {

	private Collection<HttpMessageConverter<?>> defaultMessageConverters;

	private ClientHttpRequestInterceptor ticketSesionInterceptor;
	
	
	public SpringRestCompanyFactory() {
		Gson gson = new GsonBuilder()
		.registerTypeAdapter(CompanyResponse.class, new CompanyResponseAdapter())
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
	public void iniciarSesion(TicketSesion ticketSesion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizarSesion() {
		// TODO Auto-generated method stub

	}
	
	private void configurarSesionInterceptor(RestTemplate restTemplate) {
		if (this.ticketSesionInterceptor != null) {
			restTemplate
					.setInterceptors(Arrays.asList(ticketSesionInterceptor));
		}
	}

}
