package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// ��ȸ�� Ŭ����  
class NotUserSystem extends Purchase implements KMenu
{
	private int sel;	//-- ��ȸ�� �޴� ����

	private final int BUY_PRODUCT = 1;		// 1. ��ǰ����
	private final int MAIN		  = 2;		// 2. ��������

	// �޴� ����
	@Override
	public void systemOn()		
	{
		menuDisp();
		menuSelect();
		menuRun();
	}

	// �޴� ǥ��
	@Override
	public void menuDisp() 		
	{
		System.out.println();
		System.out.println("====== ��ȸ�� ������ ======");
		System.out.println("    1. ��ǰ ����");		
		System.out.println("    2. ��������");
		System.out.println("===========================");
	}

	// �޴� ����
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
			while (sel < 1 || sel > 2);
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��ϼ���~!!");
			menuSelect();
		}
		
	}

	// �޴� ����
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