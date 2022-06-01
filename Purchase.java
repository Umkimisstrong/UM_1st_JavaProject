package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// UserSystem, NotUserSystem 의 부모클래스
class Purchase extends Buy
{
	public static String name;		//-- 구매할 상품명
	static int buyAmount = 0;		//-- 구매 수량
	static String name1;
	static boolean flag = false;
	static int j = 0;

	MemData md = new MemData();
	
	public void menuChoice()
	{
		int sel;
		
		System.out.println();
		System.out.println("=========== 카테고리 선택 ===========");
		System.out.println("    1. 식료품");
		System.out.println("    2. 생활용품");
		System.out.println("    3. 연령제한물품(술/담배)");
		System.out.println("    4. 장바구니 확인(결제 및 종료)");
		System.out.println("=====================================");

		Scanner sc = new Scanner(System.in);
		try
		{
			do
			{
				System.out.print("▶ ");
				sel = sc.nextInt();
			}
			while(sel < 1 || sel > 4);

			switch (sel)
			{
			case 1: md.printList1(); 
					menuBuy();
					break;
			case 2: md.printList2();
					menuBuy();
					break;
			case 3: testAge();
					break;	
			case 4: cartList();
					selPay();
					break;
			}

		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력해 주세요~~");
			menuChoice();
		}
			

		
	}

	public void menuBuy()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[-1 : 뒤로가기]");
		System.out.print("구매할 상품명 입력 ▶ ");
		name = sc.next();
		
	
		// -1 : 뒤로가기
		if (name.equals("-1"))	
		{
			name = name1;
			menuChoice();		
		}
		
		try
		{
			if (pdList.containsKey(name))
			{
				int productNum = pdList.get(name).getProduct_Num();		//-- 상품 현재 수량
				int setProjectNum = productNum;							//-- 재설정할 상품 수량

				do
				{
					System.out.print("구매 수량 입력     ▶ ");
					buyAmount = sc.nextInt();
				}
				while (buyAmount <= 0);
					
				if (buyAmount <= productNum)	// 구매희망수량 <= 상품현재수량
				{				
					j = pdList.get(name).getProduct_Num();
					j -= buyAmount;
					pdList.get(name).setProduct_Num(j);
				
					name1 = name;
					nameLists.add(name);
					amountLists.add(buyAmount);
					flag = nameLists.contains(name);
					
					System.out.println();
					System.out.println("장바구니에 담겼습니다.");
					cartList(); 
					menuChoice();
				}
				else 
				{
					System.out.println();
					System.out.println("수량이 부족합니다!!");
					menuBuy();
				}
			}
			else
			{
				System.out.println("잘못된 물품 이름입니다.");
				System.out.println("다시 선택하세요.");
				menuBuy();
			}
		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력해 주세요~~");
			menuBuy();
		}
		
		
	
	}

	public void testAge()
	{
		Users user = new Users();
		MemData md = new MemData();
		
		Scanner sc = new Scanner(System.in);
		String str = "";

		System.out.println();
		System.out.println("성인인증 후, 구매 가능합니다.");
		
		do
		{
			System.out.print("주민번호 입력('-'포함) ▶ ");
			str = sc.next();

			if (!user.ssnAva(str))
			{
				System.out.println("주민번호 입력 오류");
				System.out.println();
			}
		}
		while (!user.ssnAva(str));

		if (user.ssnAdult(str) == true)
		{
			System.out.println("성인인증 완료");
			System.out.println();

			md.printList3();
			menuBuy();
		}
		else 
		{
			System.out.println("미성년자 구매 불가 상품입니다.");

			menuChoice();
		}
	}
}