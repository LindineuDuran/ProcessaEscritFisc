package com.lduran.escritfisc.service;

public class BuildService
{
	public static ObjectService getObjectService(String objectType)
	{
		switch (objectType)
		{
		case "Organizacoes":
			ObjectService orgService = new OrgServiceImpl();
			return orgService;

		case "Estabelecimentos":
			ObjectService estabService = new EstabServiceImpl();
			return estabService;

		case "Participantes":
			ObjectService participanteService = new ParticipanteServiceImpl();
			return participanteService;

		case "ProdServ":
			ObjectService prodServService = new ProdServServiceImpl();
			return prodServService;

		case "NotaFiscalDeServico":
			ObjectService nfsService = new NFSServiceImpl();
			return nfsService;

		case "ApuracaoPIS":
			ObjectService apuracaoPISServ = new ApuraPISServImpl();
			return apuracaoPISServ;

		default:
			return null;
		}
	}
}
