package com.rd.communication;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rd.biz.company.CompanyDto;
import com.rd.biz.company.CompanyResponse;

public class CompanyResponseAdapter implements JsonDeserializer<CompanyResponse> {

	private static final String NombreObjeto = "GetCompanyListByUserResult";
	private static final String CompanyIdFieldName = "companyID";
	private static final String CompanyNameFieldName = "companyName";
	
	@Override
	public CompanyResponse deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		
		CompanyResponse resp = new CompanyResponse();
		
		if (json.isJsonPrimitive()) {
			resp.setLista(null);
		} else if (json.isJsonObject()) {
			JsonArray result = json.getAsJsonObject().getAsJsonArray("GetCompanyListByUserResult");
			CompanyDto[] list = new CompanyDto[result.size()];
			int i = 0;
			for (JsonElement jsonElement : result) {
				CompanyDto company = new CompanyDto();
				company.setCompanyID(jsonElement.getAsJsonObject().getAsJsonPrimitive(CompanyIdFieldName)
						.getAsInt());
				company.setCompanyName(jsonElement.getAsJsonObject().getAsJsonPrimitive(CompanyNameFieldName)
						.getAsString());
				list[i] = company;
				i++;
			}
			resp.setLista(list);
		} else {
			throw new JsonParseException("El formato de la respuesta no es válido");
		}
		return resp;
	}

}
