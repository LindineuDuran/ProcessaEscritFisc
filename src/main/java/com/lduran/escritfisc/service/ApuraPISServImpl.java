package com.lduran.escritfisc.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.*;
import com.lduran.escritfisc.util.DateFactory;
import com.lduran.escritfisc.util.ToolsFactory;

public class ApuraPISServImpl implements ObjectService
{
	@Override
	public ObjectBI getObject(List<String> list)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectBI getObject(List<String> list, ObjectBI obj)
	{
		ApuracaoPIS apuraPIS = new ApuracaoPIS();

		String codigo = list.get(1);
		switch (codigo)
		{
		case "M100":

			apuraPIS.setItemNF(0);

			if ((list.size() > 2) && (list.get(2) != null)) {apuraPIS.setTipoCredito(ToolsFactory.getInstance().obtemTipoCredito("TC" + list.get(2)));}
			if ((list.size() > 3) && (list.get(3) != null)) {apuraPIS.setIndOrigCred(list.get(3).equals("0") ? "Operacoes proprias" : "Evento de incorporacao, cisao ou fusao");}
			if ((list.size() > 5) && (list.get(5) != null) && (ToolsFactory.getInstance().isNumeric(list.get(5).replace(",", ".")))) {apuraPIS.setAliquotaPIS(new BigDecimal(Double.parseDouble(list.get(5).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 8) && (list.get(8) != null) && (ToolsFactory.getInstance().isNumeric(list.get(8).replace(",", ".")))) {apuraPIS.setValorCreditoApur(new BigDecimal(Double.parseDouble(list.get(8).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 9) && (list.get(9) != null) && (ToolsFactory.getInstance().isNumeric(list.get(9).replace(",", ".")))) {apuraPIS.setValorAjusteAcresc(new BigDecimal(Double.parseDouble(list.get(9).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 10) && (list.get(10) != null) && (ToolsFactory.getInstance().isNumeric(list.get(10).replace(",", ".")))) {apuraPIS.setValorAjustesReduc(new BigDecimal(Double.parseDouble(list.get(10).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 11) && (list.get(11) != null) && (ToolsFactory.getInstance().isNumeric(list.get(11).replace(",", ".")))) {apuraPIS.setValorCreditoDifer(new BigDecimal(Double.parseDouble(list.get(11).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 12) && (list.get(12) != null) && (ToolsFactory.getInstance().isNumeric(list.get(12).replace(",", ".")))) {apuraPIS.setValorCreditoDisp(new BigDecimal(Double.parseDouble(list.get(12).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 13) && (list.get(13) != null) && (ToolsFactory.getInstance().isNumeric(list.get(13).replace(",", ".")))) {apuraPIS.setIndUtilizCreditoDisp(ToolsFactory.getInstance().obtemIndicadorUtilizCreditoDisp(list.get(13)));}
			if ((list.size() > 14) && (list.get(14) != null) && (ToolsFactory.getInstance().isNumeric(list.get(14).replace(",", ".")))) {apuraPIS.setValorCreditoDispDesc(new BigDecimal(Double.parseDouble(list.get(14).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 15) && (list.get(15) != null) && (ToolsFactory.getInstance().isNumeric(list.get(15).replace(",", ".")))) {apuraPIS.setSaldoCredUtilizar(new BigDecimal(Double.parseDouble(list.get(15).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}

			break;

		case "M105":

			apuraPIS.setItemNF(((ApuracaoPIS) obj).getItemNF() + 1);
			apuraPIS.setTipoCredito(((ApuracaoPIS) obj).getTipoCredito());
			apuraPIS.setIndOrigCred(((ApuracaoPIS) obj).getIndOrigCred());
			apuraPIS.setIndUtilizCreditoDisp(((ApuracaoPIS) obj).getIndUtilizCreditoDisp());
			apuraPIS.setAliquotaPIS(((ApuracaoPIS) obj).getAliquotaPIS());

			if (apuraPIS.getItemNF() == 1) {apuraPIS.setValorAjusteAcresc(((ApuracaoPIS) obj).getValorAjusteAcresc());
				apuraPIS.setValorAjustesReduc(((ApuracaoPIS) obj).getValorAjustesReduc());
				apuraPIS.setValorCreditoDifer(((ApuracaoPIS) obj).getValorCreditoDifer());
				apuraPIS.setValorCreditoDisp(((ApuracaoPIS) obj).getValorCreditoDisp());
				apuraPIS.setValorCreditoDispDesc(((ApuracaoPIS) obj).getValorCreditoDispDesc());
				apuraPIS.setSaldoCredUtilizar(((ApuracaoPIS) obj).getSaldoCredUtilizar());}

			if ((list.size() > 2) && (list.get(2) != null)) {apuraPIS.setBaseCredito(ToolsFactory.getInstance().obtemBaseCalculoCredito("BCC" + list.get(2)));}
			if ((list.size() > 3) && (list.get(3) != null)) {apuraPIS.setCstPIS(Integer.parseInt(list.get(3)));}
			if ((list.size() > 4) && (list.get(4) != null) && (ToolsFactory.getInstance().isNumeric(list.get(4).replace(",", ".")))) {apuraPIS.setBasePisTotal(new BigDecimal(Double.parseDouble(list.get(4).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 5) && (list.get(5) != null) && (ToolsFactory.getInstance().isNumeric(list.get(5).replace(",", ".")))) {apuraPIS.setBasePisCumulativa(new BigDecimal(Double.parseDouble(list.get(5).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 6) && (list.get(6) != null) && (ToolsFactory.getInstance().isNumeric(list.get(6).replace(",", ".")))) {apuraPIS.setBasePisNaoCumulativa(new BigDecimal(Double.parseDouble(list.get(6).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 7) && (list.get(7) != null) && (ToolsFactory.getInstance().isNumeric(list.get(7).replace(",", ".")))) {apuraPIS.setBasePIS(new BigDecimal(Double.parseDouble(list.get(7).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}

			if ((apuraPIS.getAliquotaPIS().doubleValue() != 0) && (apuraPIS.getBasePisTotal().doubleValue() != 0))
			{
				BigDecimal valorCreditoApur = apuraPIS.getBasePisTotal().multiply(apuraPIS.getAliquotaPIS());
				valorCreditoApur = valorCreditoApur.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
				apuraPIS.setValorCreditoApur(valorCreditoApur);}

			break;

		case "M110":

			apuraPIS.setItemNF(((ApuracaoPIS) obj).getItemNF() + 1);
			apuraPIS.setTipoCredito(((ApuracaoPIS) obj).getTipoCredito());
			apuraPIS.setIndOrigCred(((ApuracaoPIS) obj).getIndOrigCred());
			apuraPIS.setIndUtilizCreditoDisp(((ApuracaoPIS) obj).getIndUtilizCreditoDisp());

			if ((list.size() > 2) && (list.get(2) != null)) {apuraPIS.setTipoAjuste(ToolsFactory.getInstance().obtemIndicadorTipoPagamento(list.get(2)));}
			if ((list.size() > 3) && (list.get(3) != null) && (ToolsFactory.getInstance().isNumeric(list.get(3).replace(",", ".")))) {apuraPIS.setValorAjuste(new BigDecimal(Double.parseDouble(list.get(3).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 4) && (list.get(4) != null)) {apuraPIS.setCodAjuste(list.get(4));}
			if ((list.size() > 6) && (list.get(6) != null)) {apuraPIS.setDescrAjuste(list.get(6));}
			if ((list.size() > 7) && (list.get(7) != null) && (list.get(7).length() == 8)) {apuraPIS.setDataRefAjuste(DateFactory.getInstance().dateModelConverter(list.get(7)));}

			break;
		}

		return apuraPIS;
	}

	/**
	 * Set Adjustment Information
	 *
	 * @param objectBI
	 * @param adjustmentInfo
	 * @return
	 */
	public ApuracaoPIS setAdjustmentInfo(ObjectBI objectBI, ApuracaoPIS adjustmentInfo)
	{
		((ApuracaoPIS) objectBI).setTipoAjuste(adjustmentInfo.getTipoAjuste());
		if ((((ApuracaoPIS) objectBI).getAliquotaPIS().doubleValue() != 0))
		{
			((ApuracaoPIS) objectBI).setValorAjuste(adjustmentInfo.getValorAjuste());
		}
		((ApuracaoPIS) objectBI).setCodAjuste(adjustmentInfo.getCodAjuste());
		((ApuracaoPIS) objectBI).setDescrAjuste(adjustmentInfo.getDescrAjuste());
		((ApuracaoPIS) objectBI).setDataRefAjuste(adjustmentInfo.getDataRefAjuste());

		return ((ApuracaoPIS) objectBI);
	}

	/**
	 * Set Organization Information
	 *
	 * @param objectBI
	 * @param adjustmentInfo
	 * @return
	 */
	public static ObjectBI setOrganizationInfo(ObjectBI objectBI, ObjectBI org)
	{
		((ApuracaoPIS) objectBI).setOrganizacao(((Organizacao) org).getCnpj());

		return objectBI;
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent)
	{
		// Apuração do PIS
		ApuracaoPIS apuraPIS = new ApuracaoPIS();

		Map<String, ObjectBI> mapApuracaoPIS = fileContent.stream() // render as stream ()
				.map(line -> Arrays.asList(line.split("\\|"))) // generates a list of the columns divided in "|"
				.collect(HashMap<String, ObjectBI>::new, (map, list) ->
				{
					ApuracaoPIS apuraPISTemp = (ApuracaoPIS) this.getObject(list, apuraPIS);

					apuraPIS.setItemNF(apuraPISTemp.getItemNF());
					apuraPIS.setTipoCredito(apuraPISTemp.getTipoCredito());
					apuraPIS.setIndOrigCred(apuraPISTemp.getIndOrigCred());
					apuraPIS.setAliquotaPIS(apuraPISTemp.getAliquotaPIS());
					apuraPIS.setValorCreditoApur(apuraPISTemp.getValorCreditoApur());
					apuraPIS.setValorAjusteAcresc(apuraPISTemp.getValorAjusteAcresc());
					apuraPIS.setValorAjustesReduc(apuraPISTemp.getValorAjustesReduc());
					apuraPIS.setValorCreditoDifer(apuraPISTemp.getValorCreditoDifer());
					apuraPIS.setValorCreditoDisp(apuraPISTemp.getValorCreditoDisp());
					apuraPIS.setIndUtilizCreditoDisp(apuraPISTemp.getIndUtilizCreditoDisp());
					apuraPIS.setValorCreditoDispDesc(apuraPISTemp.getValorCreditoDispDesc());
					apuraPIS.setSaldoCredUtilizar(apuraPISTemp.getSaldoCredUtilizar());
					apuraPIS.setTipoAjuste(apuraPISTemp.getTipoAjuste());
					apuraPIS.setValorAjuste(apuraPISTemp.getValorAjuste());
					apuraPIS.setCodAjuste(apuraPISTemp.getCodAjuste());
					apuraPIS.setDescrAjuste(apuraPISTemp.getDescrAjuste());
					apuraPIS.setDataRefAjuste(apuraPISTemp.getDataRefAjuste());

					if ((apuraPISTemp.getBasePIS().doubleValue() != 0))
					{
						String chave = "item" + apuraPISTemp.getItemNF();
						map.put(chave, apuraPISTemp);
					}

				}, (m, u) ->
				{
				}); // generate a Map<String, T> for each list of the columns generated

		List<ObjectBI> lstApuracaoPIS = new LinkedList<>();
		if (apuraPIS.getValorAjuste().doubleValue() != 0)
		{
			lstApuracaoPIS = mapApuracaoPIS.entrySet() // create a set out of the same elements contained in the hash map
					.stream() // calling the method stream() on a list of objects returns a regular
								// object stream
					.map(Map.Entry::getValue) // the Map.entrySet method returns a collection-view of the map, whose
												// elements are of this class, getValue() returns the value
												// corresponding to this entry
					.map(item -> this.setAdjustmentInfo(item, apuraPIS)) // set Adjustment Information
					.collect(Collectors.toList()); // Collectors.toList() converts Map to List
		}

		return lstApuracaoPIS;
	}

	@Override
	public String getObjectHeader()
	{
		return "Organizacao;TipoCredito;IndicadorOrigCred;BaseDeCalculoPIS;AliquotaPIS;ValorCreditoApur;TipoAjuste;CodAjuste;DescrAjuste;DataRefAjuste;ValorAjuste;ValorAjusteAcresc;ValorAjustesReduc;ValorCreditoDifer;ValorCreditoDisp;IndUtilizCreditoDisp;ValorCreditoDispDesc;SaldoCredUtilizar;BaseCredito;CstPIS;BasePisTotal;BasePisCumulativa;BasePisNaoCumulativa";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		ApuracaoPIS apuraPIS = (ApuracaoPIS) obj;
		DecimalFormat df = new DecimalFormat("#0.00");

		return apuraPIS.getOrganizacao() + ";" + apuraPIS.getTipoCredito() + ";" + apuraPIS.getIndOrigCred() + ";" + df.format(apuraPIS.getBasePIS()) + ";" + df.format(apuraPIS.getAliquotaPIS()) + ";"
				+ df.format(apuraPIS.getValorCreditoApur()) + ";" + apuraPIS.getTipoAjuste() + ";" + apuraPIS.getCodAjuste() + ";" + apuraPIS.getDescrAjuste() + ";" + apuraPIS.getDataRefAjuste() + ";" + df.format(apuraPIS.getValorAjuste())
				+ ";" + df.format(apuraPIS.getValorAjusteAcresc()) + ";" + df.format(apuraPIS.getValorAjustesReduc()) + ";" + df.format(apuraPIS.getValorCreditoDifer()) + ";" + df.format(apuraPIS.getValorCreditoDisp()) + ";"
				+ apuraPIS.getIndUtilizCreditoDisp() + ";" + df.format(apuraPIS.getValorCreditoDispDesc()) + ";" + df.format(apuraPIS.getSaldoCredUtilizar()) + ";" + apuraPIS.getBaseCredito() + ";" + apuraPIS.getCstPIS() + ";"
				+ df.format(apuraPIS.getBasePisTotal()) + ";" + df.format(apuraPIS.getBasePisCumulativa()) + ";" + df.format(apuraPIS.getBasePisNaoCumulativa());
	}
}
