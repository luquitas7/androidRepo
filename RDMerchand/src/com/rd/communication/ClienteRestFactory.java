package com.rd.communication;

import org.springframework.web.client.RestOperations;

/**
 * Factory para la creaci�n de cientes REST. Provee m�todos convenientes para
 * crear instancias de {@link RestOperations}
 */
public interface ClienteRestFactory {
	/**
	 * Retorna una instancia de {@link RestOperations} con una configuracion por
	 * defecto.
	 * 
	 * @return instancia de {@link RestOperations}
	 */
	RestOperations crear();

	/**
	 * Establece la sesion para ser utilizada en la construccion de instancias.
	 * Una vez seteada la informaci�n de sesi�n, los clientes REST se crean para
	 * inyectar los datos de sesi�n en cada petici�n.
	 * 
	 * @param ticketSesion
	 *            sesion a ser utilizada
	 */
	void iniciarSesion(TicketSesion ticketSesion);

	/**
	 * Remueve la sesion utilizada para construir instancias. A partir de este
	 * momento, todas las instancias de clientes REST dejan de enviar la
	 * informaci�n de sesi�n en cada petici�n.
	 */
	void finalizarSesion();
}
