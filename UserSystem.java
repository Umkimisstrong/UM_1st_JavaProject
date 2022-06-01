package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// ����� Ŭ����
class UserSystem extends Purchase implements KMenu
{
	int sel;
	static String id, pw;
	static boolean loginflag = false;

	private final int BUY_PRODUCT = 1;		// 1. ��ǰ����
	private final int CHECK_POINT = 2;		// 2. �ܾ�Ȯ��(����)
	private final int MAIN		  = 3;		// 3. ��������
	
	// �޴� ����
	@Override
	public void systemOn()
	{
		login();	
	}

	// �޴� ǥ��
	@Override
	public void menuDisp()
	{	
		System.out.println();
		System.out.println("====== �̿��� ������ ======");
		System.out.println("    1. ��ǰ ����");
		System.out.println("    2. �ܾ� Ȯ��(����)");
		System.out.println("    3. ��������");
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
			while (sel < 1 || sel > 3);
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��� �ּ���~~");
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
			case BUY_PRODUCT : menuChoice();	break;
			case CHECK_POINT : payChargeDisp(); break;
			case MAIN	     : 
				loginflag = false;
				ks.systemOn(); 
		}
	}

	// ȸ�� �α���
	public void login() 
	{
		Scanner sc = new Scanner(System.in);

		Users us = new Users();				
		int loginCount = 1;		//-- �α��� ���� Ƚ����
		boolean flag;	//-- �α��� Ƚ������ ���ǿ�

		System.out.println();
		System.out.println("=============== �α��� ==============");

		flag = false;
		
		do
		{
			System.out.println("[ID : �̸� / PW : ��ȭ��ȣ('-' ����)]");
			System.out.print("ID �� ");
			id = sc.next();
			System.out.print("PW �� ");
			pw = sc.next();

			// PW �߸� �Է�
			if (!memList.containsKey(pw))
			{
				System.out.println();
				System.out.println("PW �Է� ����");
				System.out.printf("[������ȸ : %d��]\n", 5 - loginCount);
				System.out.println();
			}
			// ����� �ùٷ� �Է�������, ID�� �߸��� ���
			else if(!(memList).get(pw).getName().equals(id) && memList.containsKey(pw))	
			{
				System.out.println();
				System.out.println("ID �Է� ����");
				System.out.printf("[������ȸ : %d��]\n", 5 - loginCount);
				System.out.println();
			}
			// ������ ���
			else if ((memList).get(pw).getName().equals(id) && memList.containsKey(pw))
			{
				flag = true;
			}
			// Ƚ���� 5ȸ �� �ʰ��� ���
			if (loginCount > 4)
			{
				Kiosk ks = new Kiosk();
				System.out.println("�α��� ����...");
				System.out.println("�������� ���ư��ϴ�.");
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

	// ���� �޼ҵ�
	public void payCharge(int cash)
	{
		int point = memList.get(pw).getPoint();
		point += cash;
		memList.get(pw).setPoint(point);
	}

	// ���������� �޼ҵ�
	public void payChargeDisp()
	{
		Scanner sc = new Scanner(System.in);
		UserSystem us = new UserSystem();
		
		int cash;
		
		System.out.println();
		System.out.printf ("���� �ܾ��� [ %d�� ] �Դϴ�.\n", memList.get(pw).getPoint());
		System.out.println("!!!!! ���� �� 10% �߰� ���� !!!!!");
		System.out.println();
		System.out.print("���� �Ͻðڽ��ϱ�?(Y/N) �� ");
		
		String answerForCharge = sc.next();
	

		try
		{
			if (answerForCharge.equalsIgnoreCase("Y"))
			{
				System.out.println();
				System.out.println("���� �Է� ��, ���� �������� ���ư��ϴ�.");
				do
				{
					System.out.print("���� �ݾ� (�ּ� õ��) �� ");
					cash = sc.nextInt();

					if (cash < 0)
					{
						System.out.println();
						System.out.println("������ ��ҵǾ����ϴ�.");

						menuDisp();
						menuSelect();
						menuRun();
					}

				}
				while (cash < 1000);
				
				// 1000�� �̻� �Է� ��, 
				System.out.println();
				System.out.printf("%d �� ������ �Ϸ�Ǿ����ϴ�. \n",cash);

				cash = (int)(cash * 1.1);
				payCharge(cash);
				
				System.out.println();
				System.out.println("10% �߰� �����Ǿ�,");
				System.out.printf (">> %s ���� ���� �ܾ��� [ %d �� ] �Դϴ�.\n", 
					memList.get(pw).getName(), memList.get(pw).getPoint());
				
				System.out.println();
				System.out.println("�̿��� �������� �̵��մϴ�.");

				menuDisp();
				menuSelect();
				menuRun();
			}
			else
			{
				System.out.println();
				System.out.println("������ ��ҵǾ����ϴ�.");

				menuDisp();
				menuSelect();
				menuRun();
			}
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��� �ּ���~~");
			payChargeDisp();
		}
	}
}


