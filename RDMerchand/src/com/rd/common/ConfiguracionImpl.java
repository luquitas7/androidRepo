package com.rd.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ConfiguracionImpl implements Configuracion {

	private static final String URL_BASE_WEB_SERVICES_NOMBRE_CAMPO = "UrlBaseWebServices";
	public static final String URL_BASE_WEB_SERVICES_VALOR_DEFAULT = "http://service.devrestaurantdomain.com/rest";
	
	private SharedPreferences preferencias;
	
	public ConfiguracionImpl(Context contexto) {
		this.preferencias = PreferenceManager
				.getDefaultSharedPreferences(contexto);
	}
	
	@Override
	public String getUrlBaseWebServices() {
		return this.preferencias.getString(URL_BASE_WEB_SERVICES_NOMBRE_CAMPO,
				URL_BASE_WEB_SERVICES_VALOR_DEFAULT);
	}

}
