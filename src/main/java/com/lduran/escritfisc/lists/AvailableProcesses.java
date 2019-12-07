package com.lduran.escritfisc.lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public enum AvailableProcesses
{	
	Organizacoes("|0000|", "|0005|"),
	Estabelecimentos("|0140|"),
	Participantes("|0150|"),
	ProdServ("|0200|"),
	NotaFiscalDeServico("|A010|", "|A100|", "|A170|"),
	ApuracaoPIS("|M100|", "|M105|", "|M110|");

	private String[] grupo;

	/**
	 * @param grupo
	 */
	private AvailableProcesses(String... grupo)
	{
		this.grupo = grupo;
	}

	/**
	 * Make a List<String> of groups
	 * 
	 * @return the grupo
	 */
	public List<String> getGrupo()
	{
		List<String> lstGrupo = new LinkedList<>();

		for (String grp : Arrays.asList(this.grupo))
		{
			lstGrupo.add(grp);
		}

		return lstGrupo;
	}

	/**
	 * Iterate the Enum Using Stream
	 * https://www.baeldung.com/java-enum-iteration
	 *
	 * @return
	 */
	public static Stream<AvailableProcesses> stream()
	{
		return Stream.of(AvailableProcesses.values());
	}
}