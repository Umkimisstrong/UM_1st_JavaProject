package com.test;

class Products
{
	private int price;
	private int product_Num;
	private int category;
	private int count;        //-- ��� ī���� ������ 0 �ʱ�ȭ 
	private int	addCount;	  //-- ���� ī���� ������ ī��Ʈ��ŭ ����

	//������
	Products(int price, int product_Num, int category)
	{
		this.price = price;
		this.product_Num = product_Num;
		this.category = category;
	}

	// getter
	public int getPrice()
	{
		return price;
	}

	public int getProduct_Num()
	{
		return product_Num;
	}

	public int getCategory()
	{
		return category;
	}

	public int getCount()
	{
		return count;
	}

	public int getAddCount()
	{
		return addCount;
	}
	

	// setter
	public void setPrice(int price)
	{
		this.price = price;
	}

	public void setProduct_Num(int product_Num)
	{
		this.product_Num = product_Num;
	}

	public void setCategory(int category)
	{
		this.category = category;
	}

	public void setAddCount(int addCount)
	{
		this.addCount = addCount;
	}
}

