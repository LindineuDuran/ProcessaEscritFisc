package com.lduran.escritfisc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Participante extends ObjectBI
{
	private String cnpj;
	private String razaoSocial = "";
	private String codigoIBGE = "";
	private String endereco = "";
	private String bairro = "";
	private String pais = "";
}