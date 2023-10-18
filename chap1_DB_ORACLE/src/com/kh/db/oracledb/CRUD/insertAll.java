package com.kh.db.oracledb.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertAll {

	public static void main(String[] args) {
		String jdbcurl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		Connection con;
		
		try {
			con = DriverManager.getConnection(jdbcurl,user,password);
			String insertSQL = "INSERT INTO products( product_id, product_name, category, price, stock_quantity)"
					+"VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement(insertSQL);
			
			// "Products: 테이블에 데이터 삽입
			insertProducts(ps, 8, "노트북", "전자제품", 899.99, 10);
			insertProducts(ps, 9, "냉장고", "가전제품", 799.99, 20);
			insertProducts(ps, 10, "마우스", "휴대기기", 79.99, 33);
			
			ps.close(); // 안써도 되는데 메모리 차지 방지를 위해 (잉여 메모리)
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static void insertProducts(PreparedStatement ps, int product_id, String product_name, String category, Double price, int stock_quantity) throws SQLException {
		
		ps.setInt(1, product_id);
		ps.setString(2, product_name);
		ps.setString(3, category);
		ps.setDouble(4, price);
		ps.setInt(5, stock_quantity);
		ps.executeUpdate();
		
		
	}
}
