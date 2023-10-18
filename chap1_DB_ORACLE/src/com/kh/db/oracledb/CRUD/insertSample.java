package com.kh.db.oracledb.CRUD; // CREATE, RESULT, UPDATE, DELETE

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertSample {

	public static void main(String[] args) {
		insertKhbank();
		
	}
	// insert �� ���� �����ϴ°� �ƴ϶� �����ϴ� ���̶� 
	// ResultSet(���� �������̽�) �� �� �ʿ䰡����.
	static void insertKhcafe() { 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		Connection con;
		
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void insertKhbankPSM(PreparedStatement ps, int ACCOUNT_ID, String ACCOUNT_NUMBER, String ACCOUNT_NAME, double BALANCE, String BRANCH_NAME, String LAST_TRANSACTION_DATE) throws SQLException{
		ps.setInt(1, ACCOUNT_ID);
		ps.setString(2, ACCOUNT_NUMBER);
		ps.setString(3, ACCOUNT_NAME);
		ps.setDouble(4, BALANCE);
		ps.setString(5, BRANCH_NAME);
		ps.setDate(6, Date.valueOf(LAST_TRANSACTION_DATE));
		int rows = ps.executeUpdate();
		System.out.println(ACCOUNT_NAME +"���� ������ ������Ʈ �ƽ��ϴ�.");
	}
	static void insertKhbank() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "khbank";
		Connection con;
		
		try {
			con = DriverManager.getConnection(url,user,password);
			String inKhbank = "INSERT INTO BANK (ACCOUNT_ID, ACCOUNT_NUMBER, ACCOUNT_NAME, BALANCE, BRANCH_NAME, LAST_TRANSACTION_DATE)"
					+"VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement Ps = con.prepareStatement(inKhbank);
			
			insertKhbankPSM(Ps, 41, "96000627801018", "������", 99999999.99, "��������", "2030-10-10");
			
			Ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
