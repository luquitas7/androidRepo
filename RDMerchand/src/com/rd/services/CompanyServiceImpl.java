package com.rd.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import com.rd.biz.company.CompanyResponse;
import com.rd.biz.company.CompanyService;
import com.rd.common.Configuracion;
import com.rd.communication.AuthenticationException;
import com.rd.communication.ClienteRestFactory;
import com.rd.communication.PathWebServices;

public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService {
	
	
	
	public CompanyServiceImpl(ClienteRestFactory clienteRestFactory,
			Configuracion config) {

		this.clienteRestFactory = clienteRestFactory;
		this.config = config;
		logger = LoggerFactory.getLogger("Company Logger");
	}



	@Override
	public CompanyResponse getCompaniesByUserId(int userID) {
		RestOperations restClient = this.clienteRestFactory.crear();
		
		CompanyResponse resp = restClient.getForObject(
				buildUrl(String.format("%s/%d", PathWebServices.COMPANY_LIST_BY_USERID, userID)),
				CompanyResponse.class);
		if (resp == null)
			throw new AuthenticationException(
					"Se recibio null como respuesta del web service de autenticación.");
		if (resp.errorOccurred())
			throw new AuthenticationException(resp.getCodigoError(),
					resp.getMensaje());
		
		return resp;
	}

}
