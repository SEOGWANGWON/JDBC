package com.kh.MVC.shopping;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	// ArrayList
	private List<ProductDTO> cartList;
	
	// 쇼핑카트 생성자
	public ShoppingCart() {
		cartList = new ArrayList<>();
	}
	
	public List<ProductDTO> getCartList(){
		return cartList;
	}
	
	// 장바구니 최종 결제 금액 메서드
	public double cartTotalPrice() {
		double totalPrice = 0;
		// 향상된 for문을 이용해서 가격추가
		for(ProductDTO c : cartList) {
			totalPrice += c.getPrice();
		}
		
		return totalPrice;
	}
	
	
	// 장바구니에 추가할 메서드 생성
	public void addCart(ProductDTO product/*파라미터(매개변수) 값 추가*/) {
		cartList.add(product);
		// System.out.println(product.getProduct_name() + "장바구니에 추가했습니다.");
		
	}
	
	// 장바구니에서 제품을 제거할 메서드 생성
	public void removeGoods(ProductDTO product) {
		if (cartList.remove(product)) {
			System.out.println("장바구니에서 제거했습니다.");
		} else {
			System.out.println("장바구니에 해당 제품이 없습니다.");
		}
	}

}
