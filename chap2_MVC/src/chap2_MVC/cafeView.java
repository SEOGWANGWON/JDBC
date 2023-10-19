package chap2_MVC;

import java.util.Scanner;

public class cafeView {
	// cafeModel 이라는 클래스를 가지고 오기 위해 멤버변수로 카페 모델을 추가함
	public cafeModel model;
	
	// model 매개변수를 받을 수 있는 생성자를 만들어줘야함
	public cafeView(cafeModel model) {
		this.model = model;
	}
	
	// name, address, phone_number, operating_hours
	public void addCafeName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("카페 정보를 입력하세요.");
		
		System.out.print("상호명 : ");
		String name =  sc.nextLine();
		
		System.out.println("카페 주소 : ");
		String address = sc.nextLine();
		
		System.out.println("카페 전화번호 : ");
		String phone_number = sc.nextLine();
		
		System.out.println("카페 영업시간 : ");
		String operating_hours = sc.nextLine();
		
		// 카페 모델에서 insertCafe 라는 메서드를 가지고 와야함
		
		model.insertCafe(name, address, phone_number, operating_hours);
		System.out.println("카페가 성공적으로 추가되었습니다.");
		
	}
	public void updateMenu() { // view 에선 사용자가 보기 편하게 설정 해야한다.
		Scanner sc = new Scanner(System.in);
		System.out.println("메뉴 설명을 업데이트하세요.");
		
		System.err.println("카페 ID : ");
		int cafeID = sc.nextInt();
		
		System.out.println("메뉴 ID : ");
		int menuID = sc.nextInt();		
		
		System.out.println("메뉴설명 수정 : ");
		sc.nextLine();
		
		/*
		 nextInt()는 개행문자(엔터 '\n') 전까지 숫자를 입력 받는 데 이후 개행문자 까지 
		 입력을 받는 nextLine()을 사용하게 되면 버퍼에 남아있는 
		 개행문자 하나만 입력을 받은 후에 메소드가 종료되기 때문에 발생 한다. 
		 */
		String description = sc.nextLine();
		
		// model 에 있는 UpdateMenu를 가지고와서
		// 사용자가 작성한 수정 내용 추가하기
		
		model.UpdateMenu(description, menuID, cafeID);
		System.out.println("메뉴설명이 성공적으로 업데이트 되었습니다.");
		
		
	}
	public void updateCafe() {
		Scanner sc = new Scanner(System.in);
	
		System.out.println("변경할 카페의 ID와 운영시간을 입력해주세요.");
		System.out.println("변경할 카페 ID : ");
		int cafe_id = sc.nextInt();
		
		System.out.println("변경할 카페의 운영시간 : ");
		sc.nextLine(); 
		/*
		 nextInt()는 개행문자(엔터 '\n') 전까지 숫자를 입력 받는 데 이후 개행문자 까지 
		 입력을 받는 nextLine()을 사용하게 되면 버퍼에 남아있는 
		 개행문자 하나만 입력을 받은 후에 메소드가 종료되기 때문에 발생 한다. 
		 */
		String operating_hours = sc.nextLine();
		
		
		model.UpdateCafe(operating_hours, cafe_id);
		System.out.println("카페의 운영시간이 성공적으로 업데이트 되었습니다.");
	
	}
	public void deleteCafe() {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제를 삭제하겟습니다.");
		System.out.println("삭제할 카페의 ID를 입력하세요.");
		//int cafeId_other = Integer.parseInt(sc.nextLine());
		// 문자열을 숫자로 변환해주는 방법
		
		int cafeId = sc.nextInt();
		
		model.deleteCafe(cafeId);
		System.out.println("입력하신 카페가 삭제되었습니다.");
		//강사님께 질문. 개발자가 View를 보는 사용자에게 좀더 친절하게 입력했을때
		// ex) 입력하신 ID를 가진 카페가 삭제되었습니다. 이런식으로 
		// View 를 설정하지 않는 이유가 따로 있는것인가?!
		
		
	}
	public void deleteMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제하실 메뉴의 ID를 입력하세요.");
		int menuId = sc.nextInt();
		
		model.deleteMenu(menuId);
		System.out.println("입력하신 메뉴를 삭제했습니다.");
		
		
	}
	public void deleteOrder() {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 orderId 를 적어주세요.");
		int orderId = sc.nextInt();
		
		model.deleteOrder(orderId);
		System.out.println("주문이 삭제되었습니다.");
		
		
	}
}
