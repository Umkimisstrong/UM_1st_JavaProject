package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// 사용자 클래스
class UserSystem extends Purchase implements KMenu
{
	int sel;
	static String id, pw;
	static boolean loginflag = false;

	private final int BUY_PRODUCT = 1;		// 1. 상품구매
	private final int CHECK_POINT = 2;		// 2. 잔액확인(충전)
	private final int MAIN		  = 3;		// 3. 메인으로
	
	// 메뉴 가동
	@Override
	public void systemOn()
	{
		login();	
	}

	// 메뉴 표시
	@Override
	public void menuDisp()
	{	
		System.out.println();
		System.out.println("====== 이용자 페이지 ======");
		System.out.println("    1. 상품 구매");
		System.out.println("    2. 잔액 확인(충전)");
		System.out.println("    3. 메인으로");
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
			while (sel < 1 || sel > 3);
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
			case BUY_PRODUCT : menuChoice();	break;
			case CHECK_POINT : payChargeDisp(); break;
			case MAIN	     : 
				loginflag = false;
				ks.systemOn(); 
		}
	}

	// 회원 로그인
	public void login() 
	{
		Scanner sc = new Scanner(System.in);

		Users us = new Users();				
		int loginCount = 1;		//-- 로그인 제한 횟수용
		boolean flag;	//-- 로그인 횟수제한 조건용

		System.out.println();
		System.out.println("=============== 로그인 ==============");

		flag = false;
		
		do
		{
			System.out.println("[ID : 이름 / PW : 전화번호('-' 포함)]");
			System.out.print("ID ▶ ");
			id = sc.next();
			System.out.print("PW ▶ ");
			pw = sc.next();

			// PW 잘못 입력
			if (!memList.containsKey(pw))
			{
				System.out.println();
				System.out.println("PW 입력 오류");
				System.out.printf("[남은기회 : %d번]\n", 5 - loginCount);
				System.out.println();
			}
			// 비번은 올바로 입력했으나, ID가 잘못된 경우
			else if(!(memList).get(pw).getName().equals(id) && memList.containsKey(pw))	
			{
				System.out.println();
				System.out.println("ID 입력 오류");
				System.out.printf("[남은기회 : %d번]\n", 5 - loginCount);
				System.out.println();
			}
			// 성공한 경우
			else if ((memList).get(pw).getName().equals(id) && memList.containsKey(pw))
			{
				flag = true;
			}
			// 횟수를 5회 를 초과한 경우
			if (loginCount > 4)
			{
				Kiosk ks = new Kiosk();
				System.out.println("로그인 실패...");
				System.out.println("메인으로 돌아갑니다.");
				ks.systemOn();
			}
			loginCount++;
		}
		while (!flag);
		
		loginflag = true;

		menuDisp();
		menuSelect();
		menuRun();

	}// end of login

	// 충전 메소드
	public void payCharge(int cash)
	{
		int point = memList.get(pw).getPoint();
		point += cash;
		memList.get(pw).setPoint(point);
	}

	// 충전페이지 메소드
	public void payChargeDisp()
	{
		Scanner sc = new Scanner(System.in);
		UserSystem us = new UserSystem();
		
		int cash;
		
		System.out.println();
		System.out.printf ("현재 잔액은 [ %d원 ] 입니다.\n", memList.get(pw).getPoint());
		System.out.println("!!!!! 충전 시 10% 추가 적립 !!!!!");
		System.out.println();
		System.out.print("충전 하시겠습니까?(Y/N) ▶ ");
		
		String answerForCharge = sc.next();
	

		try
		{
			if (answerForCharge.equalsIgnoreCase("Y"))
			{
				System.out.println();
				System.out.println("음수 입력 시, 이전 페이지로 돌아갑니다.");
				do
				{
					System.out.print("충전 금액 (최소 천원) ▶ ");
					cash = sc.nextInt();

					if (cash < 0)
					{
						System.out.println();
						System.out.println("충전이 취소되었습니다.");

						menuDisp();
						menuSelect();
						menuRun();
					}

				}
				while (cash < 1000);
				
				// 1000원 이상 입력 시, 
				System.out.println();
				System.out.printf("%d 원 충전이 완료되었습니다. \n",cash);

				cash = (int)(cash * 1.1);
				payCharge(cash);
				
				System.out.println();
				System.out.println("10% 추가 적립되어,");
				System.out.printf (">> %s 님의 현재 잔액은 [ %d 원 ] 입니다.\n", 
					memList.get(pw).getName(), memList.get(pw).getPoint());
				
				System.out.println();
				System.out.println("이용자 페이지로 이동합니다.");

				menuDisp();
				menuSelect();
				menuRun();
			}
			else
			{
				System.out.println();
				System.out.println("충전이 취소되었습니다.");

				menuDisp();
				menuSelect();
				menuRun();
			}
		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력해 주세요~~");
			payChargeDisp();
		}
	}
}


