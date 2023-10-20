package com.kh.dtoSample;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsModel {
	
	private Connection connection;
	
	public ProductsModel(Connection connection) {
		this.connection = connection;		
	}
	
	
	public List<ProductsDTO> getProducts(){
		List<ProductsDTO> products = new ArrayList<>(); // Ÿ�Կ� ������� ���� �ޱ����� ����Ʈȭ
		String sql = "SELECT * FROM PRODUCTS ORDER BY PRICE ASC";	// SQL�� �����ؼ� ������ ������
		
		try {
			PreparedStatement st = connection.prepareStatement(sql); // SQL�� �������� �����ϰ� ����
			ResultSet result = st.executeQuery(); // SQL�� �������� ����Ǿ� �ִ� ���� ��ȯ (������)
			
			while(result.next()) { // SQL ���������� �����°��� �� ������ ����
				ProductsDTO product = new ProductsDTO();
				product.setProductId(result.getInt("PRODUCT_ID"));
				product.setProductName(result.getString("PRODUCT_NAME"));
				product.setCategory(result.getString("CATEGORY"));
				product.setPrice(result.getDouble("PRICE"));
				product.setStockQuantity(result.getInt("STOCK_QUANTITY"));
				
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
		
	}
	
	

}
