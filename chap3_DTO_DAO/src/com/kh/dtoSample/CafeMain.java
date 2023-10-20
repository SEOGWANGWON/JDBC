package com.kh.dtoSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CafeMain {

	public static void main(String[] args) {
		// connection �� ������ ���� �ۼ�
		// url username password
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			// �ν��Ͻ� �����Ͽ� �� �ʱ�ȭ
			CafeView view = new CafeView();
			
			// �ν��Ͻ� �����Ͽ� ��Ʈ�ѷ� �ʱ�ȭ
			CafeController controller = new CafeController(con, view);
			
			// ī�� ���� ǥ���ϴ� �޼��� ��������
			controller.displayAllCafes();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
