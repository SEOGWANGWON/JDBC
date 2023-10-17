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
	static void selectBank() { // public으로 하면 객체 생성해야하니 static으로 생략
		// 1. 드라이버 연결 : Oracle JDBC 드라이버 클래스 이름
				String driver = "oracle.jdbc.driver.OracleDriver";
				// 2. 오라클 내 컴퓨터 연결 : 데이터 베이스 연결 정보
				//                             나의IP주소 /포트번호
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				/*
				 jdbc에서 oracle을 연결하겠다. thin 드라이버로 @localhost ip에서 1521포트로 SID가 xe인 주소에
				 
				 thin : Driver 의 일종 다른 종류 (oci)
				 얘를 써줘서 드라이버를 뒤에 적지 않아도 된다/
				 */
				
				String user = "khbank";
				String password = "khbank";
				Connection con = null;
				try { 
					// 연결을 사용하여 쿼리 실행 또는 데이터베이스 작업 수행
					con = DriverManager.getConnection(url, user, password);
					System.out.println("데이터베이스 연결 성공!");
					
					// SELECT 쿼리
					String selectQuery = "SELECT * FROM BANK";
					PreparedStatement selectState = con.prepareStatement(selectQuery);
					ResultSet result = selectState.executeQuery();
					// result.next() : result 객체에서 다음 행(row)으로 이동 
					// 				   다음행이 있으면 true 반환, 그렇지 않으면 false 반환
					while(result.next()) {
					//				khbank에 있는 bank 테이블 결과집합에서 account_id를 가져옴
						int accountID = result.getInt("account_id");
						//1. 함께해보기 : accountNumber
						String accountNumber = result.getString("account_number");
						System.out.print("Account_Number : " + accountNumber);
						String accountName = result.getString("account_name");
						double balance = result.getDouble("balance");
						//2. 함께해보기 : branchName
						String branchName = result.getString("branch_name");
						//3. 날짜 넣는법 : java.sql import Date lastTransactionDate 가져오기
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
			System.out.println("연결 되었소.");
			
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
			System.out.println("데이터 베이스 연결 성공!");
			
			// WHERE 절 추가하여 조건 추가
			String selectQuery = "SELECT * FROM BANK WHERE ACCOUNT_ID = ? OR ACCOUNT_ID = ? OR ACCOUNT_ID = ? OR ACCOUNT_ID = ? ORDER BY BALANCE ASC";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			
			//String[] targetAN = {"1236777888", "5555666777","이동연","송은희"};
			//여기에 원하는 조건의 account_id 설정
			int targetAID = 3; // 첫번째 열 (ACCOUNT_ID) 에서 targetAID에 해당하는 유저
			
			/*
			selectState.setString(1, targetAN[0]);
			selectState.setString(2, targetAN[2]);
			*/
			
			// 조건 설정
			selectState.setInt(1, 3); // 여기서의 1은 열(컬럼)
			selectState.setInt(2, 5);
			selectState.setInt(3, 6);
			selectState.setInt(4, 7);
			/*
			 * 조건이 WHERE ACCOUNT_NAME = ? AND BALANCE = ? 일때 (?를 기준으로 생각하면 쉬워 보인다)
			 * selectState.setInt( "조건인덱스", "조건에 부합하는 찾을 값")
			 * 							1	,	"홍길동"
			 * 							2	,	770000000
			 * 					(	"?기준의 인덱스", "?에 들어갈 값" )
			 */
			
			ResultSet result = selectState.executeQuery();
			
			//값 존재 여부 ( isBeforeFirst = 있으면 true 없으면 false )
			if(!result.isBeforeFirst()) {
				System.out.println("존재하는 데이터가 없습니다");
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
				//System.out.println("조건에 해당하는 데이터가 없습니다.");
			//}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void selectTest() {
		// String driver = "oracle:jdbc:driver:OracleDriver"; 없이테스트
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		/*
		 jdbc에서 oracle을 연결하겠다. thin 드라이버로 @localhost ip에서 1521포트로 SID가 xe인 주소에
		 
		 thin : Driver 의 일종 다른 종류 (ocl)
		 얘를 써줘서 드라이버를 뒤에 적지 않아도 된다/
		 */
		
		String user = "khbank";
		String password = "khbank";
		Connection con = null;
		try { 
			// 연결을 사용하여 쿼리 실행 또는 데이터베이스 작업 수행
			con = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 연결 성공!");
			
			// SELECT 쿼리
			String selectQuery = "SELECT * FROM BANK";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			ResultSet result = selectState.executeQuery();
			// result.next() : result 객체에서 다음 행(row)으로 이동 
			// 				   다음행이 있으면 true 반환, 그렇지 않으면 false 반환
			while(result.next()) {
			//				khbank에 있는 bank 테이블 결과집합에서 account_id를 가져옴
			int accountID = result.getInt("account_id");
			//1. 함께해보기 : accountNumber
			String accountNumber = result.getString("account_number");
			System.out.print("Account_Number : " + accountNumber);
			String accountName = result.getString("account_name");
			double balance = result.getDouble("balance");
			//2. 함께해보기 : branchName
			String branchName = result.getString("branch_name");
			//3. 날짜 넣는법 : java.sql import Date lastTransactionDate 가져오기
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
	static void insertBank() { // insert 는 값을 추출하는게 아니라 삽입하는 것이라 ResultSet(저장 인터페이스) 을 쓸 필요가없다.
							   // executeQuery(반환)
		
		//안보고 쓴거
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
			insertState.setString(3, "김순신");
			insertState.setDouble(4, 899999999.99);
			insertState.setString(5, "부여성 왼쪽입구");
			insertState.setDate(6, Date.valueOf("1570-10-17"));
			
			int rowsupdate = insertState.executeUpdate();
			System.out.println(rowsupdate + " row 추가됨");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		강사님이 쳐준거 
		
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
			insertState.setString(3, "김옥순");
			insertState.setDouble(4, 77777.77);
			insertState.setString(5, "태평양");
			insertState.setDate(6, Date.valueOf("2023-10-16"));
			
			int rowsInsert = insertState.executeUpdate();
			System.out.println(rowsInsert + " row 추가됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
	}
	
}
