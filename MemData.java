package com.test;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.List;
//import java.util.ArrayList;


class MemData extends Project
{	
	// ī�װ� �� �ķ�ǰ
	public void printList1()
	{

		//-------------------------------------------------------------------------------//
		//							[ī�װ����� ���� �ϱ�(����ϱ�)]
			
			//------------------------------[�ķ�ǰ ���]---------------------	
		System.out.println();
		System.out.println("---------------------- [�ķ�ǰ] --------------------------");
		for (String key :pdList.keySet())
		{
			Products value = pdList.get(key);

			if (value.getCategory() == 1)
				System.out.printf("��ǰ�� : %-8s\t���� : %-5d   �������� : %3d\n",key,
				value.getPrice(), value.getProduct_Num());
		}
		System.out.println("----------------------------------------------------------");
	}

	// ī�װ� �� ��Ȱ��ǰ
	public void printList2()
	{
		//------------------------------[��Ȱ��ǰ ���]---------------------
		System.out.println();
		System.out.println("----------------------- [��Ȱ��ǰ] -----------------------");
		for (String key : pdList.keySet())
		{
			Products value = pdList.get(key);
			if (value.getCategory() == 2)
				System.out.printf("��ǰ�� : %-8s\t���� : %-5d   �������� : %3d\n",key,
				value.getPrice(), value.getProduct_Num());
		}
		System.out.println("----------------------------------------------------------");
	}

	// ī�װ� �� �������ѹ�ǰ(��/���)
	public void printList3()
	{
		//------------------------------[�������� ���]---------------------
		System.out.println();
		System.out.println("--------------------- [�������� ��ǰ] --------------------");
		for (String key : pdList.keySet())
		{
			Products value = pdList.get(key);
			if (value.getCategory() == 3)
				System.out.printf("��ǰ�� : %-8s\t���� : %-5d   �������� : %3d\n",key,
				value.getPrice(), value.getProduct_Num());
		}
		System.out.println("----------------------------------------------------------");
	}
}

