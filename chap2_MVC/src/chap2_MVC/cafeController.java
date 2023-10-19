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
		
		//�Ǵ� while(choice != 9) �ε� ���ټ��ִ�.
		while(isTrue) {
			Scanner sc = new Scanner(System.in);
			
			//������ ��ȣ ��� �޼���� �Է�
			System.out.println("1. ī�� ���� �߰�");
			System.out.println("2. �޴� ���� ����");
			System.out.println("3. ī�� ��ð� ����");
			System.out.println("4. ī�� ���� ����");
			System.out.println("5. ������ ī�� �޴� �Է� ");
			System.out.println("6. ������ �ֹ� �Է�");
			System.out.println("9. ����");
			System.out.println("���ϴ� �۾��� �������ּ���.");
			
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
					System.out.println("���α׷��� �����մϴ�.");
					isTrue = false;
					break;
					
				default:
					System.out.println("�峭ġ����!!!!!!!!!!!!!");
					
			}
			
		}
		
		
		
		
	}

}
