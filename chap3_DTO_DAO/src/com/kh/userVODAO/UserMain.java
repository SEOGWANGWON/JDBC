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
				System.out.println("ã����� User ID �� �Է����ּ���.");
				System.out.println("�����ϰ� �����ôٸ� Ư������ ���� [e] �� �Է����ּ���.");
				String input = sc.nextLine();
				
				System.out.println("ã����� ID�� User Email �� �Է����ּ���.");
				String userEmail = sc.nextLine();
				
				//���࿡ e�� �Է��ߴٸ�
				// input.equalsIgnoreCase("e") �Ѵ� �Ȱ�����
				if("e".equalsIgnoreCase(input)) {
					System.out.println("�����ϰڽ��ϴ�."); // break�� ������ �����ϰڽ��ϴ� �� ��µ�
					break;
				}else if("e".equalsIgnoreCase(userEmail)) {
					System.out.println("�����ϰڽ��ϴ�."); // break�� ������ �����ϰڽ��ϴ� �� ��µ�
					break;
				}
				
				int userId = Integer.parseInt(input); // String input ����  int�� ��ȯ
				// select �� ����ϱ�
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
						System.out.println("���������ʴ� [User ID] �Դϴ�.");
						System.out.println();
					}else if (um.checkID(userId) && !um.checkEmail(userEmail)) {
						System.out.println("���������ʴ� [User Email] �Դϴ�.");
						System.out.println();
					}else {
					System.out.println("��ġ�ϴ� [User ID]�� [Email]�� ã���� �����ϴ�.");
					System.out.println();
				}
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	
 	public void insertRun() {
		// 1. DB ���� URL, USERNAME, PASSWORD
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
				
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
				UserDAO userDao = new UserDAO(connection);
					
				Scanner sc = new Scanner(System.in);
					
				System.out.println("USER ID : ");
				int userId = sc.nextInt();
					
				System.out.println("���̵� �Է� ���ּ��� : ");
				String username = sc.next();
					
				System.out.println("ȸ�������� ���Ǵ� �������ϴ�.");
				System.out.println("���������� �̸����� �ۼ����ּ���. : ");
			String email = sc.next();
					
			Date regDate = new Date(); // ���� ��¥�� �ð��� ����Ѵٴ� �ǹ�
			
					// DB�� ��� ���� �ν��Ͻ� ���� �� �ۼ����� ���� �����ϱ�
			UserVO newUser = new UserVO();
				newUser.setUserId(userId);
				newUser.setUsername(username);
				newUser.setEmail(email);
				newUser.setRegDate(regDate);
				
				sc.close();
				// �����Ͱ� ���������� ������ boolean�� ����ؼ� Ȯ��
				if(userDao.createUser(newUser)) { // true
					System.out.println("������ ���������� ��ϵǾ����ϴ�.");
				} else {
					System.out.println("���� ��Ͽ� �����Ͽ����ϴ�.");
				}
			
				// ���� ��
				
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
		
	public boolean checkID(int userId /*id �޴� �Ķ���� �ڸ�*/) throws SQLException{
		// 1. db����
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "khcafe";
		String dbPassWord = "khcafe";
		Connection cc = DriverManager.getConnection(jdbcURL, dbUserName, dbPassWord);
		
		// 2. SQL
		String sql = "SELECT * FROM USERINFO WHERE USER_ID = ?";
		// count(*) �������� ���ǿ� ��ġ�ϴ°��� 1�̻��� �����״�  int id = rs.getInt(1); �� ��������
		
		PreparedStatement st = cc.prepareStatement(sql);
		st.setInt(1, userId);
		
		// 3. IFȰ���ؼ� Result.next();
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("USER_ID");
			return id > 0; // 1�̻��̸� true
		}
		
		
		// return > 0 1�̻��̸� ��ġ
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
				con.setAutoCommit(true); // �ڵ� Ŀ���� ������ ä Ŀ���� �� �����ϴ�.
				String updateUrlP = "UPDATE BANK SET balance = balance + ? WHERE account_name = ?";
				PreparedStatement stp = con.prepareStatement(updateUrlP);
				
				String updateUrlM = "UPDATE BANK SET balance = balance + ? WHERE account_name = ?";
				PreparedStatement stm = con.prepareStatement(updateUrlM);
				
				
				System.out.println("�����࿡ ����Ͻ� ������ �Է����ּ���.");
				System.out.println("���α׷��� �����Ͻ÷��� [E] �� �Է����ּ���");
				String userName = sc.nextLine();
				
				System.out.println(userName + "���� ���忡 �Ա��� (Y) ����� (N)�� �Է����ּ���.");
				String checkIO = sc.nextLine();
				
				
				
				
				
				if(checkIO.equalsIgnoreCase("Y")) {
					System.out.println(userName +"���� ���忡 �Ա��Ͻ� �ݾ��� �Է����ּ���.");
					 double userIn = sc.nextDouble();
					 stp.setDouble(1, userIn);
					 stp.setString(2, userName);
					 stp.executeUpdate();
					 System.out.println("�Ա� �Ϸ�!");
					 break;
				}else if (checkIO.equalsIgnoreCase("N")) {
					System.out.println(userName + "���� ���忡�� ����Ͻ� �ݾ��� �Է����ּ���");
					double userOut = sc.nextDouble();
					 stm.setDouble(1, userOut);
					 stm.setString(2, userName);
					 stm.executeUpdate();
					 System.out.println("��� �Ϸ�!");
					 break;
				}if("e".equalsIgnoreCase(userName)) {
					System.out.println("�����ϰڽ��ϴ�."); // break�� ������ �����ϰڽ��ϴ� �� ��µ�
					break;
				}else if("e".equalsIgnoreCase(checkIO)) {
					System.out.println("�����ϰڽ��ϴ�."); // break�� ������ �����ϰڽ��ϴ� �� ��µ�
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
	 selectScanner ���� 
	 
	}else {
		// boolean ID or Email �ϳ��� ��ġ���� �ʴ� ��� ó��
		String allsql = "SELECT * FROM USERINFO";
		PreparedStatement allst = cc.prepareStatement(allsql);
		ResultSet allrs = allst.executeQuery();
		
	if(allrs.next()) { // �ȵƴ� ���� next()�� �ϳ��� �о� ���̴����̿��� ���� ���ʿ� ��ġ��������
	
	
			boolean idTrue = (userId == allrs.getInt("USER_ID"));
			boolean emailTrue = (userEmail == allrs.getString("EMAIL"));
		if(!idTrue ) {
		System.out.println("��ġ�ϴ� [User ID] �� ã���� �����ϴ�.");
		System.out.println();
	}else if (idTrue && !emailTrue) {
			System.out.println("��ġ���� �ʴ� [User Email] �Դϴ�.");
			System.out.println();
	}else {
			System.out.println("��ġ�ϴ� [User ID]�� [Email]�� ã���� �����ϴ�.");
			System.out.println();
			
		}		
	}
		allrs.close();
	}  
		
		st.close();
		*/
		
	
		


