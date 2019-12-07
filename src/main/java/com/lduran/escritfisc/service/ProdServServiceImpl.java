package com.lduran.escritfisc.service;

import java.util.*;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.ObjectBI;
import com.lduran.escritfisc.entity.ProdServ;
import com.lduran.escritfisc.util.ToolsFactory;

public class ProdServServiceImpl implements ObjectService
{
	@Override
	public ObjectBI getObject(List<String> list)
	{
		ProdServ prodServ = new ProdServ();

		if ((list.size() > 2) && (list.get(2) != null)) {prodServ.setIdProdServ(list.get(2));}
		if ((list.size() > 3) && (list.get(3) != null)) {prodServ.setDescricao(list.get(3));}
		if ((list.size() > 7) && (list.get(7) != null)) {prodServ.setTipoProdServ(ToolsFactory.getInstance().obtemTipoItemCredito("TI" + list.get(7)));}

		return prodServ;
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
		Map<String, ObjectBI> mapProdServ = fileContent.stream() // render as stream ()
				.map(line -> Arrays.asList(line.split("\\|"))) // generates a list of the columns divided in "|"
				.collect(HashMap<String, ObjectBI>::new, (map, list) ->
				{
					ProdServ prodServ = (ProdServ) this.getObject(list);

					String chave = prodServ.getIdProdServ();
					map.put(chave, prodServ);

				}, (m, u) ->
				{
				}); // generate a Map<String, T> for each list of the columns generated

		List<ObjectBI> lstProdServ = new LinkedList<>();
		lstProdServ = mapProdServ.entrySet() // create a set out of the same elements contained in the hash map
				.stream() // calling the method stream() on a list of objects returns a regular
							// object stream
				.map(Map.Entry::getValue) // the Map.entrySet method returns a collection-view of the map, whose
											// elements are of this class, getValue() returns the value
											// corresponding to this entry
				.collect(Collectors.toList()); // Collectors.toList() converts Map to List

		return lstProdServ;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdProduto;Produto;TipoDeProdutoServiço";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		ProdServ produto = (ProdServ) obj;
		return produto.getIdProdServ() + ";" + produto.getDescricao() + ";" + produto.getTipoProdServ();
	}
}
