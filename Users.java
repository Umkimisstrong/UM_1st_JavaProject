package com.test;

import java.util.Scanner;
//import java.util.HashMap;

public class Users
{
	Kiosk ks;
	String name, tel, ssn;	 //-- �̸�, ��ȭ��ȣ, �ֹι�ȣ
	String con;				 //-- �ΰ����ΰ�

	static String keyofUser; //-- �α��� ������
	static String nameofUser;

	// �ֹι�ȣ ��ȿ�� Ȯ��
	public boolean ssnAva(String ssn) 
	{		
		// ��ȿ�� �˻� ���ϱ� ���� �迭
		int[] chk = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5 };

		// �Է¹��� �ֹι�ȣ
		String ssnSample;
		
		// ���� �� ����
		int tot = 0;
		boolean flag = true;

		ssnSample = ssn;

		// 14 �ڸ� �ƴϸ� ��� �Է�
		if (ssnSample.length() != 14)
		{
			//System.out.println(">> �Է¿���~!!!");
			return flag = false;
		}

		// ��ȿ�� �˻�
		for (int i = 0; i < 13; i++)
		{
			if (i == 6)
				continue;

			tot += chk[i] * Integer.parseInt(ssnSample.substring(i, (i+1)));
		}

		int su = 11 - tot % 11;
		su = su % 10;
		
		// ���� ��� ���
		if (su == Integer.parseInt(ssnSample.substring(13)))
			return flag;
		else
		{
			flag = false;
			return flag;
		}
	} 

	// �̼����� Ȯ��
	public boolean ssnAdult(String ssn)	
	{
		boolean adult;

		if (ssnAva(ssn))										// ��ȿ�� �˻� �޼ҵ� true �϶��� Ȯ��
		{
			int age = Integer.parseInt(ssn.substring(0, 2));		// �ֹι�ȣ �� ���ڸ�(�⵵) ������
                
			if(age <= 22)
				age = Integer.parseInt(20 + ssn.substring(0, 2));  //�⵵�� 22�⺸�� ������ 2000��� ���
			else
				age = Integer.parseInt(19 + ssn.substring(0, 2));  //�⵵�� 22�⺸�� ũ�� 1900��� ���
			
			age = 2022 - age + 1;									// 2022 - 1995 + 1 = 28

			if(age >= 19)
			   adult = true;
			else
			   adult = false;

			return adult;
		}
		else													// ��ȿ�� �˻� Ʋ���� false
			adult = false;

		return adult;											// �����̸� true, �̼����ڸ� false ��ȯ
    }
	
	// �ڵ��� �ڸ��� Ȯ��
	private boolean telAva(String telnum) 
	{
		boolean telFlag = true;
		String telnumSample;

		telnumSample = telnum;

		if (telnumSample.length() != 13)
			telFlag = false;

		return telFlag;
	}

	// �ڵ�����ȣ �����Է� Ȯ��
	public boolean telInt(String telnum)
	{
		for (int i = 0; i < telnum.length(); i++)
		{
			if (i == 3 || i == 8)
				continue;

			if (telnum.charAt(i) < 48 || telnum.charAt(i) > 57)
				return false;	
		}
	    return true;
	}
	
	// ȸ������ 
	public void joinning()
	{
		Scanner sc = new Scanner(System.in);
		ks = new Kiosk();	

		MemData md = new MemData();

		md.TestMemberData();

		System.out.println();
		System.out.println("============== [ȸ������] ==============");
		System.out.print("�̸� �� ");
		name = sc.next();
		nameofUser = name;

		do
		{
			 System.out.print("�޴��� ��ȣ('-'����) �� ");
			 tel = sc.next();

			 if (md.memList.containsKey(tel))
			 {
				System.out.println();
				System.out.println("�̹� ���Ե� ��ȭ��ȣ�Դϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
				joinning();
			 }
		}
		while (!telAva(tel) || !telInt(tel));

		keyofUser = tel;
		
		MemData.memList.put(keyofUser, new Members(nameofUser));

		System.out.println();
		System.out.printf("�� ȯ���մϴ�~! %s ��, ȸ�������� �Ϸ�Ǿ����ϴ� ��\n", name);
		System.out.println();
		System.out.println("������ ���� ID �� �̸�");
		System.out.println("������ ���� PW �� �޴��� ��ȣ");
		System.out.println();

		System.out.print("�� ���� ����(Y/N) �� ");
		con = sc.next();

		if (con.equalsIgnoreCase("Y"))
		{
			System.out.println();
			System.out.println("=========== �� ���� ===========");
			System.out.println("�̸�         �� " + name);
			System.out.println("�޴��� ��ȣ  �� " + tel);
			System.out.println("===============================");

			ks.systemOn();
		}
		else
			ks.systemOn();
	}

	public void logout()
	{
		ks = new Kiosk();
		ks.systemOn();	// �α��� �� ȭ������ ���ư�.
	}
}

	