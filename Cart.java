package com.test;

//import java.util.Scanner;
import java.util.ArrayList;

// ��ٱ��� Ŭ����
class Cart extends Project
{																//   ��ٱ��Ͽ� ����
	ArrayList<String> nameLists = new ArrayList<String>();		//-- ��ǰ �̸�
	ArrayList<Integer> amountLists = new ArrayList<Integer>();	//-- ��ǰ ����
	ArrayList<Integer> priceLists = new ArrayList<Integer>();	//-- ��ǰ ����

	public int totPrice;	//-- �� �ݾ�

	Kiosk ks = new Kiosk();

	public void cartList()
	{
		int pdPrice;	//-- ��ǰ ����

		if (nameLists.isEmpty())
		{
			System.out.println();
			System.out.println("��ٱ��Ͽ� ��� ��ǰ�� �����ϴ�.");
			System.out.println("�������� ���ư��ϴ�.");
			UserSystem.loginflag = false;
			
			ks.systemOn();
		}
		else
		{
			System.out.println();
			System.out.println("================ [��ٱ���] ===============");
			pdPrice = pdList.get(Purchase.name).getPrice();
			priceLists.add(pdPrice);
			System.out.println("��ǰ��                ����            ����");
			System.out.println("-------------------------------------------");
	
			for (int i = 0; i < nameLists.size(); i++)
			{
				totPrice += priceLists.get(i);
				System.out.printf("%-10s\t%d\t%d\n", nameLists.get(i), amountLists.get(i), priceLists.get(i));
			}
			System.out.println("===========================================");
		}
	}

	// ��ٱ��� ����
	public void clearCart()
	{
		for (int i = 0; i < nameLists.size(); i++)
			pdList.get(nameLists.get(i)).setProduct_Num(pdList.get(nameLists.get(i)).getProduct_Num()+amountLists.get(i));
		
		nameLists.clear();
		amountLists.clear();
		priceLists.clear();

		Purchase pr = new Purchase();
		Purchase.name = null;
		pr.menuChoice();
	}
	
	// ȸ�� ���� ���н� ��ٱ��� ����
	public void clearCart2() 
	{
		for (int i = 0; i < nameLists.size(); i++)
			pdList.get(nameLists.get(i)).setProduct_Num(pdList.get(nameLists.get(i)).getProduct_Num()+amountLists.get(i));

		nameLists.clear();
		amountLists.clear();
		priceLists.clear();
		Purchase.name = null;

		UserSystem us = new UserSystem(); // �� �޼ҵ�� �޸�
		us.menuDisp();					  // ���� ������� �޴��� �ٸ�
		us.menuSelect();
		us.menuRun();
	}

}
