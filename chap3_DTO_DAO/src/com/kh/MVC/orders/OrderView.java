package com.kh.MVC.orders;

import java.util.List;
import java.util.Scanner;

public class OrderView { // ����ϱ� ���� View Ŭ����
	
	public void showTodayTotalPrice(double dayTotalPrice) {
		System.out.println("�Ϸ� ���� : " + dayTotalPrice);
	}

	
	public void showAllOrders(List<OrderDTO> orders) {
		for(OrderDTO o : orders) {
			System.out.println("�ֹ� ��ȣ : " + o.getOrder_id());
			System.out.println("ī�� ��ȣ : " + o.getCafe_id());
			System.out.println("�޴� ��ȣ : " + o.getMenu_id());
			System.out.println("�޴� ���� : " + o.getQuantity());
			System.out.println("�� ���� : " + o.getTotal_price());
			System.out.println("======================================");
		}
		
	}
	
	/*
	public void choiceMenu() {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("��ٱ��Ͽ� ������ �޴���ȣ�� �Է����ּ���");
		while(true) {
		System.out.println("�޴���ȣ �Է� : ");
		int userMenu = sc.nextInt();
		
		
		}
		*/ // ��� 
		
		
	

}
