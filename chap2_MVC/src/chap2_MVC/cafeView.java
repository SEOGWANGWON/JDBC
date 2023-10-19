package chap2_MVC;

import java.util.Scanner;

public class cafeView {
	// cafeModel �̶�� Ŭ������ ������ ���� ���� ��������� ī�� ���� �߰���
	public cafeModel model;
	
	// model �Ű������� ���� �� �ִ� �����ڸ� ����������
	public cafeView(cafeModel model) {
		this.model = model;
	}
	
	// name, address, phone_number, operating_hours
	public void addCafeName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ī�� ������ �Է��ϼ���.");
		
		System.out.print("��ȣ�� : ");
		String name =  sc.nextLine();
		
		System.out.println("ī�� �ּ� : ");
		String address = sc.nextLine();
		
		System.out.println("ī�� ��ȭ��ȣ : ");
		String phone_number = sc.nextLine();
		
		System.out.println("ī�� �����ð� : ");
		String operating_hours = sc.nextLine();
		
		// ī�� �𵨿��� insertCafe ��� �޼��带 ������ �;���
		
		model.insertCafe(name, address, phone_number, operating_hours);
		System.out.println("ī�䰡 ���������� �߰��Ǿ����ϴ�.");
		
	}
	public void updateMenu() { // view ���� ����ڰ� ���� ���ϰ� ���� �ؾ��Ѵ�.
		Scanner sc = new Scanner(System.in);
		System.out.println("�޴� ������ ������Ʈ�ϼ���.");
		
		System.err.println("ī�� ID : ");
		int cafeID = sc.nextInt();
		
		System.out.println("�޴� ID : ");
		int menuID = sc.nextInt();		
		
		System.out.println("�޴����� ���� : ");
		sc.nextLine();
		
		/*
		 nextInt()�� ���๮��(���� '\n') ������ ���ڸ� �Է� �޴� �� ���� ���๮�� ���� 
		 �Է��� �޴� nextLine()�� ����ϰ� �Ǹ� ���ۿ� �����ִ� 
		 ���๮�� �ϳ��� �Է��� ���� �Ŀ� �޼ҵ尡 ����Ǳ� ������ �߻� �Ѵ�. 
		 */
		String description = sc.nextLine();
		
		// model �� �ִ� UpdateMenu�� ������ͼ�
		// ����ڰ� �ۼ��� ���� ���� �߰��ϱ�
		
		model.UpdateMenu(description, menuID, cafeID);
		System.out.println("�޴������� ���������� ������Ʈ �Ǿ����ϴ�.");
		
		
	}
	public void updateCafe() {
		Scanner sc = new Scanner(System.in);
	
		System.out.println("������ ī���� ID�� ��ð��� �Է����ּ���.");
		System.out.println("������ ī�� ID : ");
		int cafe_id = sc.nextInt();
		
		System.out.println("������ ī���� ��ð� : ");
		sc.nextLine(); 
		/*
		 nextInt()�� ���๮��(���� '\n') ������ ���ڸ� �Է� �޴� �� ���� ���๮�� ���� 
		 �Է��� �޴� nextLine()�� ����ϰ� �Ǹ� ���ۿ� �����ִ� 
		 ���๮�� �ϳ��� �Է��� ���� �Ŀ� �޼ҵ尡 ����Ǳ� ������ �߻� �Ѵ�. 
		 */
		String operating_hours = sc.nextLine();
		
		
		model.UpdateCafe(operating_hours, cafe_id);
		System.out.println("ī���� ��ð��� ���������� ������Ʈ �Ǿ����ϴ�.");
	
	}
	public void deleteCafe() {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �����ϰٽ��ϴ�.");
		System.out.println("������ ī���� ID�� �Է��ϼ���.");
		//int cafeId_other = Integer.parseInt(sc.nextLine());
		// ���ڿ��� ���ڷ� ��ȯ���ִ� ���
		
		int cafeId = sc.nextInt();
		
		model.deleteCafe(cafeId);
		System.out.println("�Է��Ͻ� ī�䰡 �����Ǿ����ϴ�.");
		//����Բ� ����. �����ڰ� View�� ���� ����ڿ��� ���� ģ���ϰ� �Է�������
		// ex) �Է��Ͻ� ID�� ���� ī�䰡 �����Ǿ����ϴ�. �̷������� 
		// View �� �������� �ʴ� ������ ���� �ִ°��ΰ�?!
		
		
	}
	public void deleteMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�����Ͻ� �޴��� ID�� �Է��ϼ���.");
		int menuId = sc.nextInt();
		
		model.deleteMenu(menuId);
		System.out.println("�Է��Ͻ� �޴��� �����߽��ϴ�.");
		
		
	}
	public void deleteOrder() {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ orderId �� �����ּ���.");
		int orderId = sc.nextInt();
		
		model.deleteOrder(orderId);
		System.out.println("�ֹ��� �����Ǿ����ϴ�.");
		
		
	}
}
