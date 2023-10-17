package com.kh.db.oraclesample;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	public static void main(String[] args) {
		//selectBank();
		//selectKhcafe();
		selectIf();
		//selectTest();
		//insertBank();
		
		

	}
	static void selectBank() { // public���� �ϸ� ��ü �����ؾ��ϴ� static���� ����
		// 1. ����̹� ���� : Oracle JDBC ����̹� Ŭ���� �̸�
				String driver = "oracle.jdbc.driver.OracleDriver";
				// 2. ����Ŭ �� ��ǻ�� ���� : ������ ���̽� ���� ����
				//                             ����IP�ּ� /��Ʈ��ȣ
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				/*
				 jdbc���� oracle�� �����ϰڴ�. thin ����̹��� @localhost ip���� 1521��Ʈ�� SID�� xe�� �ּҿ�
				 
				 thin : Driver �� ���� �ٸ� ���� (oci)
				 �긦 ���༭ ����̹��� �ڿ� ���� �ʾƵ� �ȴ�/
				 */
				
				String user = "khbank";
				String password = "khbank";
				Connection con = null;
				try { 
					// ������ ����Ͽ� ���� ���� �Ǵ� �����ͺ��̽� �۾� ����
					con = DriverManager.getConnection(url, user, password);
					System.out.println("�����ͺ��̽� ���� ����!");
					
					// SELECT ����
					String selectQuery = "SELECT * FROM BANK";
					PreparedStatement selectState = con.prepareStatement(selectQuery);
					ResultSet result = selectState.executeQuery();
					// result.next() : result ��ü���� ���� ��(row)���� �̵� 
					// 				   �������� ������ true ��ȯ, �׷��� ������ false ��ȯ
					while(result.next()) {
					//				khbank�� �ִ� bank ���̺� ������տ��� account_id�� ������
						int accountID = result.getInt("account_id");
						//1. �Բ��غ��� : accountNumber
						String accountNumber = result.getString("account_number");
						System.out.print("Account_Number : " + accountNumber);
						String accountName = result.getString("account_name");
						double balance = result.getDouble("balance");
						//2. �Բ��غ��� : branchName
						String branchName = result.getString("branch_name");
						//3. ��¥ �ִ¹� : java.sql import Date lastTransactionDate ��������
						Date lastTransactionDate = result.getDate("LAST_TRANSACTION_DATE");
						System.out.println(", Branch_Name : " + branchName+", LAST_TRANSACTION_DATE : " + lastTransactionDate);
						System.out.println("ACCOUNT_ID : " + accountID +", ACCOUNT_NAME : " + accountName + ", BALANCE : " + balance);
						System.out.println();
					};
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	static void selectKhcafe() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("���� �Ǿ���.");
			
			String selectQuery = "SELECT * FROM MENU WHERE PRICE >= 5.0";
			
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			ResultSet result = selectState.executeQuery();
			
			
			while(result.next()) {
				int menuID = result.getInt("MENU_ID");
				int cafeID = result.getInt("CAFE_ID");
				String menuName = result.getString("MENU_NAME");
				double price = result.getDouble("PRICE");
				String description = result.getString("DESCRIPTION");
				
				System.out.println("MENU_ID : " + menuID + ", CAFE_ID : " + cafeID + ", MENU_NAME : " + menuName);
				System.out.println("PRICE : " + price + ", DESCRIPTION : " + description);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void selectIf() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "khbank";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("������ ���̽� ���� ����!");
			
			// WHERE �� �߰��Ͽ� ���� �߰�
			String selectQuery = "SELECT * FROM BANK WHERE ACCOUNT_ID = ? OR ACCOUNT_ID = ? OR ACCOUNT_ID = ? OR ACCOUNT_ID = ? ORDER BY BALANCE ASC";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			
			//String[] targetAN = {"1236777888", "5555666777","�̵���","������"};
			//���⿡ ���ϴ� ������ account_id ����
			int targetAID = 3; // ù��° �� (ACCOUNT_ID) ���� targetAID�� �ش��ϴ� ����
			
			/*
			selectState.setString(1, targetAN[0]);
			selectState.setString(2, targetAN[2]);
			*/
			
			// ���� ����
			selectState.setInt(1, 3); // ���⼭�� 1�� ��(�÷�)
			selectState.setInt(2, 5);
			selectState.setInt(3, 6);
			selectState.setInt(4, 7);
			/*
			 * ������ WHERE ACCOUNT_NAME = ? AND BALANCE = ? �϶� (?�� �������� �����ϸ� ���� ���δ�)
			 * selectState.setInt( "�����ε���", "���ǿ� �����ϴ� ã�� ��")
			 * 							1	,	"ȫ�浿"
			 * 							2	,	770000000
			 * 					(	"?������ �ε���", "?�� �� ��" )
			 */
			
			ResultSet result = selectState.executeQuery();
			
			//�� ���� ���� ( isBeforeFirst = ������ true ������ false )
			if(!result.isBeforeFirst()) {
				System.out.println("�����ϴ� �����Ͱ� �����ϴ�");
			}
			
			while(result.next()) {
				int a = result.getInt("account_id");
				String b = result.getString("account_number");
				String c = result.getString("Account_Name");
				double d = result.getDouble("Balance");
				String e = result.getString("Branch_Name");
				Date f = result.getDate("Last_Transaction_Date");
				
				System.out.println("Account_ID : " + a + ", account_number : "+ b + ", Account_Name : " + c);
				System.out.println("Balance : " +d +", Branch_Name : " + e + ", Last_Transaction_Date : " +f);
				
				
			} //else {
				//System.out.println("���ǿ� �ش��ϴ� �����Ͱ� �����ϴ�.");
			//}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void selectTest() {
		// String driver = "oracle:jdbc:driver:OracleDriver"; �����׽�Ʈ
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		/*
		 jdbc���� oracle�� �����ϰڴ�. thin ����̹��� @localhost ip���� 1521��Ʈ�� SID�� xe�� �ּҿ�
		 
		 thin : Driver �� ���� �ٸ� ���� (ocl)
		 �긦 ���༭ ����̹��� �ڿ� ���� �ʾƵ� �ȴ�/
		 */
		
		String user = "khbank";
		String password = "khbank";
		Connection con = null;
		try { 
			// ������ ����Ͽ� ���� ���� �Ǵ� �����ͺ��̽� �۾� ����
			con = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����!");
			
			// SELECT ����
			String selectQuery = "SELECT * FROM BANK";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			ResultSet result = selectState.executeQuery();
			// result.next() : result ��ü���� ���� ��(row)���� �̵� 
			// 				   �������� ������ true ��ȯ, �׷��� ������ false ��ȯ
			while(result.next()) {
			//				khbank�� �ִ� bank ���̺� ������տ��� account_id�� ������
			int accountID = result.getInt("account_id");
			//1. �Բ��غ��� : accountNumber
			String accountNumber = result.getString("account_number");
			System.out.print("Account_Number : " + accountNumber);
			String accountName = result.getString("account_name");
			double balance = result.getDouble("balance");
			//2. �Բ��غ��� : branchName
			String branchName = result.getString("branch_name");
			//3. ��¥ �ִ¹� : java.sql import Date lastTransactionDate ��������
			Date lastTransactionDate = result.getDate("LAST_TRANSACTION_DATE");
			System.out.println(", Branch_Name : " + branchName+", LAST_TRANSACTION_DATE : " + lastTransactionDate);
			System.out.println("ACCOUNT_ID : " + accountID +", ACCOUNT_NAME : " + accountName + ", BALANCE : " + balance);
			System.out.println();
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void insertBank() { // insert �� ���� �����ϴ°� �ƴ϶� �����ϴ� ���̶� ResultSet(���� �������̽�) �� �� �ʿ䰡����.
							   // executeQuery(��ȯ)
		
		//�Ⱥ��� ����
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "khbank";
		Connection con;
		
		try {
			con = DriverManager.getConnection(url,user,password);
			String insertQuery = "INSERT INTO BANK (account_id, account_number, account_name, balance, branch_name, last_transaction_date)"
								+"VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement insertState = con.prepareStatement(insertQuery);
			
			insertState.setInt(1, 15);
			insertState.setString(2, "2873846764");
			insertState.setString(3, "�����");
			insertState.setDouble(4, 899999999.99);
			insertState.setString(5, "�ο��� �����Ա�");
			insertState.setDate(6, Date.valueOf("1570-10-17"));
			
			int rowsupdate = insertState.executeUpdate();
			System.out.println(rowsupdate + " row �߰���");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		������� ���ذ� 
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "khbank";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,password);
			String insertQuery = "INSERT INTO BANK (account_id, account_number, account_name, balance, branch_name, last_transaction_date)"
								+"VALUES (?,?,?,?,?,?)";
			PreparedStatement insertState = con.prepareStatement(insertQuery);
			insertState.setInt(1, 13);
			insertState.setString(2, "203284619");
			insertState.setString(3, "�����");
			insertState.setDouble(4, 77777.77);
			insertState.setString(5, "�����");
			insertState.setDate(6, Date.valueOf("2023-10-16"));
			
			int rowsInsert = insertState.executeUpdate();
			System.out.println(rowsInsert + " row �߰���");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
	}
	
}
