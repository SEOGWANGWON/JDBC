package com.kh.db.oracledb.CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectSample {

	public static void main(String[] args) {
		//selectAll();
		//selectOne();
		//selectIf();
		//selectTest();
		selectKhcafeJoin();
		//selectJoinTest();
		//selectIf();
		//selectTEST();
		

	}
	static void selectAll() { // public으로 하면 객체 생성해야하니 static으로 생략
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
	static void selectOne() {
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
			String selectQuery = "SELECT * FROM BANK WHERE ACCOUNT_ID in(?,?,?,?) ORDER BY ? ASC";
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
			selectState.setString(5, "ACCOUNT_ID");
			
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

							   // executeQuery(반환)
	static void selectKhcafeJoin() { // select 가 안되는이유를 모르겟당
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		Connection con;
		
			try {
				con = DriverManager.getConnection(url,user,password);
				// SELECT에 조건이 넣어지지 않는이유는 굳이 쓰지 않아도 돼서
				// select * 해놓고 while문에서 뽑아쓰면 되기 때문에 아예 select 조건을 읽는
				// 메서드나 버프드안에 있는 기능이 없는듯함. (궁금증 해결!)
				String SelectJoinQuery = "SELECT * FROM OLDCAFE C JOIN OLDMENU M ON C.CAFE_ID = M.CAFE_ID "
						+ " ORDER BY  ?  ASC";
				PreparedStatement SelectState = con.prepareStatement(SelectJoinQuery);
				
				
				//SelectState.setString(1, "C.CNAME");
				//SelectState.setString(2, "M.MENU_NAME");
				SelectState.setString(1, "C.CAFE_ID");
				
				
				ResultSet result = SelectState.executeQuery();
				System.out.println("저장 성공");
				
				while(result.next()) {
					System.out.println("들어왔따");
					String cafeName = result.getString("CNAME");
					System.out.println("첫번째꺼 출력");
					String cafeMenu = result.getString("MENU_NAME");
					
					System.out.println("CNAME : " + cafeName + ", MENU_NAME : " + cafeMenu);
				}
				SelectState.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				System.out.println("실패: " + e.getMessage());
				e.printStackTrace();
			}
			
		
	}
	static void selectJoinTest() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		Connection con;
		String sel = "C.CNAME, M.MENU_NAME";
			try {
				con = DriverManager.getConnection(url,user,password);
				
				String SelectJoinQuery = "SELECT " + sel + " FROM CAFES C JOIN MENU M ON"
						+ " C.CAFE_ID = M.CAFE_ID ORDER BY ? ASC";
				PreparedStatement SelectState = con.prepareStatement(SelectJoinQuery);
				
				
				SelectState.setString(1, "CAFE_ID");
				
				
				System.out.println("저장 성공");
				ResultSet result = SelectState.executeQuery();
				
				while(result.next()) {
					String cafeName = result.getString("CNAME");
					String cafeMenu = result.getString("MENU_NAME");
					System.out.println();
					
					System.out.println("CNAME : " + cafeName + ", MENU_NAME : " + cafeMenu);
				}
				SelectState.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("실패");
				e.printStackTrace();
			}
		
	}
	static void selectTEST() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		Connection con;
		String hi = "CNAME";
			try {
				con = DriverManager.getConnection(url,user,password);
				String SelectJoinQuery = "SELECT "+ hi +" FROM CAFES"
						+ " ORDER BY ? ASC";
				PreparedStatement SelectState = con.prepareStatement(SelectJoinQuery);
				
				
				SelectState.setString(1, " CNAME ");
				
				
				
				System.out.println("저장 성공");
				ResultSet result = SelectState.executeQuery();
				
				while(result.next()) {
					System.out.println("들어왔따");
					String cafeName = result.getString("CNAME");
					
					
					System.out.println("CNAME : " + cafeName);
				}
				SelectState.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("실패");
				e.printStackTrace();
			}
	}
}
