package com.lduran.escritfisc.lists;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum TipoCredito
{
	TC101 ("Credito vinculado a receita tributada no mercado interno - Aliquota Basica"),
	TC102 ("Credito vinculado a receita tributada no mercado interno - Aliquotas Diferenciadas"),
	TC103 ("Credito vinculado a receita tributada no mercado interno - Aliquota por Unidade de Produto"),
	TC104 ("Credito vinculado a receita tributada no mercado interno - Estoque de Abertura"),
	TC105 ("Credito vinculado a receita tributada no mercado interno - Aquisicao Embalagens para revenda"),
	TC106 ("Credito vinculado a receita tributada no mercado interno - Presumido da Agroindustria"),
	TC107 ("Credito vinculado a receita tributada no mercado interno - Outros Creditos Presumidos"),
	TC108 ("Credito vinculado a receita tributada no mercado interno - Importacao"),
	TC109 ("Credito vinculado a receita tributada no mercado interno - Atividade Imobiliaria"),
	TC199 ("Credito vinculado a receita tributada no mercado interno - Outros"),
	TC201 ("Credito vinculado a receita nao tributada no mercado interno - Aliquota Basica"),
	TC202 ("Credito vinculado a receita nao tributada no mercado interno - Aliquotas Diferenciadas"),
	TC203 ("Credito vinculado a receita nao tributada no mercado interno - Aliquota por Unidade de Produto"),
	TC204 ("Credito vinculado a receita nao tributada no mercado interno - Estoque de Abertura"),
	TC205 ("Credito vinculado a receita nao tributada no mercado interno - Aquisicao Embalagens para revenda"),
	TC206 ("Credito vinculado a receita nao tributada no mercado interno - Presumido da Agroindustria"),
	TC207 ("Credito vinculado a receita nao tributada no mercado interno - Outros Creditos Presumidos"),
	TC208 ("Credito vinculado a receita nao tributada no mercado interno - Importacao"),
	TC299 ("Credito vinculado a receita nao tributada no mercado interno - Outros"),
	TC301 ("Credito vinculado a receita de exportacao - Aliquota Basica"),
	TC302 ("Credito vinculado a receita de exportacao - Aliquotas Diferenciadas"),
	TC303 ("Credito vinculado a receita de exportacao - Aliquota por Unidade de Produto"),
	TC304 ("Credito vinculado a receita de exportacao - Estoque de Abertura"),
	TC305 ("Credito vinculado a receita de exportacao - Aquisicao Embalagens para revenda"),
	TC306 ("Credito vinculado a receita de exportacao - Presumido da Agroindustria"),
	TC307 ("Credito vinculado a receita de exportacao - Outros Creditos Presumidos"),
	TC308 ("Credito vinculado a receita de exportacao - Importacao"),
	TC399 ("Credito vinculado a receita de exportacao - Outros");

	private String descricao;
}