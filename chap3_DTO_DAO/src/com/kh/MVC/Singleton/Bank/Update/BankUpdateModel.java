package com.kh.MVC.Singleton.Bank.Update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankUpdateModel {
	
	private static Connection connection;
	private static BankUpdateModel bankupdateModel = null;
	private static String Bank_URl = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String username = "khbank";
	private static String password = "khbank";
	
	// 기본생성자를 사용하여 외부에서 인스턴스를 직접 생성하는걸 막기위한
	// 기본생성자 캡슐화 (보안성 증가)
	private BankUpdateModel() {
		
	}
	
	
	// 인스턴스가 없다면 인스턴스를 생성하여 반환해줌 (기본생성자가 보안을위해 캡슐화되어있어서)
	public static BankUpdateModel getInstance() throws SQLException{
		if(bankupdateModel == null) {
			bankupdateModel = new BankUpdateModel();
			connection = DriverManager.getConnection(Bank_URl,username,password);
		}
		return bankupdateModel;
	}
	
	public boolean UpdateBank(BankUpdateDTO bankDTO) {
		String sql = "UPDATE BANK SET BALANCE = ? WHERE ACCOUNT_ID = ?";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setDouble(1, bankDTO.getBalance());
			System.out.println("***금액 변경***");
			st.setInt(2, bankDTO.getAccountId());
			System.out.println("***변경하는 통장ID***");
			int rowsAffected = st.executeUpdate();  // 이새끼 안된이유 Oracle 에 코드가 있어서 (지운거 커밋안함)
			System.out.println("***저장완료***");
			return rowsAffected > 0;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
}
