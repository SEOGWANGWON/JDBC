package com.kh.MVC.jdbcBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Bank {
	public static void main(String[] args) {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khbank";
		String dbPassWord = "khbank";
		
		
		try {
			Connection con = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
			Scanner sc = new Scanner(System.in);
			con.setAutoCommit(false);
			String updatePlusSql = "UPDATE BANK SET BALANCE = BALANCE + ? WHERE ACCOUNT_ID = ?";
			PreparedStatement stp = con.prepareStatement(updatePlusSql);
			
			String updateMinusSql = "UPDATE BANK SET BALANCE = BALANCE - ? WHERE ACCOUNT_ID = ?";
			PreparedStatement stm = con.prepareStatement(updateMinusSql);
			
			System.out.println("전송할 ID를 입력하세요 : ");
			int fromAccountId = sc.nextInt();
			
			System.out.println("전송받을 ID를 입력해주세요. : ");
			int toAccountId = sc.nextInt();
			
			System.out.println("전송할 금액을 입력하세요. : ");
			double amount = sc.nextDouble();
			stm.setDouble(1, amount);
			stp.setDouble(1, amount);
			stp.setInt(2, toAccountId);
			stm.setInt(2, fromAccountId);
			stm.executeUpdate();
			stp.executeUpdate();
			con.commit();
			con.close();
			sc.close();
			
			System.out.println("전송 완료!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
