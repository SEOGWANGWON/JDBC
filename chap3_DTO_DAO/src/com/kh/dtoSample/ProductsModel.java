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
		List<ProductsDTO> products = new ArrayList<>(); // 타입에 상관없이 값을 받기위한 리스트화
		String sql = "SELECT * FROM PRODUCTS ORDER BY PRICE ASC";	// SQL과 연동해서 실행할 쿼리문
		
		try {
			PreparedStatement st = connection.prepareStatement(sql); // SQL로 쿼리문을 저장하고 전달
			ResultSet result = st.executeQuery(); // SQL에 쿼리문에 저장되어 있는 값을 반환 (가져옴)
			
			while(result.next()) { // SQL 쿼리문에서 가져온값이 더 있으면 실행
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
