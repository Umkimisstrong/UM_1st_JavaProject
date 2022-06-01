package com.test;

import java.util.Scanner;
//import java.util.HashMap;
import java.util.InputMismatchException;

class Kiosk extends Project implements KMenu	
{	
	int sel;
	public final int K_Log = 1;
	public final int K_NotUser = 2;
	public final int K_Join = 3;
	public final int K_Admin = 4;


	@Override
	public void systemOn()
	{
		menuDisp();
		menuSelect();
		menuRun();
	}
	
	@Override
	public void menuDisp()	
	{
		System.out.println();
		System.out.println("===========================");
		System.out.println("       무인 편의점");
		System.out.println("===========================");
		System.out.println("    1. 고객 로그인");
		System.out.println("    2. 비회원 구매");
		System.out.println("    3. 회원 가입");
		System.out.println("    4. 관리자 모드");
		System.out.println("===========================");
	}

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
			while(sel < 1 || sel > 4);	
		}
		catch (InputMismatchException e)
		{
			System.out.println("숫자 입력해 주세요~~");
			menuSelect();
		}
				
	}

	// 선택된 메뉴 실행에 따른 기능 호출 메소드
	@Override
	public void menuRun()
	{
			if (sel == K_Log)
			{
				UserSystem us = new UserSystem();
				us.systemOn();
			}
			else if (sel == K_NotUser)
			{
				NotUserSystem nu = new NotUserSystem();
				nu.systemOn();
			}
			else if (sel == K_Join)
			{
				Users us = new Users();
				us.joinning();
			}
			else if (sel == K_Admin)
			{
				AdSystem ad = new AdSystem();
				ad.systemOn();
			}
	}
}

