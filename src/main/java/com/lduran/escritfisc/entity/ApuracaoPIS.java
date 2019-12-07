package com.lduran.escritfisc.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApuracaoPIS extends ObjectBI
{
	private int itemNF = 0;
	private String organizacao = " ";
	private String tipoCredito = " ";
	private String indOrigCred =  " ";
	private BigDecimal basePIS = new BigDecimal(0);
	private BigDecimal aliquotaPIS = new BigDecimal(0);
	private BigDecimal valorCreditoApur = new BigDecimal(0);
	private String tipoAjuste = " ";
	private String codAjuste = " ";
	private String descrAjuste = " ";
	private String dataRefAjuste =  " ";
	private BigDecimal valorAjuste = new BigDecimal(0);
	private BigDecimal valorAjusteAcresc = new BigDecimal(0);
	private BigDecimal valorAjustesReduc = new BigDecimal(0);
	private BigDecimal valorCreditoDifer = new BigDecimal(0);
	private BigDecimal valorCreditoDisp = new BigDecimal(0);
	private String indUtilizCreditoDisp = " ";
	private BigDecimal valorCreditoDispDesc = new BigDecimal(0);
	private BigDecimal saldoCredUtilizar = new BigDecimal(0);
	private String baseCredito = " ";
	private int cstPIS =  0;
	private BigDecimal basePisTotal = new BigDecimal(0);
	private BigDecimal basePisCumulativa = new BigDecimal(0);
	private BigDecimal basePisNaoCumulativa = new BigDecimal(0);
}