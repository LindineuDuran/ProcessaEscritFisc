package com.lduran.escritfisc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Estabelecimento extends ObjectBI
{
	private String cnpj = "";
	private String razaoSocial;
	private String uf = "";
	private String codigoIBGE = "";
}