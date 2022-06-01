package com.test;

//import java.util.Scanner;
import java.util.ArrayList;

// 장바구니 클래스
class Cart extends Project
{																//   장바구니에 담은
	ArrayList<String> nameLists = new ArrayList<String>();		//-- 상품 이름
	ArrayList<Integer> amountLists = new ArrayList<Integer>();	//-- 상품 수량
	ArrayList<Integer> priceLists = new ArrayList<Integer>();	//-- 상품 가격

	public int totPrice;	//-- 총 금액

	Kiosk ks = new Kiosk();

	public void cartList()
	{
		int pdPrice;	//-- 상품 가격

		if (nameLists.isEmpty())
		{
			System.out.println();
			System.out.println("장바구니에 담긴 상품이 없습니다.");
			System.out.println("메인으로 돌아갑니다.");
			UserSystem.loginflag = false;
			
			ks.systemOn();
		}
		else
		{
			System.out.println();
			System.out.println("================ [장바구니] ===============");
			pdPrice = pdList.get(Purchase.name).getPrice();
			priceLists.add(pdPrice);
			System.out.println("상품명                수량            가격");
			System.out.println("-------------------------------------------");
	
			for (int i = 0; i < nameLists.size(); i++)
			{
				totPrice += priceLists.get(i);
				System.out.printf("%-10s\t%d\t%d\n", nameLists.get(i), amountLists.get(i), priceLists.get(i));
			}
			System.out.println("===========================================");
		}
	}

	// 장바구니 비우기
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
	
	// 회원 충전 실패시 장바구니 비우기
	public void clearCart2() 
	{
		for (int i = 0; i < nameLists.size(); i++)
			pdList.get(nameLists.get(i)).setProduct_Num(pdList.get(nameLists.get(i)).getProduct_Num()+amountLists.get(i));

		nameLists.clear();
		amountLists.clear();
		priceLists.clear();
		Purchase.name = null;

		UserSystem us = new UserSystem(); // 윗 메소드와 달리
		us.menuDisp();					  // 비우고 띄워야할 메뉴가 다름
		us.menuSelect();
		us.menuRun();
	}

}
