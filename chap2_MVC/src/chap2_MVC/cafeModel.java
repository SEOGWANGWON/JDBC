package chap2_MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cafeModel{
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "khcafe";
	String password = "khcafe";
	
	public void insertCafe(String name, String address, String phone_number, String operating_hours) {
		
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			String sql = "INSERT INTO cafes (cname, address, phone_number, operating_hours)"
					+ "VALUES(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, phone_number);
			ps.setString(4, operating_hours);
			
			ps.executeUpdate();
						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void UpdateMenu(String description, int menuId, int cafeId) { 
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "UPDATE MENU SET description = ? WHERE MENU_ID = ? AND CAFE_ID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, description);
			st.setInt(2, menuId);
			st.setInt(3, cafeId);
			
			
			st.executeUpdate();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void UpdateCafe(String operating_hours, int cafe_id) { 
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "UPDATE cafes SET operating_hours = ? WHERE cafe_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, operating_hours);
			ps.setInt(2, cafe_id);
			
			ps.executeUpdate();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void deleteCafe(int cafeid) {
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "DELETE FROM oldcafe WHERE cafe _id = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, cafeid);
			st.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteMenu(int menuId) {
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			String sql = "DELETE FROM oldMENU WHERE MENU_ID = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, menuId);
			st.executeUpdate();
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteOrder(int orderId) {
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			String sql = "DELETE FROM ORDERS WHERE ORDER_ID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,orderId);
			
			st.executeUpdate();
			
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
