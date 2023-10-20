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
	
	// �⺻�����ڸ� ����Ͽ� �ܺο��� �ν��Ͻ��� ���� �����ϴ°� ��������
	// �⺻������ ĸ��ȭ (���ȼ� ����)
	private BankUpdateModel() {
		
	}
	
	
	// �ν��Ͻ��� ���ٸ� �ν��Ͻ��� �����Ͽ� ��ȯ���� (�⺻�����ڰ� ���������� ĸ��ȭ�Ǿ��־)
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
			System.out.println("***�ݾ� ����***");
			st.setInt(2, bankDTO.getAccountId());
			System.out.println("***�����ϴ� ����ID***");
			int rowsAffected = st.executeUpdate();  // �̻��� �ȵ����� Oracle �� �ڵ尡 �־ (����� Ŀ�Ծ���)
			System.out.println("***����Ϸ�***");
			return rowsAffected > 0;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
}
