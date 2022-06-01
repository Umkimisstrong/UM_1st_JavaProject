package com.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;

class Buy extends Cart
{ 
	//public static boolean payflag = true;

	int returnCash = 0;		//-- 거스름돈
	int sumCash = 0;		//-- 누적 투입 현금

	static int salesCate1, salesCate2, salesCate3;	//-- 카테고리별 매출 계산

	static int totPrice = 0;

	// (회원) 결제수단 선택
	public void selPay()
	{
		Scanner sc = new Scanner(System.in);
		
		
		int sel;
		
		System.out.println();
		System.out.println("====== 결제수단 선택 ======");
		
		try
		{
			if (UserSystem.loginflag == true)
			{
				do
				{
					System.out.println("    1. 현금 결제");
					System.out.println("    2. 잔액 결제");
					System.out.println("    3. 장바구니 비우기");
					System.out.println("===========================");
					System.out.println("* 참고 : 비회원은 현금결제만 가능합니다.");
					System.out.print("▶ ");
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
					System.out.println("    1. 현금 결제");
					System.out.println("    2. 장바구니 비우기");
					System.out.println("===========================");
					System.out.println("* 참고 : 비회원은 현금결제만 가능합니다.");
					System.out.print("▶ ");
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
			System.out.println("숫자 입력해 주세요~~");	
			totPrice = 0;
			selPay();
		}
		
	}

	// 현금결제 메소드 (이용자, 비회원 둘 다 이용)
	public void payCash()
	{
		Scanner sc = new Scanner(System.in);

		int inputCash;		//-- 넣은 현금

		for (int i = 0; i < nameLists.size(); i++)
			totPrice += ( amountLists.get(i) * priceLists.get(i) );
		
		
		System.out.println();
		System.out.printf("총 금액          ▶ %d\n", totPrice);
		System.out.print("현금 넣어주세요  ▶ ");
		inputCash = sc.nextInt();

		sumCash = inputCash;

		if (inputCash < totPrice)
		{
			System.out.println();
			System.out.printf("%d원 부족\n", totPrice - sumCash);
			System.out.print("현금 넣어주세요  ▶ ");
			inputCash = sc.nextInt();

			sumCash += inputCash;

			if (sumCash < totPrice)
			{
				System.out.println();
				System.out.printf("%d원 부족\n", totPrice - sumCash);
				System.out.println("결제가 취소되었습니다.");
				System.out.println("카테고리로 돌아갑니다.");
				totPrice = 0; // 고른 총액 다 초기화.
				clearCart();
				//ks.systemOn();

			}
		}
		if (sumCash == totPrice)
		{
			System.out.println("\n★ 결제가 완료되었습니다 ★\n");
		}
		else if (sumCash > totPrice)
		{
			System.out.println("\n★ 결제가 완료되었습니다 ★\n");

			returnCash = returnChange(sumCash);		//-- 거스름돈 메소드 호출

			System.out.printf("넣은  돈 ▶ %8d 원\n", sumCash);
			System.out.printf("거스름돈 ▶ %8d 원\n", returnCash);
			System.out.println();
		}

		// 영수증출력 메소드 호출
		receipt();
	}
//==================================================================================================//
	
	// 잔액결제 메소드 (이용자만)
	public void payPoint()
	{
		Scanner sc = new Scanner(System.in);

		UserSystem us = new UserSystem();
		Purchase pc = new Purchase();

		String name = memList.get(us.pw).getName();
		int point = memList.get(us.pw).getPoint();
		
		// 총 금액 = 장바구니에 담긴 상품수량*상품가격 
		for (int i = 0; i < nameLists.size(); i++)
			totPrice += ( amountLists.get(i) * priceLists.get(i) );

		System.out.println();
		System.out.printf("총 금액                 ▶ %10d 원\n", totPrice);
		System.out.printf("%s 님의 현재 잔액   ▶ %10d 원\n", name, point);
		System.out.printf("결제 후 %s님의 잔액 ▶ %10d 원\n", name, point-totPrice);
		
		if (point < totPrice)	// → 충전 후 진행
		{
			//payflag = false;
			System.out.println();
			System.out.printf("부족금액 ▶ %d 원\n", totPrice - point); 
			System.out.println();
			System.out.println("충전 후 이용해주세요.");
			System.out.println("이용자 페이지로 이동합니다.");
			totPrice = 0; // 고른 총액 다 초기화.
			clearCart2(); // 장바구니 비운 후 유저메뉴 출력
			
		}
		
		memList.get(us.pw).setPoint(point - totPrice);

		// 영수증출력 메소드 출력
		receipt();

	}

	// 거스름돈 메소드
	public int returnChange(int sumCash)
	{
		// 거스름돈 = 이용자 낸 금액 - 총 금액 
		returnCash = sumCash - totPrice;

		return returnCash;
	}


	// 영수증출력 메소드
	private void receipt()
	{
		LocalDate nowDate = LocalDate.now();	//-- 현재 날짜
		LocalTime nowTime = LocalTime.now();	//-- 현재 시간
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");	//-- 포맷 정의
		String formatTime = nowTime.format(formatter);	//-- 포맷 적용
		
		System.out.println();
		System.out.println("★ 영수증을 출력합니다 ★");
		System.out.println();
		System.out.println("===========================================");
		System.out.println("         2조 무인편의점 (영수증)");
		System.out.println("-------------------------------------------");
		System.out.println("상품명                수량            가격");
		System.out.println("-------------------------------------------");

		for (int i = 0; i < nameLists.size(); i++)
			System.out.printf("%-10s\t%10d\t%10d\n", nameLists.get(i), amountLists.get(i), priceLists.get(i));
		 
		System.out.println("-------------------------------------------"); 
		System.out.printf("판매금액%34d\n", totPrice);
		System.out.println("-------------------------------------------");
		System.out.println(nowDate + " " + formatTime);
		System.out.println("\n이용해주셔서 감사합니다 *^^*");
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

	// 카테고리 합 계산
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