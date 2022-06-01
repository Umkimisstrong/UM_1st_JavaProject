package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// ������ Ŭ����
class AdSystem extends Buy implements KMenu
{
	private int sel;	//-- ������ �޴� ����
	String pdName;		//-- ��ǰ��
	static int totalSales;
	static int totalCate1, totalCate2, totalCate3;


	private final int STOCK = 1;		// 1. ������
	private final int SALES = 2;		// 2. �������
	private final int MAIN  = 3;		// 3. ��������
	private final int EXIT  = 4;		// 4. �ý�������
	
	
	// �޴� ����
	@Override
	public void systemOn()
	{
		adLogin();
	}

	// �޴� ǥ��
	@Override
	public void menuDisp()		
	{
		System.out.println();
		System.out.println("====== ������ ������ ======");
		System.out.println("    1. ��� ����");
		System.out.println("    2. ���� ����");
		System.out.println("    3. ��������");
		System.out.println("    4. �ý��� ����");
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
			while (sel < 1 || sel > 4);
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
		case STOCK: stockMg(); break;
		case SALES: salesMg(); break;
		case MAIN : ks.systemOn(); break; 
		case EXIT : exit();
		}
	}

	// ������ �α��� 
	public void adLogin()
	{
		Scanner sc = new Scanner(System.in);
		Kiosk ks = new Kiosk();

		String adPw = "1234";  //-- ������ pw
		String inputPw;        //-- �Է�   pw

		System.out.println("\n====== ������ �α��� ======");
		System.out.print("PW (-1 : ����) �� ");
		inputPw = sc.next();

		// pw �� -1 �Է� �� ��������
		if(inputPw.equals("-1"))  
			ks.systemOn();
		else if(inputPw.equals(adPw))
		{
			menuDisp();
			menuSelect();
			menuRun();
		}
		// ������ pw ����ġ �� -> ������ �� ����
		else if(!inputPw.equals(adPw))
		{
			do
			{
				System.out.println("�߸��� ��й�ȣ�Դϴ�.");
				System.out.println();
				System.out.print("PW (-1 : ����) �� ");
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

	// 1. ��� ����
	public void stockMg()
	{
		Scanner sc = new Scanner(System.in);
		int category, pdPrice, pdNum;		//-- ī�װ�, ����, ��ǰ����
		int chNum;							//-- ��ǰ��������� ���

		System.out.println();
		System.out.println("====== ��� ���� ======");
		System.out.println("    1. ��ǰ �߰�");
		System.out.println("    2. ��ǰ ����(����)");
		System.out.println("    3. ��ǰ ����");
		System.out.println("    0. ���� ������");
		System.out.println("=======================");
		
		try
		{
			do
			{
				System.out.print("�� ");
				sel = sc.nextInt();
			}
			while (sel < 0 || sel > 3);

			if (sel == 0)	// 0. ���� ������
			{
				menuDisp();
				menuSelect();
				menuRun();
			}
			else if (sel == 1)	// 1. ��ǰ�߰�
			{
				System.out.println();
				System.out.println("======== �߰��� ��ǰ�� ������ �Է����ּ��� ========");
				System.out.println("�� �ķ�ǰ  �� ��Ȱ��ǰ  �� �������ѹ�ǰ(��/���)\n");
				do
				{
					System.out.print("ī�װ�    �� ");
					category = sc.nextInt();

					if (category < 1 || category > 3)
						System.out.println("ī�װ� �Է� ����");
				}
				while (category < 1 || category > 3);
				
				System.out.print("�߰� ��ǰ�� �� ");
				pdName = sc.next();

				System.out.print("��ǰ ����   �� ");
				pdPrice = sc.nextInt();

				System.out.print("��ǰ ����   �� ");
				pdNum = sc.nextInt();
				
				pdList.put(pdName, new Products(pdPrice, pdNum, category));
				
				System.out.println();
				System.out.printf("�� [��ǰ��: %s, ����: %d��, ���: %d��] �߰� �Ϸ�\n", pdName, pdPrice, pdNum);
			}
			else if (sel == 2) // 2. ��ǰ����(����)
			{
				System.out.println();
				System.out.print("���� ������ ��ǰ�� �� ");
				pdName = sc.next();

				if (pdList.containsKey(pdName))	
				{	
					System.out.printf("[%s]�� ���� ���� : %d��\n", pdName, pdList.get(pdName).getProduct_Num());
					System.out.print("���� ���� �� ");
					chNum = sc.nextInt();

					pdList.get(pdName).setProduct_Num(chNum);

					System.out.println();
					System.out.printf("�� [��ǰ��: %s, ���: %d��] ���� �Ϸ�\n", pdName, pdList.get(pdName).getProduct_Num());
				}
				else
					System.out.println("�Է��Ͻ� ��ǰ�� �������� �ʽ��ϴ�.");
			}
			else if (sel == 3)	// 3. ��ǰ ����
			{
				System.out.println();
				System.out.print("���� ��ǰ�� �� ");
				pdName = sc.next();
				
				// ��ǰ ���� Ȯ�� ��, ����
				if (pdList.containsKey(pdName))	
				{
					pdList.remove(pdName);

					System.out.printf("�� [%s] ��ǰ ���� �Ϸ�\n", pdName);
				}
				else
					System.out.println("�Է��Ͻ� ��ǰ�� �������� �ʽ��ϴ�.");
			}
			
			menuDisp();
			menuSelect();
			menuRun();
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��� �ּ���~~");
			stockMg();
		}
			
	}


	// 2. ���� ����
	public void salesMg()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("======== ���� ���� ========");
		System.out.println("    1. ��ü ���� ��Ȳ");
		System.out.println("    2. ī�װ��� ��Ȳ");
		System.out.println("    0. ���� ������");
		System.out.println("===========================");

		do
		{
			System.out.print("�� ");
			sel = sc.nextInt();
		}
		while (sel < 0 || sel > 3);

		if (sel == 0)		// 0. ���� ������
		{
			menuDisp();
			menuSelect();
			menuRun();
		}
		else if (sel == 1)		// 1. ��ü ���� ��Ȳ
		{	
			totalSales += totPrice;

			System.out.printf("[ ��ü ���� ] : %d ��\n", totalSales);
			menuDisp();
			menuSelect();
			menuRun();
		}
		
		else if (sel == 2)	// 2. ī�װ��� ��Ȳ
		{
			System.out.println("[ī�װ��� ����]") ;
			System.out.printf("�� �ķ�ǰ                : %10d ��\n", totalCate1);
			System.out.printf("�� ��Ȱ��ǰ              : %10d ��\n", totalCate2);
			System.out.printf("�� �������ѹ�ǰ(��/���) : %10d ��\n", totalCate3);

			menuDisp();
			menuSelect();
			menuRun();
		}
		
		menuDisp();
		menuSelect();
		menuRun();
	}

	// 4. �ý��� ����
	public void exit()
	{
		System.out.println();
		System.out.println("���α׷� ����...");

		System.exit(-1);
	}
}