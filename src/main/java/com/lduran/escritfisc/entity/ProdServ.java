package com.lduran.escritfisc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@ToString
public class ProdServ extends ObjectBI
{
	private String IdProdServ = "";
	private String descricao = "";
	private String tipoProdServ = "";
}