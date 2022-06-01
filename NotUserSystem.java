package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// 비회원 클래스  
class NotUserSystem extends Purchase implements KMenu
{
	private int sel;	//-- 비회원 메뉴 선택

	private final int BUY_PRODUCT = 1;		// 1. 상품구매
	private final int MAIN		  = 2;		// 2. 메인으로

	// 메뉴 가동
	@Override
	public void systemOn()		
	{
		menuDisp();
		menuSelect();
		menuRun();
	}

	// 메뉴 표시
	@Override
	public void menuDisp() 		
	{
		System.out.println();
		System.out.println("====== 비회원 페이지 ======");
		System.out.println("    1. 상품 구매");		
		System.out.println("    2. 메인으로");
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
			while (sel < 1 || sel > 2);
		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력하세요~!!");
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
		case BUY_PRODUCT: menuChoice(); break;
		case MAIN       : ks.systemOn();
		}
	}
}