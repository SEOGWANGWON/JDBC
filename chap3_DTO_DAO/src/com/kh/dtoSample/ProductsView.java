package com.kh.dtoSample;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductsView {
	

	
	// 리스트에 저장되어 있는 값을 보여줄 display 메서드
	public void displayProducts(List<ProductsDTO> products) {
		// 멤버 변수를 작성한것도 아니고 리스트 인스턴스를 생성한것도 아닌데 출력이 되는이유는
		// controller 에서 displayAllProducts() 메서드 안에서 사용 될 꺼기 때문
		// 이 메서드는 값을 받을 필요가없음 생성자로서 역할을 다함
		
		for(ProductsDTO p : products) {
			System.out.println("Product ID : " + p.getProductId());
			System.out.println("Product Name : " + p.getProductName());
			System.out.println("Category : " + p.getCategory());
			System.out.println("Price : " + p.getPrice());
			System.out.println("Stock Quantity : " + p.getStockQuantity());
			System.out.println();
		}
		
	}
	
	
	
}
