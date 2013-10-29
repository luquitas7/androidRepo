package com.rd.biz.auth;

import com.rd.services.AutenticacionException;

public interface AutenticacionService {

	AutenticacionResponse autenticar(String nombreUsuario, String password)
			throws AutenticacionException;
}
