package com.rd.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rd.biz.auth.AutenticacionService;
import com.rd.common.Configuracion;
import com.rd.common.URLUtils;
import com.rd.communication.ClienteRestFactory;

public class BaseServiceImpl {

	protected ClienteRestFactory clienteRestFactory;
	protected Configuracion config;
	protected Logger logger;

	public BaseServiceImpl() {
	}

	protected String buildUrl(String pathServicio) {
		String url = URLUtils.combinar(this.config.getUrlBaseWebServices(),
				pathServicio);
		if (logger.isInfoEnabled())
			logger.info("    URL: {}", url);
		return url;
	}

}