package com.lduran.escritfisc.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.NotaFiscServ;
import com.lduran.escritfisc.entity.ObjectBI;
import com.lduran.escritfisc.util.DateFactory;
import com.lduran.escritfisc.util.ToolsFactory;

public class NFSServiceImpl implements ObjectService
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
		NotaFiscServ nfs = new NotaFiscServ();

		String codigo = list.get(1);
		switch (codigo)
		{
		case "A010":
			if ((list.size() > 2) && (list.get(2) != null)) {nfs.setOrganizacao(list.get(2));}

			break;

		case "A100":
			nfs.setItemNF(0);
			nfs.setOrganizacao(((NotaFiscServ) obj).getOrganizacao());

			if ((list.size() > 2) && (list.get(2) != null)) {nfs.setOperacao(list.get(2).equals("0") ? "Servico Contratado pelo Estabelecimento" : "Servico Prestado pelo Estabelecimento");}
			if ((list.size() > 3) && (list.get(3) != null)) {nfs.setEmissao(list.get(3).equals("0") ? "Propria" : "Terceiro");}
			if ((list.size() > 4) && (list.get(4) != null)) {nfs.setParticipante(list.get(4));}
			if ((list.size() > 5) && (list.get(5) != null)) {nfs.setSituacao(list.get(5).equals("00") ? "Documento regular" : "Documento cancelado");}
			if ((list.size() > 8) && (list.get(8) != null)) {nfs.setNumDoc(list.get(8));}
			if ((list.size() > 10) && (list.get(10) != null) && (list.get(10).length() == 8)) {nfs.setDataEmissao(DateFactory.getInstance().dateModelConverter(list.get(10)));}
			if ((list.size() > 11) && (list.get(11) != null) && (list.get(11).length() == 8)) {nfs.setDataMovimento(DateFactory.getInstance().dateModelConverter(list.get(11)));}
			if ((list.size() > 13) && (list.get(13) != null)) {nfs.setPagamento(ToolsFactory.getInstance().obtemIndicadorTipoPagamento(list.get(13)));}

			break;

		case "A170":
			nfs.setItemNF(((NotaFiscServ) obj).getItemNF() + 1);
			nfs.setOrganizacao(((NotaFiscServ) obj).getOrganizacao());
			nfs.setOperacao(((NotaFiscServ) obj).getOperacao());
			nfs.setEmissao(((NotaFiscServ) obj).getEmissao());
			nfs.setParticipante(((NotaFiscServ) obj).getParticipante());
			nfs.setSituacao(((NotaFiscServ) obj).getSituacao());
			nfs.setDataEmissao(((NotaFiscServ) obj).getDataEmissao());
			nfs.setDataMovimento(((NotaFiscServ) obj).getDataMovimento());
			nfs.setPagamento(((NotaFiscServ) obj).getPagamento());

			if ((list.size() > 3) && (list.get(3) != null)) {nfs.setProduto(list.get(3));}
			if ((list.size() > 5) && (list.get(5) != null) && (ToolsFactory.getInstance().isNumeric(list.get(5).replace(",", ".")))) {nfs.setValorTotal(new BigDecimal(Double.parseDouble(list.get(5).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 6) && (list.get(6) != null) && (ToolsFactory.getInstance().isNumeric(list.get(6).replace(",", ".")))) {nfs.setDesconto(new BigDecimal(Double.parseDouble(list.get(6).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 7) && (list.get(7) != null)) {nfs.setBaseCredito(ToolsFactory.getInstance().obtemBaseCalculoCredito("BCC" + list.get(7)));}
			if ((list.size() > 8) && (list.get(8) != null)) {nfs.setOrigemCredito(list.get(8).equals("0") ? "Operacao no Mercado Interno" : "Operacao de Importacao");}
			if ((list.size() > 9) && (list.get(9) != null)) {nfs.setCstPIS(list.get(9));}
			if ((list.size() > 10) && (list.get(10) != null) && (ToolsFactory.getInstance().isNumeric(list.get(10).replace(",", ".")))) {nfs.setBasePIS(new BigDecimal(Double.parseDouble(list.get(10).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 11) && (list.get(11) != null) && (ToolsFactory.getInstance().isNumeric(list.get(11).replace(",", ".")))) {nfs.setAliquotaPIS(new BigDecimal(Double.parseDouble(list.get(11).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 12) && (list.get(12) != null) && (ToolsFactory.getInstance().isNumeric(list.get(12).replace(",", ".")))) {nfs.setValorPIS(new BigDecimal(Double.parseDouble(list.get(12).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 13) && (list.get(13) != null)) {nfs.setCstCOFINS(list.get(13));}
			if ((list.size() > 14) && (list.get(14) != null) && (ToolsFactory.getInstance().isNumeric(list.get(14).replace(",", ".")))) {nfs.setBaseCOFINS(new BigDecimal(Double.parseDouble(list.get(14).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 15) && (list.get(15) != null) && (ToolsFactory.getInstance().isNumeric(list.get(15).replace(",", ".")))) {nfs.setAliquotaCOFINS(new BigDecimal(Double.parseDouble(list.get(15).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}
			if ((list.size() > 16) && (list.get(16) != null) && (ToolsFactory.getInstance().isNumeric(list.get(16).replace(",", ".")))) {nfs.setValorCOFINS(new BigDecimal(Double.parseDouble(list.get(16).replace(",", "."))).setScale(2, BigDecimal.ROUND_HALF_UP));}

			break;
		}

		return nfs;
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent)
	{
		// Apuração do PIS
		NotaFiscServ nfs = new NotaFiscServ();

		Map<String, ObjectBI> mapNotaFiscServ = fileContent.stream() // render as stream ()
				.map(line -> Arrays.asList(line.split("\\|"))) // generates a list of the columns divided in "|"
				.collect(HashMap<String, ObjectBI>::new, (map, list) ->
				{
					NotaFiscServ nfsTemp = (NotaFiscServ) this.getObject(list, nfs);

					nfs.setItemNF(nfsTemp.getItemNF() + 1);
					nfs.setOrganizacao(nfsTemp.getOrganizacao());
					nfs.setOperacao(nfsTemp.getOperacao());
					nfs.setEmissao(nfsTemp.getEmissao());
					nfs.setParticipante(nfsTemp.getParticipante());
					nfs.setSituacao(nfsTemp.getSituacao());
					nfs.setNumDoc(nfsTemp.getNumDoc());
					nfs.setDataEmissao(nfsTemp.getDataEmissao());
					nfs.setDataMovimento(nfsTemp.getDataMovimento());
					nfs.setPagamento(nfsTemp.getPagamento());

					if ((nfsTemp.getValorTotal().doubleValue() != 0))
					{
						String chave = nfsTemp.getItemNF() + nfsTemp.getOrganizacao() + nfsTemp.getOperacao() + nfsTemp.getEmissao() + nfsTemp.getParticipante() + nfsTemp.getSituacao() + nfsTemp.getNumDoc() + nfsTemp.getDataEmissao()
								+ nfsTemp.getDataMovimento() + nfsTemp.getPagamento() + nfs.getProduto();
						map.put(chave, nfsTemp);
					}

				}, (m, u) ->
				{
				}); // generate a Map<String, T> for each list of the columns generated

		List<ObjectBI> lstNotaFiscServ = new LinkedList<>();
		lstNotaFiscServ = mapNotaFiscServ.entrySet() // create a set out of the same elements contained in the hash map
				.stream() // calling the method stream() on a list of objects returns a regular
							// object stream
				.map(Map.Entry::getValue) // the Map.entrySet method returns a collection-view of the map, whose
											// elements are of this class, getValue() returns the value
											// corresponding to this entry
				.collect(Collectors.toList()); // Collectors.toList() converts Map to List

		return lstNotaFiscServ;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdOrganizacao;Operacao;Emissao;IdParticipante;IdSituacao;IdProduto;DataDaEmissao;DataDoMovimento;" + "Pagamento;CstCOFINS;CstPIS;AliquotaCOFINS;AliquotaPIS;BaseCalculoDoCredito;OrigemDoCredito;"
				+ "TotalDoItem;ValorDesconto;ValorCOFINS;ValorPIS;BaseDeCalculoCOFINS;BaseDeCalculoPIS";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		DecimalFormat df = new DecimalFormat("#0.00");

		NotaFiscServ nfs = (NotaFiscServ) obj;

		try
		{
			return nfs.getOrganizacao() + ";" + nfs.getOperacao() + ";" + nfs.getEmissao() + ";" + nfs.getParticipante() + ";" + nfs.getSituacao() + ";" + nfs.getProduto() + ";" + nfs.getDataEmissao() + ";" + nfs.getDataMovimento() + ";"
					+ nfs.getPagamento() + ";" + nfs.getCstCOFINS() + ";" + nfs.getCstPIS() + ";" + df.format(nfs.getAliquotaCOFINS()) + ";" + df.format(nfs.getAliquotaPIS()) + ";" + nfs.getBaseCredito() + ";" + nfs.getOrigemCredito() + ";"
					+ df.format(nfs.getValorTotal()) + ";" + df.format(nfs.getDesconto()) + ";" + df.format(nfs.getValorCOFINS()) + ";" + df.format(nfs.getValorPIS()) + ";" + df.format(nfs.getBaseCOFINS()) + ";"
					+ df.format(nfs.getBasePIS());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
