package com.lduran.escritfisc.service;

import java.util.*;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.ObjectBI;
import com.lduran.escritfisc.entity.Organizacao;

public class OrgServiceImpl implements ObjectService
{
	@Override
	public ObjectBI getObject(List<String> list)
	{
		Organizacao org = new Organizacao();

		if ((list.size() > 8) && (list.get(8) != null)) {org.setRazaoSocial(list.get(8));}
		if ((list.size() > 9) && (list.get(9) != null)) {org.setCnpj(list.get(9));}
		if ((list.size() > 10) && (list.get(10) != null)) {org.setUf(list.get(10));}
		if ((list.size() > 11) && (list.get(11) != null)) {org.setCodigoIBGE(list.get(11));}

		return org;
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
		Map<String, ObjectBI> mapOrganizacao = fileContent.stream() // render as stream ()
				.map(line -> Arrays.asList(line.split("\\|"))) // generates a list of the columns divided in "|"
				.collect(HashMap<String, ObjectBI>::new, (map, list) ->
				{
					Organizacao org = (Organizacao) this.getObject(list);

					String chave = org.getCnpj();
					map.put(chave, org);

				}, (m, u) ->
				{
				}); // generate a Map<String, T> for each list of the columns generated

		List<ObjectBI> lstOrganizacao = new LinkedList<>();
		lstOrganizacao = mapOrganizacao.entrySet() // create a set out of the same elements contained in the hash map
				.stream() // calling the method stream() on a list of objects returns a regular
							// object stream
				.map(Map.Entry::getValue) // the Map.entrySet method returns a collection-view of the map, whose
											// elements are of this class, getValue() returns the value
											// corresponding to this entry
				.collect(Collectors.toList()); // Collectors.toList() converts Map to List

		return lstOrganizacao;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdOrganizacao;Organizacao;IdCidade;UF";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		Organizacao org = (Organizacao) obj;
		return org.getCnpj() + ";" + org.getRazaoSocial() + ";" + org.getCodigoIBGE() + ";" + org.getUf();
	}
}
