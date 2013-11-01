package com.rd.common;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {

	/**
	 * Combina una URL base con una relativa
	 * @param uriBase 
	 * @param uriRelativa 
	 * @return uriBase + uriRelativa, resolviendo las barras como corresponde.
	 */
	public static String combinar(String uriBase, String uriRelativa)
	{
		try {
			if (!uriBase.endsWith("/"))
				uriBase += "/";
			if (uriRelativa.startsWith("/"))
				uriRelativa = uriRelativa.substring(1);
			return new URL(new URL(uriBase), uriRelativa).toString();
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
