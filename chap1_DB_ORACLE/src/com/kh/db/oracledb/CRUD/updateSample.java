package com.kh.db.oracledb.CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateSample {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "khbank";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String updateQuery = "UPDATE BANK SET last_transaction_date = ? WHERE account_number = ?";
			PreparedStatement updatePs = con.prepareStatement(updateQuery);
			updatePs.setDate(1, Date.valueOf("2023-10-18"));
			updatePs.setString(2, "1236777888");
			int rows = updatePs.executeUpdate();
			System.out.println(rows + " 업데이트 되었습니다.");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
