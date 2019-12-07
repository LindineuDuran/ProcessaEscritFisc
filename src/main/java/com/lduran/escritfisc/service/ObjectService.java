package com.lduran.escritfisc.service;

import java.util.List;

import com.lduran.escritfisc.entity.ObjectBI;

public interface ObjectService
{
	public ObjectBI getObject(List<String> list);
	public ObjectBI getObject(List<String> list, ObjectBI obj);
	public List<? extends ObjectBI> getObjectList(List<String> fileContent);
	public String getObjectHeader();
	public String getObjectContent(ObjectBI obj);
}
