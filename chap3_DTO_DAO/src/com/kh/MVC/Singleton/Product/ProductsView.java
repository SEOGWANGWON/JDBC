package com.kh.MVC.Singleton.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductsView {
	

	
	public void addProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�� ��ǰ ������ �Է��ϼ���.");
		
		System.out.println("��ǰ ID : ");
		int productId = sc.nextInt();
		
		System.out.println("��ǰ �̸� : ");
		String productName = sc.next();
		
		System.out.println("ī�װ� : ");
		String category = sc.next();
		
		System.out.println("���� : ");
		double price = sc.nextDouble();
		
		System.out.println("������ : ");
		int stockQuantity = sc.nextInt();
		
		// Scanner�� ���� ��ǰ������ ���� �߰��ϱ�
		
		ProductsDTO newProduct = new ProductsDTO(productId, productName, category, price, stockQuantity);
		ProductsModel productsModel;
		boolean success = false;
		
		try {
			productsModel = ProductsModel.getInstance();
			success = productsModel.insertProduct(newProduct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(success) {
			System.out.println("��ǰ�� ���������� �߰��Ǿ����ϴ�.");
		} else {
			System.out.println("��ǰ �߰� �� ������ �߻��߽��ϴ�.");
		}
		
		sc.close();
		
	}
}
