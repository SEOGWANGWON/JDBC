package chap2_MVC;

public class Main {

	public static void main(String[] args) {
		// ī�� �𵨰� ī�� �並 ������;���
		
		cafeModel model = new cafeModel();
		cafeView view = new cafeView(model);
		cafeController controller = new cafeController(model, view);
		
		//view.addCafeName();
		//view.updateMenu();
		//view.updateCafe(); // 1. cafeModel// ī�� ��ð� �����ϱ�
		//view.deleteCafe();
		//view.deleteMenu();
		//view.deleteOrder();
		
		controller.run();
	}

}
