package com.kh.MVC.orders;

import java.util.List;
import java.util.Scanner;

public class MyMenuView {
	
	public MyMenuView() {
		
	}

	public void showAllMenus(List<MyMenuDTO> menus) {
		for(MyMenuDTO m : menus) {
			System.out.println("메뉴 번호 : " + m.getMenu_id());
			System.out.println("카페 번호 : " + m.getCafe_id());
			System.out.println("메뉴 이름 : " + m.getMenu_name());
			System.out.println("메뉴 가격 : " + m.getPrice());
			System.out.println("메뉴 설명 : " + m.getDescription());
			System.out.println("=======================================================");
		}
		
	}
	
	public void showMenuTotalPrice(double totalprice) {
		System.out.println("주문하신 메뉴 총 가격입니다 : " + totalprice);
	}
	
	/*		// 폐기
	public void choiceMenu() {
		Scanner sc = new Scanner(System.in);
		MyMenuDAO dao = new MyMenuDAO();
		
		
		while(true) {
		System.out.println("주문하실 메뉴를 선택해주세요");
		int usermenu = sc.nextInt();
		
		// dao.choiceMenus(usermenu); // 폐기
		System.out.println("추가 되었습니다.");
		System.out.println("주문 종료는 [E]를 눌러주십시요.");
		System.out.println("주문을 계속하시려면 [c]를 눌러주세요.");
		String choice = sc.next();
		
		if(choice.equalsIgnoreCase("e")) {
			break;
		}
		}
		
		
	}*/
}
