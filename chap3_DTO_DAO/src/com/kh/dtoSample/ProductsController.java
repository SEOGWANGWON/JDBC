package com.kh.dtoSample;

import java.sql.Connection;
import java.util.List;

public class ProductsController {
	// 필드 (멤버변수) 
	private ProductsModel pmodel; // 모델 멤버변수 캡슐화
	private ProductsView pview; // 뷰 멤버변수 캡슐화
	
	public ProductsController(Connection con, ProductsView view) {
		this.pmodel = new ProductsModel(con);
		this.pview = view;
	}
	
	// 저장된 값을 출력하는 메서드
	public void displayAllProducts() {
		List<ProductsDTO> products = pmodel.getProducts();
		// 모델 products 리스트에 저장되어있던 값을 컨트롤러 메서드에 넣어줌 (지역변수 여서 가능) 
		pview.displayProducts(products); 
		// 위에서 저장한 products 값을 출력하게끔 view 메서드 호출
	}

}
