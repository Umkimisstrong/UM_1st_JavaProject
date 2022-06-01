package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// 관리자 클래스
class AdSystem extends Buy implements KMenu
{
	private int sel;	//-- 관리자 메뉴 선택
	String pdName;		//-- 상품명
	static int totalSales;
	static int totalCate1, totalCate2, totalCate3;


	private final int STOCK = 1;		// 1. 재고관리
	private final int SALES = 2;		// 2. 매출관리
	private final int MAIN  = 3;		// 3. 메인으로
	private final int EXIT  = 4;		// 4. 시스템종료
	
	
	// 메뉴 가동
	@Override
	public void systemOn()
	{
		adLogin();
	}

	// 메뉴 표시
	@Override
	public void menuDisp()		
	{
		System.out.println();
		System.out.println("====== 관리자 페이지 ======");
		System.out.println("    1. 재고 관리");
		System.out.println("    2. 매출 관리");
		System.out.println("    3. 메인으로");
		System.out.println("    4. 시스템 종료");
		System.out.println("===========================");
	}

	// 메뉴 선택
	@Override
	public void menuSelect()	
	{
		Scanner sc = new Scanner(System.in);
		try
		{
			do
			{
				System.out.print("▶ ");
				sel = sc.nextInt();
			}
			while (sel < 1 || sel > 4);
		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력해 주세요~~");
			menuSelect();
		}
		
	}

	// 메뉴 실행
	@Override
	public void menuRun() 	
	{
		Kiosk ks = new Kiosk();

		switch (sel)
		{
		case STOCK: stockMg(); break;
		case SALES: salesMg(); break;
		case MAIN : ks.systemOn(); break; 
		case EXIT : exit();
		}
	}

	// 관리자 로그인 
	public void adLogin()
	{
		Scanner sc = new Scanner(System.in);
		Kiosk ks = new Kiosk();

		String adPw = "1234";  //-- 관리자 pw
		String inputPw;        //-- 입력   pw

		System.out.println("\n====== 관리자 로그인 ======");
		System.out.print("PW (-1 : 메인) ▶ ");
		inputPw = sc.next();

		// pw 에 -1 입력 → 메인으로
		if(inputPw.equals("-1"))  
			ks.systemOn();
		else if(inputPw.equals(adPw))
		{
			menuDisp();
			menuSelect();
			menuRun();
		}
		// 관리자 pw 불일치 시 -> 성공할 때 까지
		else if(!inputPw.equals(adPw))
		{
			do
			{
				System.out.println("잘못된 비밀번호입니다.");
				System.out.println();
				System.out.print("PW (-1 : 메인) ▶ ");
				inputPw = sc.next();
				
				if(inputPw.equals("-1"))  
					ks.systemOn();
			}
			while (!inputPw.equals(adPw));
			
			menuDisp();
			menuSelect();
			menuRun();
		}	
	}

	// 1. 재고 관리
	public void stockMg()
	{
		Scanner sc = new Scanner(System.in);
		int category, pdPrice, pdNum;		//-- 카테고리, 가격, 상품수량
		int chNum;							//-- 상품수량변경시 사용

		System.out.println();
		System.out.println("====== 재고 관리 ======");
		System.out.println("    1. 상품 추가");
		System.out.println("    2. 상품 변경(수량)");
		System.out.println("    3. 상품 삭제");
		System.out.println("    0. 이전 페이지");
		System.out.println("=======================");
		
		try
		{
			do
			{
				System.out.print("▶ ");
				sel = sc.nextInt();
			}
			while (sel < 0 || sel > 3);

			if (sel == 0)	// 0. 이전 페이지
			{
				menuDisp();
				menuSelect();
				menuRun();
			}
			else if (sel == 1)	// 1. 상품추가
			{
				System.out.println();
				System.out.println("======== 추가할 상품의 정보를 입력해주세요 ========");
				System.out.println("① 식료품  ② 생활용품  ③ 연령제한물품(술/담배)\n");
				do
				{
					System.out.print("카테고리    ▶ ");
					category = sc.nextInt();

					if (category < 1 || category > 3)
						System.out.println("카테고리 입력 오류");
				}
				while (category < 1 || category > 3);
				
				System.out.print("추가 상품명 ▶ ");
				pdName = sc.next();

				System.out.print("상품 가격   ▶ ");
				pdPrice = sc.nextInt();

				System.out.print("상품 수량   ▶ ");
				pdNum = sc.nextInt();
				
				pdList.put(pdName, new Products(pdPrice, pdNum, category));
				
				System.out.println();
				System.out.printf("▶ [상품명: %s, 가격: %d원, 재고: %d개] 추가 완료\n", pdName, pdPrice, pdNum);
			}
			else if (sel == 2) // 2. 상품변경(수량)
			{
				System.out.println();
				System.out.print("수량 변경할 상품명 ▶ ");
				pdName = sc.next();

				if (pdList.containsKey(pdName))	
				{	
					System.out.printf("[%s]의 현재 수량 : %d개\n", pdName, pdList.get(pdName).getProduct_Num());
					System.out.print("변경 수량 ▶ ");
					chNum = sc.nextInt();

					pdList.get(pdName).setProduct_Num(chNum);

					System.out.println();
					System.out.printf("▶ [상품명: %s, 재고: %d개] 변경 완료\n", pdName, pdList.get(pdName).getProduct_Num());
				}
				else
					System.out.println("입력하신 상품이 존재하지 않습니다.");
			}
			else if (sel == 3)	// 3. 상품 삭제
			{
				System.out.println();
				System.out.print("삭제 상품명 ▶ ");
				pdName = sc.next();
				
				// 상품 존재 확인 후, 삭제
				if (pdList.containsKey(pdName))	
				{
					pdList.remove(pdName);

					System.out.printf("▶ [%s] 상품 삭제 완료\n", pdName);
				}
				else
					System.out.println("입력하신 상품이 존재하지 않습니다.");
			}
			
			menuDisp();
			menuSelect();
			menuRun();
		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력해 주세요~~");
			stockMg();
		}
			
	}


	// 2. 매출 관리
	public void salesMg()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("======== 매출 관리 ========");
		System.out.println("    1. 전체 매출 현황");
		System.out.println("    2. 카테고리별 현황");
		System.out.println("    0. 이전 페이지");
		System.out.println("===========================");

		do
		{
			System.out.print("▶ ");
			sel = sc.nextInt();
		}
		while (sel < 0 || sel > 3);

		if (sel == 0)		// 0. 이전 페이지
		{
			menuDisp();
			menuSelect();
			menuRun();
		}
		else if (sel == 1)		// 1. 전체 매출 현황
		{	
			totalSales += totPrice;

			System.out.printf("[ 전체 매출 ] : %d 원\n", totalSales);
			menuDisp();
			menuSelect();
			menuRun();
		}
		
		else if (sel == 2)	// 2. 카테고리별 현황
		{
			System.out.println("[카테고리별 매출]") ;
			System.out.printf("① 식료품                : %10d 원\n", totalCate1);
			System.out.printf("② 생활용품              : %10d 원\n", totalCate2);
			System.out.printf("③ 연령제한물품(술/담배) : %10d 원\n", totalCate3);

			menuDisp();
			menuSelect();
			menuRun();
		}
		
		menuDisp();
		menuSelect();
		menuRun();
	}

	// 4. 시스템 종료
	public void exit()
	{
		System.out.println();
		System.out.println("프로그램 종료...");

		System.exit(-1);
	}
}