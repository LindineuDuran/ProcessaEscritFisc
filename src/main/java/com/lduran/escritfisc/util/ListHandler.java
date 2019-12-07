package com.lduran.escritfisc.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.lduran.escritfisc.entity.ObjectBI;
import com.lduran.escritfisc.service.BuildService;

public class ListHandler
{
	private static ListHandler instance;

	/**
	 * @return the instance
	 */
	public static ListHandler getInstance()
	{
		if (instance == null) {instance = new ListHandler();}

		return instance;
	}

	/**
	 * Formata List<String> da List<ObjectBI> fornecida
	 * 
	 * @param lstObjetosBI
	 * @param objectType
	 * @return
	 * @throws IOException
	 */
	public List<String> formataRelatorio(List<? extends ObjectBI> lstObjetosBI, String objectType) throws IOException
	{
		List<String> relatorio = new LinkedList<>();

		String header = BuildService.getObjectService(objectType).getObjectHeader();
		relatorio.add(header);

		if (!lstObjetosBI.isEmpty())
		{
			relatorio.addAll(lstObjetosBI.stream().map(line -> BuildService.getObjectService(objectType).getObjectContent(line)).collect(Collectors.toList()));
		}

		return relatorio;
	}
}
