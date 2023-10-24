package com.kh.MVC.orders;

import java.util.List;
import java.util.Scanner;

public class OrderView { // 출력하기 위한 View 클래스
	
	public void showTodayTotalPrice(double dayTotalPrice) {
		System.out.println("하루 매출 : " + dayTotalPrice);
	}

	
	public void showAllOrders(List<OrderDTO> orders) {
		for(OrderDTO o : orders) {
			System.out.println("주문 번호 : " + o.getOrder_id());
			System.out.println("카페 번호 : " + o.getCafe_id());
			System.out.println("메뉴 번호 : " + o.getMenu_id());
			System.out.println("메뉴 수량 : " + o.getQuantity());
			System.out.println("총 가격 : " + o.getTotal_price());
			System.out.println("======================================");
		}
		
	}
	
	/*
	public void choiceMenu() {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("장바구니에 담고싶은 메뉴번호를 입력해주세요");
		while(true) {
		System.out.println("메뉴번호 입력 : ");
		int userMenu = sc.nextInt();
		
		
		}
		*/ // 폐기 
		
		
	

}
