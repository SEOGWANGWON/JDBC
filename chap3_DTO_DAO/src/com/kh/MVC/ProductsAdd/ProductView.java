package com.kh.MVC.ProductsAdd;
import java.util.List;

public class ProductView {
	
	
	public void showProductList(List<ProductDTO> products/*제품 리스트 파라미터 추가*/) {
		for (ProductDTO p : products) {
			System.out.println("제품명 : " + p.getProduct_name());
			System.out.println("가격 : " + p.getPrice());
			System.out.println("수량 : " + p.getStock_quantity());
			System.out.println("=========================");
		}
	}
	
	// 제품 최종 가격 메소드
	public void showTotalPrice(double totalPrice) {
		System.out.println("총 가격 : " + totalPrice);
	}
	




}
