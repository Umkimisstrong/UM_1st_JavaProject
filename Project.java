package com.test;

import java.util.HashMap;
import java.util.Map;

public class Project
{	
	public static Map<String, Members> memList = new HashMap<String, Members>(); 
	public static Map<String, Products> pdList = new HashMap<String, Products>();

	public void TestMemberData()
	{
		memList.put("010-5154-6322", new Members("김민성"));
		memList.put("010-9768-3110", new Members("김태형"));
		memList.get("010-5154-6322").setPoint(0);
	}

	public void TestProdData()
	{
		pdList.put("코카콜라", new Products(1000,10,1));
		pdList.put("펩시", new Products(900,10,1));
		pdList.put("사이다",new Products(1000,10,1));
		pdList.put("양말", new Products(5000,10,2));
		pdList.put("면도기", new Products(8000,10,2));
		pdList.put("빨래세제", new Products(10000,10,2));
		pdList.put("샴푸", new Products(12000,10,2));
	    pdList.put("말보로레드",new Products(4500,10,3));
	    pdList.put("참이슬",new Products(1700,10,3));
	}
	

	public static void main(String[] args)
	{
		Project pj = new Project();
		pj.TestMemberData();
		pj.TestProdData();

		Kiosk ks = new Kiosk();
		ks.systemOn();
	}
}

