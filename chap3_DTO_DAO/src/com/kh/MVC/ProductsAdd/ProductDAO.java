package com.kh.MVC.ProductsAdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private Connection connection; // 다른부분에서 이어서 써질수 있어서 초기화 함
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
			PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUCTS"); // Result 값을 넣어줄수도있다.
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
