package com.kh.userVODAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class UserDAO {
	private Connection connection;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}
	
	public boolean createUser(UserVO user) {
		String sql = "INSERT INTO USERINFO(user_id, username, email, reg_date)"
				+"VALUES(?, ?, ?, ?)";
		try //(PreparedStatement st= connection.prepareStatement(sql)) �ε� ��������
		{
			PreparedStatement st= connection.prepareStatement(sql);
			st.setInt(1, user.getUserId());
			st.setString(2, user.getUsername());
			st.setString(3, user.getEmail());
			st.setDate(4, new Date(user.getRegDate().getTime()));
			
			int rows = st.executeUpdate(); // ������ �������� ������Ʈ
			st.close();
			return rows > 0; //���� ������ 0���� Ŀ���Ƿ� true�� ��
			
			
		} catch (SQLException e) {
			
		
			e.printStackTrace();
			return false;
		}
	
		
	}
	public void seleteUser() { // ����Ʈ Ȱ�뵵 ����
		List<UserVO> userList = new ArrayList<>();
		String sql = "SELECT * FROM USERINFO";
		
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				System.out.println("USER ID : " + result.getInt("USER_ID"));
				System.out.println("USERNAME : " + result.getString("USERNAME"));
				System.out.println("EMAIL : " + result.getString("EMAIL"));
				System.out.println("���Գ�¥ : " + result.getDate("reg_Date"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public List<UserVO> getAllUsers() throws SQLException{ // ����Ʈ Ȱ�뵵 ����
		List<UserVO> users = new ArrayList<>();
		String sql = "SELECT * FROM USERINFO";
		
		// try - with - resources
		// �����ͺ��̽� ���ҽ��� �����ϰ� ����ϰ� �ڵ����� �ݾ��ֱ� ���ؼ� ���Ǵ� ��� (close)
		
		
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			ResultSet result = st.executeQuery();
			// selectOne-If		// selectAll-while
			
			while(result.next()) {
				UserVO user = new UserVO();
				user.setUserId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setEmail(result.getString("EMAIL"));
				user.setRegDate(result.getDate("REG_Date"));
				users.add(user);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}

}
