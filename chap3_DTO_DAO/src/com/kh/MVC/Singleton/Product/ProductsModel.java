package com.kh.MVC.Singleton.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsModel {
	
	private static Connection connection;
	//Ŭ���� �ν��Ͻ��� �����ϴ� �̱��� ��ü ��
	private static ProductsModel productsModel = null;
	private static String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String ID = "khcafe";
	private static String PW = "khcafe";
	
	// �⺻ �����ڷκ��� �ܺο��� �ν��Ͻ��� ���� �����ϴ� ���� �����ϱ� ���� private���� ����
	private ProductsModel() {
		
	}
	
	// ProductsModel Ŭ������ �ν��Ͻ��� ��ȯ�ϴ� �޼���
	// ó�� ȣ��� �� �����ͺ��̽��� ������ �����ϰ� ���� ȣ�⿡���� �̹� ������ �ν��Ͻ��� ��ȯ
	// �̱��� ������ �����ϴ� ���
	public static ProductsModel getInstance() throws SQLException {
		if(productsModel == null) {
			productsModel = new ProductsModel();
			connection = DriverManager.getConnection(DB_URL,ID,PW);
		}
		return productsModel;
	}
	
	public boolean insertProduct(ProductsDTO product) {
		String sql = "INSERT INTO products (product_id, product_name, category, price, stock_quantity)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,  product.getProductId());
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getCategory());
			ps.setDouble(4, product.getPrice());
			ps.setInt(5, product.getStockQuantity());
			
			int rowsAffected = ps.executeUpdate();
			
			return rowsAffected > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}
}
