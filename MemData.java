package com.test;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.List;
//import java.util.ArrayList;


class MemData extends Project
{	
	// 카테고리 ① 식료품
	public void printList1()
	{

		//-------------------------------------------------------------------------------//
		//							[카테고리별로 정렬 하기(출력하기)]
			
			//------------------------------[식료품 출력]---------------------	
		System.out.println();
		System.out.println("---------------------- [식료품] --------------------------");
		for (String key :pdList.keySet())
		{
			Products value = pdList.get(key);

			if (value.getCategory() == 1)
				System.out.printf("상품명 : %-8s\t가격 : %-5d   남은수량 : %3d\n",key,
				value.getPrice(), value.getProduct_Num());
		}
		System.out.println("----------------------------------------------------------");
	}

	// 카테고리 ② 생활용품
	public void printList2()
	{
		//------------------------------[생활용품 출력]---------------------
		System.out.println();
		System.out.println("----------------------- [생활용품] -----------------------");
		for (String key : pdList.keySet())
		{
			Products value = pdList.get(key);
			if (value.getCategory() == 2)
				System.out.printf("상품명 : %-8s\t가격 : %-5d   남은수량 : %3d\n",key,
				value.getPrice(), value.getProduct_Num());
		}
		System.out.println("----------------------------------------------------------");
	}

	// 카테고리 ③ 연령제한물품(술/담배)
	public void printList3()
	{
		//------------------------------[연령제한 출력]---------------------
		System.out.println();
		System.out.println("--------------------- [연령제한 물품] --------------------");
		for (String key : pdList.keySet())
		{
			Products value = pdList.get(key);
			if (value.getCategory() == 3)
				System.out.printf("상품명 : %-8s\t가격 : %-5d   남은수량 : %3d\n",key,
				value.getPrice(), value.getProduct_Num());
		}
		System.out.println("----------------------------------------------------------");
	}
}

