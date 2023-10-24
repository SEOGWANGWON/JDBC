package com.kh.MVC.shopping;

import java.util.List;
import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		ProductController controller = new ProductController(dao);
		ProductView view = new ProductView();
		
		List<ProductDTO> products = controller.getAllProducts();
		
		Scanner sc = new Scanner(System.in);
		
		// 쇼핑카트 인스턴스 생성자리
		ShoppingCart cart = new ShoppingCart();
		System.out.println("메뉴를 선택해주세요.");
		int choice = sc.nextInt();
		switch(choice) {
		
			case 1:
				//모든 제품 리스트 가지고오기
				view.showProductList(products);
		
			case 2:
				//최종가격 계산 가져오기
				double totalPrice = controller.calculateTotalPrice(products);
				view.showTotalPrice(totalPrice);
			
			case 3:
				// 제품 추가
				System.out.println("장바구니에 추가할 제품의 ID를 입력하세요.");
				// 제품 아이디 입력 받기
				int productId = sc.nextInt();
				// 향상된 for문과 if문을 활용해서 제품 담기
				for(ProductDTO p : products) {
					if(p.getProduct_id() == productId) {
						// 카트에 추가
						cart.addCart(p);
						System.out.println(p.getProduct_name() + "을 장바구니에 담았습니다.");
						break;
					}
				}
				
			case 4:
				// 제거
				System.out.println("장바구니에서 제거할 제품의 ID를 입력하세요.");
				int removeProductId = sc.nextInt();
				for(ProductDTO r : cart.getCartList() /*장바구니 목록*/) {
					if(r.getProduct_id() == removeProductId) {
						cart.removeGoods(r);
						break;
					}
				}
				
			case 5:
				// 결제
				UserPay pay = new UserPay(cart); 
				boolean payResult = pay.payment();
				if(payResult) {
					// 카드 비우기 cart
				}
				break;
					
		
				
			default:
				System.out.println("잘못된 선택입니다.");
		
		}
	}

}
