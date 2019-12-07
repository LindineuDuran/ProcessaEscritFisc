package com.lduran.escritfisc.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotaFiscServ extends ObjectBI
{
	private int itemNF                = 0;
	private String organizacao        = "";
	private String operacao           = "";
	private String emissao            = "";
	private String participante       = "";
	private String situacao           = "";
	private String numDoc             = "";
	private String dataEmissao        = "";
	private String dataMovimento      = "";
	private String pagamento          = "";
	private String produto            = "";
	private BigDecimal valorTotal     = new BigDecimal(0);
	private BigDecimal desconto       = new BigDecimal(0);
	private String baseCredito        = "";
	private String origemCredito      = "";
	private String cstPIS             = "";
	private BigDecimal basePIS        = new BigDecimal(0);
	private BigDecimal aliquotaPIS    = new BigDecimal(0);
	private BigDecimal valorPIS       = new BigDecimal(0);
	private String cstCOFINS          = "";
	private BigDecimal baseCOFINS     = new BigDecimal(0);
	private BigDecimal aliquotaCOFINS = new BigDecimal(0);
	private BigDecimal valorCOFINS    = new BigDecimal(0);
}
