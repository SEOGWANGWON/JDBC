package com.kh.dtoSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductsMain {

	public static void main(String[] args) {
		
		// controller ㅡ> model 로 들어갈 connection 내용 작성
		// connection(접속,연결) 하기위한 url, username, passowrd 
		// 변수명 명확하게 (정석) ex) jdbc_oracle_url = jdbc oracle 연결하기위한 url
		String jdbc_oracle_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(jdbc_oracle_url, username, password);
			
			ProductsView pview = new ProductsView(); 
			// 컨트롤러에 넣어주기위한 인스턴스 생성
			
			ProductsController controller = new ProductsController(con, pview);
			// controller와 연결된 model에 con 정보 전달, controller에 view 전달
			
			controller.displayAllProducts();
			// list Products 안에 저장된 값을 모두 출력
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
