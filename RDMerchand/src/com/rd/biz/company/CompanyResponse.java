package com.rd.biz.company;

import com.google.gson.annotations.SerializedName;
import com.rd.communication.BaseResponse;


public class CompanyResponse extends BaseResponse {
	
	@SerializedName("GetCompanyListByUserResult")
	private CompanyDto[] lista;

	public CompanyDto[] getLista() {
		return lista;
	}

	public void setLista(CompanyDto[] lista) {
		this.lista = lista;
	}
	
	

}
