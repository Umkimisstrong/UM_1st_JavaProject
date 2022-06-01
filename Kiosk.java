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
		System.out.println("       ���� ������");
		System.out.println("===========================");
		System.out.println("    1. �� �α���");
		System.out.println("    2. ��ȸ�� ����");
		System.out.println("    3. ȸ�� ����");
		System.out.println("    4. ������ ���");
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
				System.out.print("�� ");
				sel = sc.nextInt();
			}
			while(sel < 1 || sel > 4);	
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��� �ּ���~~");
			menuSelect();
		}
				
	}

	// ���õ� �޴� ���࿡ ���� ��� ȣ�� �޼ҵ�
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

