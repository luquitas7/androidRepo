package com.rd.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import com.rd.biz.auth.AutenticacionRequest;
import com.rd.biz.auth.AutenticacionResponse;
import com.rd.biz.auth.AutenticacionService;
import com.rd.common.Configuracion;
import com.rd.communication.AuthenticationException;
import com.rd.communication.ClienteRestFactory;
import com.rd.communication.PathWebServices;
import com.rd.communication.TicketSesion;

public class AutenticacionServiceImpl extends BaseServiceImpl implements AutenticacionService {

	public AutenticacionServiceImpl(ClienteRestFactory clienteRestFactory, Configuracion cfg) {
		this.clienteRestFactory = clienteRestFactory;
		this.config = cfg;
		logger = LoggerFactory.getLogger("AuthLogger");
	}

	@Override
	public AutenticacionResponse autenticar(String nombreUsuario, String password)
			throws AutenticacionException {
		
		RestOperations restClient = this.clienteRestFactory.crear();
		
		//AutenticacionRequest request = new AutenticacionRequest(nombreUsuario, password);
		
		AutenticacionResponse resp = restClient.getForObject(
				buildUrl(String.format("%s/%s/%s", PathWebServices.AUTENTICACION_PATH, nombreUsuario, password)),
				AutenticacionResponse.class);
		
		if (resp == null)
			throw new AuthenticationException(
					"Se recibio null como respuesta del web service de autenticación.");
		if (resp.errorOccurred())
			throw new AuthenticationException(resp.getCodigoError(),
					resp.getMensaje());
		
		return resp;
	}
}
