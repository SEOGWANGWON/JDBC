package com.kh.db.oracledb.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteSample {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "khbank";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String deleteQuery = "DELETE FROM BANK WHERE account_id = ?";
			PreparedStatement deletePs = con.prepareStatement(deleteQuery);
			deletePs.setDouble(1, 9);
			
			int rows = deletePs.executeUpdate();
			System.out.println(rows +"���� " + "���� ������ ����Ʈ �Ǿ����ϴ�.");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
