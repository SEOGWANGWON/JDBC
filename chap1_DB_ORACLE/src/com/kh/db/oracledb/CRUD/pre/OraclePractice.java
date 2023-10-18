package com.kh.db.oracledb.CRUD.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OraclePractice {
/*
 1. 모든 카페 목록 가져오기
 2. 특정 카페의 메뉴 가져오기
 3. 새로운 카페 추가하기
 4. 특정 카페의 메뉴가격 변경
 5. 특정 카페의 정보 수정하기
 6. 특정 카페의 정보 삭제하기
 7. 특정 카페의 메뉴 수 가져오기
 8. 특정 가격 범위 내의 모든 메뉴 가져오기
 9. 특정 메뉴 정보 가져오기
 10. 모든 카페의 이름과 주소 가져오기
 11. 특정 카페의 운영 시간 변경 및 결과 확인
 12. 특정 카페의 전화번호 변경 및 결과 확인
  
 */
	public static void main(String[] args) {
		// 1번문제 selectAll();			--모든 카페 목록
		// 2번문제 selectWhereMenu(); 	//--CAFE_ID = 4 에 있는 카페 메뉴 // 여기서 WHERE LIKE '%안산%' 썻는데 왜안되냐 
		// 3번문제 insertCafes();			// 우리은하 카페 추가
		// 4번문제 updateMenu();			// 특정 MENU_ID 의 가격 변경
		// 5번문제 updateCafes();			// 특정 카페의 정보 수정
		// 6번문제 deleteCafes();			// 특정 카페의 정보 삭제
		// 7번문제 selectCount();			// 특정 카페의 메뉴 수 가져오기
		// 8번문제 selectAllMenus();		// 특정 가격 범위내의 모든 메뉴 가져오기
		// 9번문제 selectMenu();			// 특정 메뉴 정보 가져오기
		// 10번문제 selectCafesName();	// 모든 카페의 이름과 주소
	}
	static void selectAll() {
		// 1. 모든 카페 목록 가져오기
		
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
				System.out.println("카페이름 : " + cname + ", 주소 : " + address + ", 메뉴 : " + menu_name);
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
		// 3. 새로운 카페 추가하기
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "INSERT INTO CAFES (CAFE_ID, CNAME, ADDRESS, PHONE_NUMBER, OPERATING_HOURS)"
					+"VALUES (?, ?, ?, ?, ?)" ;
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			
			st1.setInt(1, 90);
			st1.setString(2, "알빠치노");
			st1.setString(3, "우리은하시 태평양구 지구동");
			st1.setString(4, "999-9999-9999");
			st1.setString(5, "웜홀로 항시 이용가능");
			
			int rows = st1.executeUpdate();
			System.out.println(rows + "개의 카페 추가");
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	static void updateMenu() {
		// 4. 특정 카페의 메뉴가격 변경
		
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
			System.out.println(rows + "개의 메뉴 가격 변경");
			
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	static void updateCafes() {
		// 5. 특정카페의 정보 수정하기
		
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
			System.out.println(rows + "개의 카페 정보 수정");
			
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	static void deleteCafes() {
		// 6. 특정 카페의 정보 삭제하기
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "DELETE FROM CAFES WHERE CAFE_ID = ?";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setInt(1, 80);
			
			
			int rows = st1.executeUpdate();
			System.out.println(rows + "개의 카페정보 삭제!");
			
			
			st1.close();
			con.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	static void selectCount() {
		// 7. 특정 카페의 메뉴 수 가져오기
		
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
		// 8. 특정 가격 범위 내의 모든 메뉴 가져오기
		
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
				System.out.println("CAFE_ID : " + cafe_id + ", 메뉴이름 : "
				+ cname + ", 가격 : " + address + ", 메뉴설명 : " + phone_number);
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
		// 9. 특정 메뉴 정보 가져오기
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM MENU M JOIN CAFES C ON M.CAFE_ID = C.CAFE_ID WHERE M.MENU_NAME = ? ORDER BY C.CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setString(1, "카푸치노");
			
			ResultSet result1 = st1.executeQuery();
			
			while(result1.next()) {
				String cname1 = result1.getString("CNAME");
				String address1 = result1.getString("ADDRESS");
				int cafe_id = result1.getInt("CAFE_ID");
				String cname = result1.getString("MENU_NAME");
				double address = result1.getDouble("PRICE");
				String phone_number = result1.getString("DESCRIPTION");
				System.out.println("CAFE_ID : " + cafe_id + ", 메뉴이름 : "
				+ cname + ", 가격 : " + address + ", 메뉴설명 : " + phone_number);
				System.out.println("카페이름 : " + cname1 + ", 카페주소 : " + address1);
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
		// 10. 모든 카페의 이름과 주소
		
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
				System.out.println("카페이름 : " + cname1 + ", 카페주소 : " + address1);
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
		// 11. 특정 카페의 운영 시간 변경 및 결과 확인
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			String selectA = "SELECT * FROM MENU M JOIN CAFES C ON M.CAFE_ID = C.CAFE_ID WHERE M.MENU_NAME = ? ORDER BY C.CAFE_ID ASC";
			PreparedStatement st1 = con.prepareStatement(selectA);
			
			st1.setString(1, "카푸치노");
			
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
