package com.kh.db.oracledb.CRUD.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// cafes insert 작성해서 여러 카페 추가하기
public class insertExam {

	public static void main(String[] args) {
		insertBook();
		
		
	}
	// throws SQLException 를 써주지 않으면 트라이 캐치 문구가 뜸
	static void insertPSM(PreparedStatement ps, int cafe_id, String cname, String address, String phone_number, String operating_hours) throws SQLException{
		ps.setInt(1, cafe_id);
		ps.setString(2, cname);
		ps.setString(3, address);
		ps.setString(4, phone_number);
		ps.setString(5, operating_hours);
		int rowsUpdate = ps.executeUpdate();
		System.out.println(rowsUpdate + "행 삽입 완료!");
	}
	static void insertCafes() {
		String jdbcurl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		try {
			Connection con = DriverManager.getConnection(jdbcurl,user,password);
			
			String insertQuery = "INSERT INTO CAFES (cafe_id, cname, address, phone_number, operating_hours)"
					+"VALUES (?, ?, ?, ?, ?)";
			// cafe_id 값이 올바르게 들어가지 않는이유 시퀀스로 자동생성
			PreparedStatement psm = con.prepareStatement(insertQuery);
			
			insertPSM(psm, 1, "아몰랑카노", "안산시 상록구 중앙동 ", "031-237-5437", "월-금: 11:00-20:00");
			insertPSM(psm, 2, "아싸푸치노", "부산시 해운대구 해운대로 67 ", "051-842-5173", "월-금: 08:30-19:30");
			insertPSM(psm, 3, "안산밀크티", "안산시 상록구 성포동 ", "031-819-3287", "월-금: 08:30-19:30, 주말: 10:00-18:00");
			
			
			psm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void insertBook() {
		String jdbcurl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		try {
			Connection con = DriverManager.getConnection(jdbcurl,user,password);
			String insertQuery = "INSERT INTO BOOK (BOOK_ID, TITLE, AUTHOR, PUBLICATION_YEAR, ISBN, GENRE, DESCRIPTION, "
					+ "PRICE, PUBLICATION_DATE, CREATED_DATE, IS_AVAILABLE)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement psm = con.prepareStatement(insertQuery);
			
			insertBookPSM(psm, 21, "아저씨", "원빈", 2010, "978-8936813717", "영화원작소설", "전 특수요원의 가슴아픈 이야기", 29.99, "10/08/04", "2023-10-18" , "Y");
			
			
			
			
			psm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void insertBookPSM(PreparedStatement psm, int book_id , String title, String author, int publication_year, String isbn, String genre, String description, double price, String publication_date, String create_date, String is_available) throws SQLException{
		psm.setInt(1, book_id);
		psm.setString(2, title);
		psm.setString(3, author);
		psm.setInt(4, publication_year);
		psm.setString(5, isbn);
		psm.setString(6, genre);
		psm.setString(7, description);
		psm.setDouble(8, price);
		psm.setString(9, publication_date);
		psm.setString(10, create_date);
		psm.setString(11, is_available);
		int rows = psm.executeUpdate();
		System.out.println("책 제목 : " +title +"  "+ rows + "권 추가 완료!");
		
	}
}
