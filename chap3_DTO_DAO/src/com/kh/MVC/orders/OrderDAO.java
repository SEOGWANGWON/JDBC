package com.kh.MVC.orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO { // 데이터베이스와 연결된 클래스
	private Connection connection;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "khcafe";
	private String password = "khcafe";
	
	
	public OrderDAO() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<OrderDTO> getAllOrders(){
		List<OrderDTO> orders = new ArrayList<>();
		
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM ORDERS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int orderId = rs.getInt("order_id");
				int cafeId = rs.getInt("cafe_id");
				int menuId = rs.getInt("menu_id");
				Date orderDate = rs.getDate("order_date");
				int quantity = rs.getInt("quantity"); 
				double totalPrice = rs.getDouble("total_price");
				
				OrderDTO orderDTO = new OrderDTO(orderId, cafeId, menuId, orderDate, quantity, totalPrice);
				orders.add(orderDTO);
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
		
		
	}
	
	
}
