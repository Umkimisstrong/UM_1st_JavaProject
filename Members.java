package com.test;

class Members
{
	private String name;	//-- 회원 이름
	private int point;		//-- 잔액


	// 생성자
	Members(String name)
	{
		this.name = name;
	}

    // getter
	public String getName()
	{
		return name;
	}


	public int getPoint()
	{
		return point;
	}
	
	// setter
	public void setName(String name)
	{
		this.name = name;
	}


	public void setPoint(int point)
	{
		this.point = point;	
	}

}






	