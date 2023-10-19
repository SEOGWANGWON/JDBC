package chap2_MVC;

public class Main {

	public static void main(String[] args) {
		// 카페 모델과 카페 뷰를 가지고와야지
		
		cafeModel model = new cafeModel();
		cafeView view = new cafeView(model);
		cafeController controller = new cafeController(model, view);
		
		//view.addCafeName();
		//view.updateMenu();
		//view.updateCafe(); // 1. cafeModel// 카페 운영시간 수정하기
		//view.deleteCafe();
		//view.deleteMenu();
		//view.deleteOrder();
		
		controller.run();
	}

}
