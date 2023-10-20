package com.kh.dtoSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductsMain {

	public static void main(String[] args) {
		
		// controller ��> model �� �� connection ���� �ۼ�
		// connection(����,����) �ϱ����� url, username, passowrd 
		// ������ ��Ȯ�ϰ� (����) ex) jdbc_oracle_url = jdbc oracle �����ϱ����� url
		String jdbc_oracle_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbc_oracle_url, username, password);
			
			ProductsView pview = new ProductsView(); 
			// ��Ʈ�ѷ��� �־��ֱ����� �ν��Ͻ� ����
			
			ProductsController controller = new ProductsController(con, pview);
			// controller�� ����� model�� con ���� ����, controller�� view ����
			
			controller.displayAllProducts();
			// list Products �ȿ� ����� ���� ��� ���
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
