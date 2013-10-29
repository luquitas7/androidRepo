package com.rd.communication;

import org.springframework.web.client.RestOperations;

/**
 * Factory para la creación de cientes REST. Provee métodos convenientes para
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
	 * Una vez seteada la información de sesión, los clientes REST se crean para
	 * inyectar los datos de sesión en cada petición.
	 * 
	 * @param ticketSesion
	 *            sesion a ser utilizada
	 */
	void iniciarSesion(TicketSesion ticketSesion);

	/**
	 * Remueve la sesion utilizada para construir instancias. A partir de este
	 * momento, todas las instancias de clientes REST dejan de enviar la
	 * información de sesión en cada petición.
	 */
	void finalizarSesion();
}
