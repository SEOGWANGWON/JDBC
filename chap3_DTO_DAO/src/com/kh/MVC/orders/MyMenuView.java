package com.kh.MVC.orders;

import java.util.List;
import java.util.Scanner;

public class MyMenuView {
	
	public MyMenuView() {
		
	}

	public void showAllMenus(List<MyMenuDTO> menus) {
		for(MyMenuDTO m : menus) {
			System.out.println("�޴� ��ȣ : " + m.getMenu_id());
			System.out.println("ī�� ��ȣ : " + m.getCafe_id());
			System.out.println("�޴� �̸� : " + m.getMenu_name());
			System.out.println("�޴� ���� : " + m.getPrice());
			System.out.println("�޴� ���� : " + m.getDescription());
			System.out.println("=======================================================");
		}
		
	}
	
	public void showMenuTotalPrice(double totalprice) {
		System.out.println("�ֹ��Ͻ� �޴� �� �����Դϴ� : " + totalprice);
	}
	
	/*		// ���
	public void choiceMenu() {
		Scanner sc = new Scanner(System.in);
		MyMenuDAO dao = new MyMenuDAO();
		
		
		while(true) {
		System.out.println("�ֹ��Ͻ� �޴��� �������ּ���");
		int usermenu = sc.nextInt();
		
		// dao.choiceMenus(usermenu); // ���
		System.out.println("�߰� �Ǿ����ϴ�.");
		System.out.println("�ֹ� ����� [E]�� �����ֽʽÿ�.");
		System.out.println("�ֹ��� ����Ͻ÷��� [c]�� �����ּ���.");
		String choice = sc.next();
		
		if(choice.equalsIgnoreCase("e")) {
			break;
		}
		}
		
		
	}*/
}
