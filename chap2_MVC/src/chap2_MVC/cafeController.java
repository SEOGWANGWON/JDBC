package chap2_MVC;

import java.util.Scanner;

public class cafeController {
	public cafeModel model;
	public cafeView view;
	
	
	public cafeController(cafeModel model, cafeView view) {
		this.model = model;
		this.view = view;
		
	}
	//run
	public void run() {
		boolean isTrue = true;
		
		//또는 while(choice != 9) 로도 해줄수있다.
		while(isTrue) {
			Scanner sc = new Scanner(System.in);
			
			//선택할 번호 출력 메서드로 입력
			System.out.println("1. 카페 정보 추가");
			System.out.println("2. 메뉴 설명 변경");
			System.out.println("3. 카페 운영시간 변경");
			System.out.println("4. 카페 정보 삭제");
			System.out.println("5. 삭제할 카페 메뉴 입력 ");
			System.out.println("6. 삭제할 주문 입력");
			System.out.println("9. 종료");
			System.out.println("원하는 작업을 선택해주세요.");
			
			int choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					view.addCafeName();
					break;
				case 2:
					view.updateMenu();
					break;
				case 3:
					view.updateCafe();
					break;
				case 4:
					view.deleteCafe();
					break;
				case 5:
					view.deleteMenu();
					break;
				case 6:
					view.deleteOrder();
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					isTrue = false;
					break;
					
				default:
					System.out.println("장난치지마!!!!!!!!!!!!!");
					
			}
			
		}
		
		
		
		
	}

}
