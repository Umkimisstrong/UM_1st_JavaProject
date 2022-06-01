package com.test;

import java.util.Scanner;
//import java.util.HashMap;

public class Users
{
	Kiosk ks;
	String name, tel, ssn;	 //-- 이름, 전화번호, 주민번호
	String con;				 //-- 부가적인것

	static String keyofUser; //-- 로그인 받을때
	static String nameofUser;

	// 주민번호 유효성 확인
	public boolean ssnAva(String ssn) 
	{		
		// 유효성 검사 곱하기 해줄 배열
		int[] chk = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5 };

		// 입력받은 주민번호
		String ssnSample;
		
		// 곱셈 후 누적
		int tot = 0;
		boolean flag = true;

		ssnSample = ssn;

		// 14 자리 아니면 계속 입력
		if (ssnSample.length() != 14)
		{
			//System.out.println(">> 입력오류~!!!");
			return flag = false;
		}

		// 유효성 검사
		for (int i = 0; i < 13; i++)
		{
			if (i == 6)
				continue;

			tot += chk[i] * Integer.parseInt(ssnSample.substring(i, (i+1)));
		}

		int su = 11 - tot % 11;
		su = su % 10;
		
		// 최종 결과 출력
		if (su == Integer.parseInt(ssnSample.substring(13)))
			return flag;
		else
		{
			flag = false;
			return flag;
		}
	} 

	// 미성년자 확인
	public boolean ssnAdult(String ssn)	
	{
		boolean adult;

		if (ssnAva(ssn))										// 유효성 검사 메소드 true 일때만 확인
		{
			int age = Integer.parseInt(ssn.substring(0, 2));		// 주민번호 앞 두자리(년도) 가져옴
                
			if(age <= 22)
				age = Integer.parseInt(20 + ssn.substring(0, 2));  //년도가 22년보다 작으면 2000년대 출생
			else
				age = Integer.parseInt(19 + ssn.substring(0, 2));  //년도가 22년보다 크면 1900년대 출생
			
			age = 2022 - age + 1;									// 2022 - 1995 + 1 = 28

			if(age >= 19)
			   adult = true;
			else
			   adult = false;

			return adult;
		}
		else													// 유효성 검사 틀리면 false
			adult = false;

		return adult;											// 성인이면 true, 미성년자면 false 반환
    }
	
	// 핸드폰 자릿수 확인
	private boolean telAva(String telnum) 
	{
		boolean telFlag = true;
		String telnumSample;

		telnumSample = telnum;

		if (telnumSample.length() != 13)
			telFlag = false;

		return telFlag;
	}

	// 핸드폰번호 숫자입력 확인
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
	
	// 회원가입 
	public void joinning()
	{
		Scanner sc = new Scanner(System.in);
		ks = new Kiosk();	

		MemData md = new MemData();

		md.TestMemberData();

		System.out.println();
		System.out.println("============== [회원가입] ==============");
		System.out.print("이름 ▶ ");
		name = sc.next();
		nameofUser = name;

		do
		{
			 System.out.print("휴대폰 번호('-'포함) ▶ ");
			 tel = sc.next();

			 if (md.memList.containsKey(tel))
			 {
				System.out.println();
				System.out.println("이미 가입된 전화번호입니다.");
				System.out.println("다시 입력해주세요.");
				joinning();
			 }
		}
		while (!telAva(tel) || !telInt(tel));

		keyofUser = tel;
		
		MemData.memList.put(keyofUser, new Members(nameofUser));

		System.out.println();
		System.out.printf("★ 환영합니다~! %s 님, 회원가입이 완료되었습니다 ★\n", name);
		System.out.println();
		System.out.println("귀하의 접속 ID ▶ 이름");
		System.out.println("귀하의 접속 PW ▶ 휴대폰 번호");
		System.out.println();

		System.out.print("내 정보 보기(Y/N) ▶ ");
		con = sc.next();

		if (con.equalsIgnoreCase("Y"))
		{
			System.out.println();
			System.out.println("=========== 내 정보 ===========");
			System.out.println("이름         ▶ " + name);
			System.out.println("휴대폰 번호  ▶ " + tel);
			System.out.println("===============================");

			ks.systemOn();
		}
		else
			ks.systemOn();
	}

	public void logout()
	{
		ks = new Kiosk();
		ks.systemOn();	// 로그인 전 화면으로 돌아감.
	}
}

	