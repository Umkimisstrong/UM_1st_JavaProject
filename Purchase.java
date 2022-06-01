package com.test;

import java.util.Scanner;
import java.util.InputMismatchException;

// UserSystem, NotUserSystem �� �θ�Ŭ����
class Purchase extends Buy
{
	public static String name;		//-- ������ ��ǰ��
	static int buyAmount = 0;		//-- ���� ����
	static String name1;
	static boolean flag = false;
	static int j = 0;

	MemData md = new MemData();
	
	public void menuChoice()
	{
		int sel;
		
		System.out.println();
		System.out.println("=========== ī�װ� ���� ===========");
		System.out.println("    1. �ķ�ǰ");
		System.out.println("    2. ��Ȱ��ǰ");
		System.out.println("    3. �������ѹ�ǰ(��/���)");
		System.out.println("    4. ��ٱ��� Ȯ��(���� �� ����)");
		System.out.println("=====================================");

		Scanner sc = new Scanner(System.in);
		try
		{
			do
			{
				System.out.print("�� ");
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
			System.out.println("���� �Է��� �ּ���~~");
			menuChoice();
		}
			

		
	}

	public void menuBuy()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[-1 : �ڷΰ���]");
		System.out.print("������ ��ǰ�� �Է� �� ");
		name = sc.next();
		
	
		// -1 : �ڷΰ���
		if (name.equals("-1"))	
		{
			name = name1;
			menuChoice();		
		}
		
		try
		{
			if (pdList.containsKey(name))
			{
				int productNum = pdList.get(name).getProduct_Num();		//-- ��ǰ ���� ����
				int setProjectNum = productNum;							//-- �缳���� ��ǰ ����

				do
				{
					System.out.print("���� ���� �Է�     �� ");
					buyAmount = sc.nextInt();
				}
				while (buyAmount <= 0);
					
				if (buyAmount <= productNum)	// ����������� <= ��ǰ�������
				{				
					j = pdList.get(name).getProduct_Num();
					j -= buyAmount;
					pdList.get(name).setProduct_Num(j);
				
					name1 = name;
					nameLists.add(name);
					amountLists.add(buyAmount);
					flag = nameLists.contains(name);
					
					System.out.println();
					System.out.println("��ٱ��Ͽ� �����ϴ�.");
					cartList(); 
					menuChoice();
				}
				else 
				{
					System.out.println();
					System.out.println("������ �����մϴ�!!");
					menuBuy();
				}
			}
			else
			{
				System.out.println("�߸��� ��ǰ �̸��Դϴ�.");
				System.out.println("�ٽ� �����ϼ���.");
				menuBuy();
			}
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��� �ּ���~~");
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
		System.out.println("�������� ��, ���� �����մϴ�.");
		
		do
		{
			System.out.print("�ֹι�ȣ �Է�('-'����) �� ");
			str = sc.next();

			if (!user.ssnAva(str))
			{
				System.out.println("�ֹι�ȣ �Է� ����");
				System.out.println();
			}
		}
		while (!user.ssnAva(str));

		if (user.ssnAdult(str) == true)
		{
			System.out.println("�������� �Ϸ�");
			System.out.println();

			md.printList3();
			menuBuy();
		}
		else 
		{
			System.out.println("�̼����� ���� �Ұ� ��ǰ�Դϴ�.");

			menuChoice();
		}
	}
}