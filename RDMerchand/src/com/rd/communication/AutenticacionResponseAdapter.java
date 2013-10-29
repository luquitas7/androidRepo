package com.rd.communication;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rd.biz.auth.AutenticacionResponse;

public class AutenticacionResponseAdapter implements JsonDeserializer<AutenticacionResponse> {

	private static final String NombreObjeto = "GetAppUserByUserAndPasswordResult";
	private static final String NombreUsuario = "username";
	private static final String Pass = "passwordhash";
	
	@Override
	public AutenticacionResponse deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		AutenticacionResponse resp = new AutenticacionResponse();
		if (json.isJsonPrimitive()) {
			resp.setUsername(json.getAsString());
		} else if (json.isJsonObject()) {
			JsonObject result = json.getAsJsonObject().get(NombreObjeto).getAsJsonObject();
			if (result.get(NombreUsuario).isJsonPrimitive()) //just for example purpose.
				resp.setUsername(result.getAsJsonPrimitive(NombreUsuario).getAsString());
			
			resp.setPasswordhash(result.getAsJsonPrimitive(Pass).getAsString());
			resp.setFirstname(result.getAsJsonPrimitive("firstname").getAsString());
			resp.setLastname(result.getAsJsonPrimitive("lastname").getAsString());
			resp.setAppuserid(result.getAsJsonPrimitive("appuserid").getAsInt());
		} else {
			throw new JsonParseException("El formato de la respuesta no es válido");
		}
		return resp;
	}

}
