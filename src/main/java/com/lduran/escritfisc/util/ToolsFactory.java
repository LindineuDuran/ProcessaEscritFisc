package com.lduran.escritfisc.util;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.lduran.escritfisc.lists.AvailableProcesses;
import com.lduran.escritfisc.lists.BaseCalculoCredito;
import com.lduran.escritfisc.lists.TipoCredito;
import com.lduran.escritfisc.lists.TipoItem;

public class ToolsFactory
{
	private static ToolsFactory instance;

	/**
	 * @return the instance
	 */
	public static ToolsFactory getInstance()
	{
		if (instance == null) {instance = new ToolsFactory();}

		return instance;
	}

	/**
	 * Get the groups of the listed AvailableProcesses
	 *
	 * @param selectedValuesList
	 * @return
	 */
	public List<String> obtemAvailableProcesses(List<String> selectedValuesList)
	{
		List<String> grupo = new LinkedList<>();

		for (Object item : selectedValuesList)
		{
			grupo.addAll(AvailableProcesses.stream().filter(d -> d.name().equals(item.toString())).map(d -> d.getGrupo()).flatMap(x -> x.stream()).collect(Collectors.toList()));
		}
		return grupo;
	}

	/**
	 * Get the groups of the listed AvailableProcesses
	 *
	 * @param selectedValuesList
	 * @return
	 */
	public List<String> obtemAvailableProcesses(Object selectedItem)
	{
		List<String> grupo = new LinkedList<>();
		grupo.addAll(AvailableProcesses.stream()
				.filter(d -> d.name().equals(selectedItem.toString()))
				.map(d -> d.getGrupo()).flatMap(x -> x.stream())
				.collect(Collectors.toList()));

		return grupo;
	}

	/**
	 * Create RegEx from List<String>
	 * @param grupo
	 * @return
	 */
	public Pattern makePattern(List<String> grupo)
	{
		//Ex.: grupo = {"|9900|", "|C500|", "|D100|", "|D500|", "|D990|"};
		StringBuilder stb = new StringBuilder("");
		Pattern ptn = Pattern.compile("9999");

		for(String grp : grupo)
		{
			// Ensures no null items will come
			if(grp != null)
			{
				// Ex.: "\\|9900\\|"
				grp = grp.replace("|", "\\|");

				if(stb.length() == 0)
				{
					// Ex.: "^\\|9900\\|"
					stb.append("^");
					stb.append(grp);
				}
				else
				{
					// Ex.: "^\\|9900\\||^\\|C500\\||^\\|D500\\|"
					stb.append("|");
					stb.append("^");
					stb.append(grp);
				}
			}

			// Get the pattern
			ptn = Pattern.compile(stb.toString());
		}

		return ptn;
	}

	/**
	 * Obtem a descrição do enum TipoItem
	 * 
	 * @param codigo
	 * @return
	 */
	public String obtemTipoItemCredito(String codigo)
	{
		Optional<String> descricao = EnumSet.allOf(TipoItem.class).stream()
				.filter(bcc -> bcc.name().contains(codigo)).findFirst().map(TipoItem::getDescricao);

		return descricao.get();
	}

	/**
	 * Obtem o Indicador do tipo de pagamento
	 * 
	 * @param codigo
	 * @return
	 */
	public String obtemIndicadorTipoPagamento(String codigo)
	{
		String descricao = "";

		switch(codigo)
		{
		case "0": descricao = "A vista"; break;
		case "1": descricao = "A prazo"; break;
		case "9": descricao = "Sem pagamento"; break;
		}

		return descricao;
	}

	/**
	 * Check if a string is numeric [with regex]
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

	/**
	 * Obtem a descrição do enum BaseCalculoCredito
	 * 
	 * @param codigo
	 * @return
	 */
	public String obtemBaseCalculoCredito(String codigo)
	{
		Optional<String> descricao = EnumSet.allOf(BaseCalculoCredito.class).stream()
				.filter(bcc -> bcc.name().contains(codigo)).findFirst().map(BaseCalculoCredito::getDescricao);

		return descricao.get();
	}

	/**
	 * Obtem a descrição do enum TipoCredito
	 * 
	 * @param codigo
	 * @return
	 */
	public String obtemTipoCredito(String codigo)
	{
		Optional<String> descricao = EnumSet.allOf(TipoCredito.class).stream()
				.filter(bcc -> bcc.name().contains(codigo)).findFirst().map(TipoCredito::getDescricao);

		return descricao.get();
	}

	/**
	 * Obtem o Indicador de Utilização Crédito Disponível
	 * @param codigo
	 * @return
	 */
	public String obtemIndicadorUtilizCreditoDisp(String codigo)
	{
		String descricao = "";

		switch(codigo)
		{
		case "0": descricao = "Utilizacao do valor total para desconto da contribuicao apurada no periodo"; break;
		case "1": descricao = "Utilizacao de valor parcial para desconto da contribuicao apurada no periodo"; break;
		}

		return descricao;
	}
}