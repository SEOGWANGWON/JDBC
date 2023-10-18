package com.kh.db.oracledb.CRUD.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OraclePractice {
/*
 1. ��� ī�� ��� ��������
 2. Ư�� ī���� �޴� ��������
 3. ���ο� ī�� �߰��ϱ�
 4. Ư�� ī���� �޴����� ����
 5. Ư�� ī���� ���� �����ϱ�
 6. Ư�� ī���� ���� �����ϱ�
 7. Ư�� ī���� �޴� �� ��������
 8. Ư�� ���� ���� ���� ��� �޴� ��������
 9. Ư�� �޴� ���� ��������
 10. ��� ī���� �̸��� �ּ� ��������
 11. Ư�� ī���� � �ð� ���� �� ��� Ȯ��
 12. Ư�� ī���� ��ȭ��ȣ ���� �� ��� Ȯ��
  
 */
	public static void main(String[] args) {
		// 1������ selectAll();			--��� ī�� ���
		// 2������ selectWhereMenu(); 	//--CAFE_ID = 4 �� �ִ� ī�� �޴� // ���⼭ WHERE LIKE '%�Ȼ�%' ���µ� �־ȵǳ� 
		// 3������ insertCafes();			// �츮���� ī�� �߰�
		// 4������ updateMenu();			// Ư�� MENU_ID �� ���� ����
		// 5������ updateCafes();			// Ư�� ī���� ���� ����
		// 6������ deleteCafes();			// Ư�� ī���� ���� ����
		// 7������ selectCount();			// Ư�� ī���� �޴� �� ��������
		// 8������ selectAllMenus();		// Ư�� ���� �������� ��� �޴� ��������
		// 9������ selectMenu();			// Ư�� �޴� ���� ��������
		// 10������ selectCafesName();	// ��� ī���� �̸��� �ּ�
	}
	static void selectAll() {
		// 1. ��� ī�� ��� ��������
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM CAFES ORDER BY CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				int cafe_id = result1.getInt("CAFE_ID");
				String cname = result1.getString("CNAME");
				String address = result1.getString("ADDRESS");
				String phone_number = result1.getString("PHONE_NUMBER");
				String operating_hours = result1.getString("OPERATING_HOURS");
				System.out.println("CAFE_ID : " + cafe_id + ", CNAME : "
				+ cname + ", ADDRESS : " + address);
				System.out.println(", PHONE_NUMBER : " + phone_number + ", OPERATING_HOURS : " + operating_hours);
				System.out.println();
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	static void selectWhereMenu() {

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM CAFES C JOIN MENU M ON C.CAFE_ID = M.CAFE_ID WHERE C.CAFE_ID = ? ORDER BY C.CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			
			st1.setInt(1, 4);
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				String cname = result1.getString("CNAME");
				String address = result1.getString("ADDRESS");
				String menu_name = result1.getString("MENU_NAME");
				System.out.println("ī���̸� : " + cname + ", �ּ� : " + address + ", �޴� : " + menu_name);
				System.out.println();
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void insertCafes() {
		// 3. ���ο� ī�� �߰��ϱ�
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "INSERT INTO CAFES (CAFE_ID, CNAME, ADDRESS, PHONE_NUMBER, OPERATING_HOURS)"
					+"VALUES (?, ?, ?, ?, ?)" ;
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			
			st1.setInt(1, 90);
			st1.setString(2, "�˺�ġ��");
			st1.setString(3, "�츮���Ͻ� ����籸 ������");
			st1.setString(4, "999-9999-9999");
			st1.setString(5, "��Ȧ�� �׽� �̿밡��");
			
			int rows = st1.executeUpdate();
			System.out.println(rows + "���� ī�� �߰�");
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	static void updateMenu() {
		// 4. Ư�� ī���� �޴����� ����
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "UPDATE MENU SET PRICE = ? WHERE MENU_ID = ?";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setDouble(1, 2.5);
			st1.setInt(2, 2);
			
			int rows = st1.executeUpdate();
			System.out.println(rows + "���� �޴� ���� ����");
			
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	static void updateCafes() {
		// 5. Ư��ī���� ���� �����ϱ�
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "UPDATE CAFES SET CNAME = ? WHERE CAFE_ID = ?";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			
			st1.setString(1, "MEGA COFFEE");
			st1.setInt(2, 78);
			
			int rows = st1.executeUpdate();
			System.out.println(rows + "���� ī�� ���� ����");
			
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	static void deleteCafes() {
		// 6. Ư�� ī���� ���� �����ϱ�
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "DELETE FROM CAFES WHERE CAFE_ID = ?";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setInt(1, 80);
			
			
			int rows = st1.executeUpdate();
			System.out.println(rows + "���� ī������ ����!");
			
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	static void selectCount() {
		// 7. Ư�� ī���� �޴� �� ��������
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT C.CNAME, COUNT(M.MENU_ID) FROM CAFES C "
					+ "JOIN MENU M ON C.CAFE_ID = M.CAFE_ID WHERE C.CAFE_ID = ? GROUP BY C.CNAME ORDER BY C.CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setInt(1, 2);
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				String cname = result1.getString("CNAME");
				int countMenu = result1.getInt("COUNT(M.MENU_ID)");
				
				System.out.println("CNAME : " + cname + ", COUNT(M.MENU_ID) : " + countMenu);
				
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	static void selectAllMenus() {
		// 8. Ư�� ���� ���� ���� ��� �޴� ��������
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM MENU WHERE PRICE BETWEEN ? AND ?  ORDER BY CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setDouble(1, 5);
			st1.setDouble(2, 30);
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				int cafe_id = result1.getInt("CAFE_ID");
				String cname = result1.getString("MENU_NAME");
				double address = result1.getDouble("PRICE");
				String phone_number = result1.getString("DESCRIPTION");
				System.out.println("CAFE_ID : " + cafe_id + ", �޴��̸� : "
				+ cname + ", ���� : " + address + ", �޴����� : " + phone_number);
				System.out.println();
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void selectMenu() {
		// 9. Ư�� �޴� ���� ��������
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM MENU M JOIN CAFES C ON M.CAFE_ID = C.CAFE_ID WHERE M.MENU_NAME = ? ORDER BY C.CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setString(1, "īǪġ��");
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				String cname1 = result1.getString("CNAME");
				String address1 = result1.getString("ADDRESS");
				int cafe_id = result1.getInt("CAFE_ID");
				String cname = result1.getString("MENU_NAME");
				double address = result1.getDouble("PRICE");
				String phone_number = result1.getString("DESCRIPTION");
				System.out.println("CAFE_ID : " + cafe_id + ", �޴��̸� : "
				+ cname + ", ���� : " + address + ", �޴����� : " + phone_number);
				System.out.println("ī���̸� : " + cname1 + ", ī���ּ� : " + address1);
				System.out.println();
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	static void selectCafesName() {
		// 10. ��� ī���� �̸��� �ּ�
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM CAFES ORDER BY ? ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setString(1, "CAFE_ID");
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				String cname1 = result1.getString("CNAME");
				String address1 = result1.getString("ADDRESS");
				System.out.println("ī���̸� : " + cname1 + ", ī���ּ� : " + address1);
				System.out.println();
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void selectOH() {
		// 11. Ư�� ī���� � �ð� ���� �� ��� Ȯ��
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM MENU M JOIN CAFES C ON M.CAFE_ID = C.CAFE_ID WHERE M.MENU_NAME = ? ORDER BY C.CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setString(1, "īǪġ��");
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				
				System.out.println();
			}
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
