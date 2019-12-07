package com.lduran.escritfisc.service;

import java.util.*;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.ObjectBI;
import com.lduran.escritfisc.entity.Participante;

public class ParticipanteServiceImpl implements ObjectService
{
	@Override
	public ObjectBI getObject(List<String> list)
	{
		Participante partic = new Participante();

		if ((list.size() > 3) && (list.get(3) != null)) {partic.setRazaoSocial(list.get(3));}
		if ((list.size() > 4) && (list.get(4) != null)) {partic.setPais(list.get(4));}
		if ((list.size() > 6) && ((list.get(5) != null) || (list.get(6) != null))) {partic.setCnpj(!list.get(5).equals("") ? list.get(5) : list.get(6));}
		if ((list.size() > 8) && (list.get(8) != null)) {partic.setCodigoIBGE(list.get(8));}
		if ((list.size() > 11) && (list.get(10) != null) && (list.get(11) != null)) {partic.setEndereco(list.get(10) + " " + list.get(11));}
		if ((list.size() > 13) && (list.get(13) != null)) {partic.setBairro(list.get(13));}

		return partic;
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
		Map<String, ObjectBI> mapPartic = fileContent.stream() // render as stream ()
				.map(line -> Arrays.asList(line.split("\\|"))) // generates a list of the columns divided in "|"
				.collect(HashMap<String, ObjectBI>::new, (map, list) ->
				{
					Participante partic = (Participante) this.getObject(list);

					String chave = partic.getCnpj();
					map.put(chave, partic);

				}, (m, u) ->
				{
				}); // generate a Map<String, T> for each list of the columns generated

		List<ObjectBI> lstPartic = new LinkedList<>();
		lstPartic = mapPartic.entrySet() // create a set out of the same elements contained in the hash map
				.stream() // calling the method stream() on a list of objects returns a regular
							// object stream
				.map(Map.Entry::getValue) // the Map.entrySet method returns a collection-view of the map, whose
											// elements are of this class, getValue() returns the value
											// corresponding to this entry
				.collect(Collectors.toList()); // Collectors.toList() converts Map to List

		return lstPartic;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdParticipante;Participante;IdCidade;Endereco;Bairro;Pais";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		Participante participante = (Participante) obj;
		return participante.getCnpj() + ";" + participante.getRazaoSocial() + ";" + participante.getCodigoIBGE() + ";" + participante.getEndereco() + ";" + participante.getBairro() + ";" + participante.getPais();
	}
}
