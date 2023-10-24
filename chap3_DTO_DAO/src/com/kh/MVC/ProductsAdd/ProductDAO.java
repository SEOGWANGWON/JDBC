package com.kh.MVC.ProductsAdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private Connection connection; // �ٸ��κп��� �̾ ������ �־ �ʱ�ȭ ��
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "khcafe";
	private String password = "khcafe";
	
	public ProductDAO() {
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ProductDTO> getAllProducts(){
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUCTS"); // Result ���� �־��ټ����ִ�.
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				int product_id = result.getInt("PRODUCT_ID");
				String product_name = result.getString("PRODUCT_NAME");
				String category = result.getString("CATEGORY");
				double price = result.getDouble("PRICE");
				int stock_quantity = result.getInt("stock_quantity");
				
				ProductDTO product = new ProductDTO(product_id, product_name, category, price, stock_quantity);
				products.add(product);
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//e.getErrorCode();
		}
		
		return products;
	}
	

}
