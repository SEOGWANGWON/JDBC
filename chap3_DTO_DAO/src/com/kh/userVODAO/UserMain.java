package com.kh.userVODAO;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMain {

	public static void main(String[] args) {
		UserMain um = new UserMain();
		// um.insertRun();
		//um.selectAll();
		//um.selectScanner();
		um.updateScannerbank();
	}
		
	public void selectScanner() {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
		UserMain um = new UserMain();
		
		try {
			Connection cc = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.println("찾고싶은 User ID 를 입력해주세요.");
				System.out.println("종료하고 싶으시다면 특수문자 제외 [e] 를 입력해주세요.");
				String input = sc.nextLine();
				
				System.out.println("찾고싶은 ID의 User Email 을 입력해주세요.");
				String userEmail = sc.nextLine();
				
				//만약에 e를 입력했다면
				// input.equalsIgnoreCase("e") 둘다 똑같은거
				if("e".equalsIgnoreCase(input)) {
					System.out.println("종료하겠습니다."); // break가 없으면 종료하겠습니다 만 출력됨
					break;
				}else if("e".equalsIgnoreCase(userEmail)) {
					System.out.println("종료하겠습니다."); // break가 없으면 종료하겠습니다 만 출력됨
					break;
				}
				
				int userId = Integer.parseInt(input); // String input 값을  int로 변환
				// select 문 출력하기
				String sql = "SELECT * FROM USERINFO WHERE user_id = ? AND EMAIL = ?";
				PreparedStatement st = cc.prepareStatement(sql);
				st.setInt(1, userId);
				st.setString(2, userEmail);
				ResultSet rs = st.executeQuery();
				
				// selectOne if
				if(rs.next()) {
					System.out.println("User ID : " + rs.getInt("USER_ID"));
					System.out.println("User Name : " + rs.getString("USERNAME"));
					System.out.println("Email : " + rs.getString("EMAIL"));
					System.out.println("Registration : " + rs.getDate("REG_DATE"));
				}else {
					if(!um.checkID(userId) && um.checkEmail(userEmail)) {
						System.out.println("존재하지않는 [User ID] 입니다.");
						System.out.println();
					}else if (um.checkID(userId) && !um.checkEmail(userEmail)) {
						System.out.println("존재하지않는 [User Email] 입니다.");
						System.out.println();
					}else {
					System.out.println("일치하는 [User ID]와 [Email]을 찾을수 없습니다.");
					System.out.println();
				}
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	
 	public void insertRun() {
		// 1. DB 연결 URL, USERNAME, PASSWORD
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
				
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
				UserDAO userDao = new UserDAO(connection);
					
				Scanner sc = new Scanner(System.in);
					
				System.out.println("USER ID : ");
				int userId = sc.nextInt();
					
				System.out.println("아이디를 입력 해주세요 : ");
				String username = sc.next();
					
				System.out.println("회원가입이 거의다 끝났습니다.");
				System.out.println("마지막으로 이메일을 작성해주세요. : ");
			String email = sc.next();
					
			Date regDate = new Date(); // 현재 날짜와 시간을 사용한다는 의미
			
					// DB에 담기 위해 인스턴스 생성 후 작성받은 정보 저장하기
			UserVO newUser = new UserVO();
				newUser.setUserId(userId);
				newUser.setUsername(username);
				newUser.setEmail(email);
				newUser.setRegDate(regDate);
				
				sc.close();
				// 데이터가 정상적으로 들어갔는지 boolean을 사용해서 확인
				if(userDao.createUser(newUser)) { // true
					System.out.println("유저가 성공적으로 등록되었습니다.");
				} else {
					System.out.println("유저 등록에 실패하였습니다.");
				}
			
				// 연결 닫
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
	}
	
	public void selectAll() {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
			// List<UserVO> users = getAllUsers();
			UserDAO userDao = new UserDAO(con);
			
			List<UserVO> users = userDao.getAllUsers();
			
			for(UserVO u : users) {
				System.out.println("User ID : " + u.getUserId());
				System.out.println("User Name : " + u.getUsername());
				System.out.println("User Email : " + u.getEmail());
				System.out.println("Registration Date : " + u.getRegDate());
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public boolean checkID(int userId /*id 받는 파라미터 자리*/) throws SQLException{
		// 1. db연결
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
		Connection cc = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
		
		// 2. SQL
		String sql = "SELECT * FROM USERINFO WHERE USER_ID = ?";
		// count(*) 형식으로 조건에 일치하는값이 1이상이 나올테니  int id = rs.getInt(1); 도 성립가능
		
		PreparedStatement st = cc.prepareStatement(sql);
		st.setInt(1, userId);
		
		// 3. IF활용해서 Result.next();
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("USER_ID");
			return id > 0; // 1이상이면 true
		}
		
		
		// return > 0 1이상이면 일치
		return false;
		
	}
	public boolean checkEmail(String userEmail) throws SQLException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
		Connection cc = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
	
		// String sql = "SELECT COUNT(*) FROM USERINFO WHERE EMAIL = ?";
		// int count = rs.getInt(1);
		
		String sql = "SELECT COUNT(*) FROM USERINFO WHERE EMAIL = ?";
		PreparedStatement st = cc.prepareStatement(sql);
		st.setString(1, userEmail);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		}
		
		
		return false;
	
	
	
	
	}
	
	public void updateScannerbank() {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khbank";
		String dbPassWord = "khbank";
		
		Connection con;
		Scanner sc = new Scanner(System.in);
		try {
			con = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
			while(true) {
				con.setAutoCommit(true); // 자동 커밋이 설정된 채 커밋할 수 없습니다.
				String updateUrlP = "UPDATE BANK SET balance = balance + ? WHERE account_name = ?";
				PreparedStatement stp = con.prepareStatement(updateUrlP);
				
				String updateUrlM = "UPDATE BANK SET balance = balance + ? WHERE account_name = ?";
				PreparedStatement stm = con.prepareStatement(updateUrlM);
				
				
				System.out.println("본은행에 등록하신 성함을 입력해주세요.");
				System.out.println("프로그램을 종료하시려면 [E] 를 입력해주세요");
				String userName = sc.nextLine();
				
				System.out.println(userName + "님의 통장에 입금은 (Y) 출금은 (N)을 입력해주세요.");
				String checkIO = sc.nextLine();
				
				
				
				
				
				if(checkIO.equalsIgnoreCase("Y")) {
					System.out.println(userName +"님의 통장에 입금하실 금액을 입력해주세요.");
					 double userIn = sc.nextDouble();
					 stp.setDouble(1, userIn);
					 stp.setString(2, userName);
					 stp.executeUpdate();
					 System.out.println("입금 완료!");
					 break;
				}else if (checkIO.equalsIgnoreCase("N")) {
					System.out.println(userName + "님의 통장에서 출금하실 금액을 입력해주세요");
					double userOut = sc.nextDouble();
					 stm.setDouble(1, userOut);
					 stm.setString(2, userName);
					 stm.executeUpdate();
					 System.out.println("출금 완료!");
					 break;
				}if("e".equalsIgnoreCase(userName)) {
					System.out.println("종료하겠습니다."); // break가 없으면 종료하겠습니다 만 출력됨
					break;
				}else if("e".equalsIgnoreCase(checkIO)) {
					System.out.println("종료하겠습니다."); // break가 없으면 종료하겠습니다 만 출력됨
					break;
				}
				
				con.commit();
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	}
	/*
	 selectScanner 에서 
	 
	}else {
		// boolean ID or Email 하나가 일치하지 않는 경우 처리
		String allsql = "SELECT * FROM USERINFO";
		PreparedStatement allst = cc.prepareStatement(allsql);
		ResultSet allrs = allst.executeQuery();
		
	if(allrs.next()) { // 안됐던 이유 next()로 하나씩 읽어 들이는중이여서 값이 애초에 일치하지않음
	
	
			boolean idTrue = (userId == allrs.getInt("USER_ID"));
			boolean emailTrue = (userEmail == allrs.getString("EMAIL"));
		if(!idTrue ) {
		System.out.println("일치하는 [User ID] 를 찾을수 없습니다.");
		System.out.println();
	}else if (idTrue && !emailTrue) {
			System.out.println("일치하지 않는 [User Email] 입니다.");
			System.out.println();
	}else {
			System.out.println("일치하는 [User ID]와 [Email]을 찾을수 없습니다.");
			System.out.println();
			
		}		
	}
		allrs.close();
	}  
		
		st.close();
		*/
		
	
		


