package com.test;

import java.util.HashMap;
import java.util.Map;

public class Project
{	
	public static Map<String, Members> memList = new HashMap<String, Members>(); 
	public static Map<String, Products> pdList = new HashMap<String, Products>();

	public void TestMemberData()
	{
		memList.put("010-5154-6322", new Members("��μ�"));
		memList.put("010-9768-3110", new Members("������"));
		memList.get("010-5154-6322").setPoint(0);
	}

	public void TestProdData()
	{
		pdList.put("��ī�ݶ�", new Products(1000,10,1));
		pdList.put("���", new Products(900,10,1));
		pdList.put("���̴�",new Products(1000,10,1));
		pdList.put("�縻", new Products(5000,10,2));
		pdList.put("�鵵��", new Products(8000,10,2));
		pdList.put("��������", new Products(10000,10,2));
		pdList.put("��Ǫ", new Products(12000,10,2));
	    pdList.put("�����η���",new Products(4500,10,3));
	    pdList.put("���̽�",new Products(1700,10,3));
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

