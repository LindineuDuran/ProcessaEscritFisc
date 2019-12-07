package com.lduran.escritfisc.service;

import java.util.*;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.Estabelecimento;
import com.lduran.escritfisc.entity.ObjectBI;

public class EstabServiceImpl implements ObjectService
{
	@Override
	public ObjectBI getObject(List<String> list)
	{
		Estabelecimento estab = new Estabelecimento();

		if ((list.size() > 2) && (list.get(2) != null)) {estab.setCnpj(list.get(2));}
		if ((list.size() > 3) && (list.get(3) != null)) {estab.setRazaoSocial(list.get(3));}
		if ((list.size() > 5) && (list.get(5) != null)) {estab.setUf(list.get(5));}
		if ((list.size() > 7) && (list.get(7) != null)) {estab.setCodigoIBGE(list.get(7));}

		return estab;
	}

	@Override
	public ObjectBI getObject(List<String> list, ObjectBI obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent)
	{
		Map<String, ObjectBI> mapEstab = fileContent.stream() // render as stream ()
				.map(line -> Arrays.asList(line.split("\\|"))) // generates a list of the columns divided in "|"
				.collect(HashMap<String, ObjectBI>::new, (map, list) ->
				{
					Estabelecimento estab = (Estabelecimento) this.getObject(list);

					String chave = estab.getCnpj();
					map.put(chave, estab);

				}, (m, u) ->
				{
				}); // generate a Map<String, T> for each list of the columns generated

		List<ObjectBI> lstEstab = new LinkedList<>();
		lstEstab = mapEstab.entrySet() // create a set out of the same elements contained in the hash map
				.stream() // calling the method stream() on a list of objects returns a regular
							// object stream
				.map(Map.Entry::getValue) // the Map.entrySet method returns a collection-view of the map, whose
											// elements are of this class, getValue() returns the value
											// corresponding to this entry
				.collect(Collectors.toList()); // Collectors.toList() converts Map to List

		return lstEstab;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdEstabelecimento;Estabelecimento;IdCidade;UF";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		Estabelecimento estab = (Estabelecimento) obj;
		return estab.getCnpj() + ";" + estab.getRazaoSocial() + ";" + estab.getCodigoIBGE() + ";" + estab.getUf();
	}
}
