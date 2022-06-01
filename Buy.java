package com.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;

class Buy extends Cart
{ 
	//public static boolean payflag = true;

	int returnCash = 0;		//-- �Ž�����
	int sumCash = 0;		//-- ���� ���� ����

	static int salesCate1, salesCate2, salesCate3;	//-- ī�װ��� ���� ���

	static int totPrice = 0;

	// (ȸ��) �������� ����
	public void selPay()
	{
		Scanner sc = new Scanner(System.in);
		
		
		int sel;
		
		System.out.println();
		System.out.println("====== �������� ���� ======");
		
		try
		{
			if (UserSystem.loginflag == true)
			{
				do
				{
					System.out.println("    1. ���� ����");
					System.out.println("    2. �ܾ� ����");
					System.out.println("    3. ��ٱ��� ����");
					System.out.println("===========================");
					System.out.println("* ���� : ��ȸ���� ���ݰ����� �����մϴ�.");
					System.out.print("�� ");
					sel = sc.nextInt();

					if (sel == 1)
						payCash();
					else if (sel == 2)
						payPoint();
					else if (sel == 3)
						clearCart();	
				}
				while (sel < 1 || sel > 3);
			}
			else 
			{
				do
				{
					System.out.println("    1. ���� ����");
					System.out.println("    2. ��ٱ��� ����");
					System.out.println("===========================");
					System.out.println("* ���� : ��ȸ���� ���ݰ����� �����մϴ�.");
					System.out.print("�� ");
					sel = sc.nextInt();

					if (sel == 1)
						payCash();
					else if (sel == 2)
						clearCart();	
				}
				while (sel < 1 || sel > 2);
			}
		}
		catch (InputMismatchException e)
		{
			System.out.println("���� �Է��� �ּ���~~");	
			totPrice = 0;
			selPay();
		}
		
	}

	// ���ݰ��� �޼ҵ� (�̿���, ��ȸ�� �� �� �̿�)
	public void payCash()
	{
		Scanner sc = new Scanner(System.in);

		int inputCash;		//-- ���� ����

		for (int i = 0; i < nameLists.size(); i++)
			totPrice += ( amountLists.get(i) * priceLists.get(i) );
		
		
		System.out.println();
		System.out.printf("�� �ݾ�          �� %d\n", totPrice);
		System.out.print("���� �־��ּ���  �� ");
		inputCash = sc.nextInt();

		sumCash = inputCash;

		if (inputCash < totPrice)
		{
			System.out.println();
			System.out.printf("%d�� ����\n", totPrice - sumCash);
			System.out.print("���� �־��ּ���  �� ");
			inputCash = sc.nextInt();

			sumCash += inputCash;

			if (sumCash < totPrice)
			{
				System.out.println();
				System.out.printf("%d�� ����\n", totPrice - sumCash);
				System.out.println("������ ��ҵǾ����ϴ�.");
				System.out.println("ī�װ��� ���ư��ϴ�.");
				totPrice = 0; // �� �Ѿ� �� �ʱ�ȭ.
				clearCart();
				//ks.systemOn();

			}
		}
		if (sumCash == totPrice)
		{
			System.out.println("\n�� ������ �Ϸ�Ǿ����ϴ� ��\n");
		}
		else if (sumCash > totPrice)
		{
			System.out.println("\n�� ������ �Ϸ�Ǿ����ϴ� ��\n");

			returnCash = returnChange(sumCash);		//-- �Ž����� �޼ҵ� ȣ��

			System.out.printf("����  �� �� %8d ��\n", sumCash);
			System.out.printf("�Ž����� �� %8d ��\n", returnCash);
			System.out.println();
		}

		// ��������� �޼ҵ� ȣ��
		receipt();
	}
//==================================================================================================//
	
	// �ܾװ��� �޼ҵ� (�̿��ڸ�)
	public void payPoint()
	{
		Scanner sc = new Scanner(System.in);

		UserSystem us = new UserSystem();
		Purchase pc = new Purchase();

		String name = memList.get(us.pw).getName();
		int point = memList.get(us.pw).getPoint();
		
		// �� �ݾ� = ��ٱ��Ͽ� ��� ��ǰ����*��ǰ���� 
		for (int i = 0; i < nameLists.size(); i++)
			totPrice += ( amountLists.get(i) * priceLists.get(i) );

		System.out.println();
		System.out.printf("�� �ݾ�                 �� %10d ��\n", totPrice);
		System.out.printf("%s ���� ���� �ܾ�   �� %10d ��\n", name, point);
		System.out.printf("���� �� %s���� �ܾ� �� %10d ��\n", name, point-totPrice);
		
		if (point < totPrice)	// �� ���� �� ����
		{
			//payflag = false;
			System.out.println();
			System.out.printf("�����ݾ� �� %d ��\n", totPrice - point); 
			System.out.println();
			System.out.println("���� �� �̿����ּ���.");
			System.out.println("�̿��� �������� �̵��մϴ�.");
			totPrice = 0; // �� �Ѿ� �� �ʱ�ȭ.
			clearCart2(); // ��ٱ��� ��� �� �����޴� ���
			
		}
		
		memList.get(us.pw).setPoint(point - totPrice);

		// ��������� �޼ҵ� ���
		receipt();

	}

	// �Ž����� �޼ҵ�
	public int returnChange(int sumCash)
	{
		// �Ž����� = �̿��� �� �ݾ� - �� �ݾ� 
		returnCash = sumCash - totPrice;

		return returnCash;
	}


	// ��������� �޼ҵ�
	private void receipt()
	{
		LocalDate nowDate = LocalDate.now();	//-- ���� ��¥
		LocalTime nowTime = LocalTime.now();	//-- ���� �ð�
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH�� mm�� ss��");	//-- ���� ����
		String formatTime = nowTime.format(formatter);	//-- ���� ����
		
		System.out.println();
		System.out.println("�� �������� ����մϴ� ��");
		System.out.println();
		System.out.println("===========================================");
		System.out.println("         2�� ���������� (������)");
		System.out.println("-------------------------------------------");
		System.out.println("��ǰ��                ����            ����");
		System.out.println("-------------------------------------------");

		for (int i = 0; i < nameLists.size(); i++)
			System.out.printf("%-10s\t%10d\t%10d\n", nameLists.get(i), amountLists.get(i), priceLists.get(i));
		 
		System.out.println("-------------------------------------------"); 
		System.out.printf("�Ǹűݾ�%34d\n", totPrice);
		System.out.println("-------------------------------------------");
		System.out.println(nowDate + " " + formatTime);
		System.out.println("\n�̿����ּż� �����մϴ� *^^*");
		System.out.println("===========================================");
		System.out.println();
		System.out.println();

		AdSystem.totalSales += totPrice;
		calCateTot();

		Kiosk ks = new Kiosk();
		totPrice = 0;
		UserSystem.loginflag = false;
		ks.systemOn();

	}	

	// ī�װ� �� ���
	public void calCateTot()
	{
		salesCate1 = 0; 
		salesCate2 = 0; 
		salesCate3 = 0;
		
		int category = 1;

		for (int i = 0; i < nameLists.size(); i++)
		{	
			category = pdList.get(nameLists.get(i)).getCategory();

			if (category == 1)
				salesCate1 += amountLists.get(i) * priceLists.get(i);
			else if (category == 2)
				salesCate2 += amountLists.get(i) * priceLists.get(i);
			else	// category == 3
				salesCate3 += amountLists.get(i) * priceLists.get(i);
		}	

		AdSystem.totalCate1 += salesCate1;
		AdSystem.totalCate2 += salesCate2;
		AdSystem.totalCate3 += salesCate3;
		

	}
}