package com.kh.dtoSample;

import java.sql.Connection;
import java.util.List;

public class ProductsController {
	// �ʵ� (�������) 
	private ProductsModel pmodel; // �� ������� ĸ��ȭ
	private ProductsView pview; // �� ������� ĸ��ȭ
	
	public ProductsController(Connection con, ProductsView view) {
		this.pmodel = new ProductsModel(con);
		this.pview = view;
	}
	
	// ����� ���� ����ϴ� �޼���
	public void displayAllProducts() {
		List<ProductsDTO> products = pmodel.getProducts();
		// �� products ����Ʈ�� ����Ǿ��ִ� ���� ��Ʈ�ѷ� �޼��忡 �־��� (�������� ���� ����) 
		pview.displayProducts(products); 
		// ������ ������ products ���� ����ϰԲ� view �޼��� ȣ��
	}

}
