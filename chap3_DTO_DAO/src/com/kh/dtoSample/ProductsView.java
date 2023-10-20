package com.kh.dtoSample;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductsView {
	

	
	// ����Ʈ�� ����Ǿ� �ִ� ���� ������ display �޼���
	public void displayProducts(List<ProductsDTO> products) {
		// ��� ������ �ۼ��Ѱ͵� �ƴϰ� ����Ʈ �ν��Ͻ��� �����Ѱ͵� �ƴѵ� ����� �Ǵ�������
		// controller ���� displayAllProducts() �޼��� �ȿ��� ��� �� ���� ����
		// �� �޼���� ���� ���� �ʿ䰡���� �����ڷμ� ������ ����
		
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
