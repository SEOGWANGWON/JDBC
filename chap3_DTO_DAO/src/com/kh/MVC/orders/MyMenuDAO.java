package com.kh.MVC.orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyMenuDAO {
	private Connection connection;
	private Connection connection2;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "khcafe";
	private String password = "khcafe";
	private String userbank = "khbank";
	private String bankpassword = "khbank";
	
	public MyMenuDAO() {
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<MyMenuDTO> getAllMenus(){
		List<MyMenuDTO> menus = new ArrayList<>();
		
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM OLDMENU");
			ResultSet rs = st.executeQuery(); 
			
			while(rs.next()) {
				int menuID = rs.getInt("MENU_ID");
				int cafeID = rs.getInt("CAFE_ID");
				String menuName = rs.getString("MENU_NAME");
				double price = rs.getDouble("PRICE");
				String description = rs.getString("description");
				
				MyMenuDTO mydto = new MyMenuDTO(menuID, cafeID, menuName, price, description);
				menus.add(mydto);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menus;
		
	}
	
	public void choiceMenus() {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			
			Scanner sc = new Scanner(System.in);
			List<MyMenuDTO> userChoice = new ArrayList<>();
			double totalPrice = 0;
			
			
			while(true) {
				PreparedStatement st = connection.prepareStatement("SELECT * FROM OLDMENU WHERE MENU_ID = ?");
				
				System.out.println("주문하실 메뉴를 담아주세요.");
				int choice = sc.nextInt();
				
				
				if(choice == 99) {
					System.out.println("주문을 종료합니다.");
					System.out.println();
					
					for(MyMenuDTO my : userChoice) {
						System.out.println("메뉴 번호 : " + my.getMenu_id());
						System.out.println("메뉴 이름 : " + my.getMenu_name());
						System.out.println("메뉴 가격 : " + my.getPrice());
						System.out.println("메뉴 설명 : " + my.getDescription());
						totalPrice += my.getPrice();
						System.out.println("===========================");
					}
					System.out.println("총 가격 : " + totalPrice);
					break;
				}
				
				
				st.setInt(1, (choice));
				System.out.println("주문이 추가되었습니다.");
				System.out.println("주문을 종료하시려면 [99]번을 입력하세요.");
				System.out.println();
				
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					int menuID = rs.getInt("MENU_ID");
					int cafeID = rs.getInt("CAFE_ID");
					String menuName = rs.getString("MENU_NAME");
					double price = rs.getDouble("PRICE");
					String description = rs.getString("description");
					
					MyMenuDTO mydto = new MyMenuDTO(menuID, cafeID, menuName, price, description);
					userChoice.add(mydto);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void userBank() {
		
		try {
			connection = DriverManager.getConnection(url, userbank, bankpassword);
			connection.setAutoCommit(false);
			Scanner sc = new Scanner(System.in);
			double bankBalance = 0;
			PreparedStatement st = connection.prepareStatement("SELECT * FROM BANK WHERE ACCOUNT_NUMBER = ?");
			System.out.println("계좌번호를 입력해주세요.");
			String bankNumber = sc.nextLine();
			
			st.setString(1, bankNumber);
			
			ResultSet result = st.executeQuery();
			if(result.next()) {
				int accountID = result.getInt("account_id");
				String accountNumber = result.getString("account_number");
				String accountName = result.getString("account_name");
				double balance = result.getDouble("balance");
				String branchName = result.getString("branch_name");
				Date lastTransactionDate = result.getDate("LAST_TRANSACTION_DATE");
				System.out.println("계좌번호 : " + accountName + ", 성함 : " + accountName + ", 계좌잔액 : " + balance);
				
				bankBalance = balance;
				
			}
			System.out.println("계좌 잔액 : " + bankBalance);
			connection2 = DriverManager.getConnection(url, username, password);
			
			
			List<MyMenuDTO> userChoice = new ArrayList<>();
			double totalPrice = 0;
			
			
			while(true) {
				PreparedStatement stm = connection2.prepareStatement("SELECT * FROM OLDMENU WHERE MENU_ID = ?");
				System.out.println("주문하실 메뉴를 담아주세요.");
				int choice = sc.nextInt();
				
				
				if(choice == 99) {
					System.out.println("주문을 종료합니다.");
					System.out.println();
					
					for(MyMenuDTO my : userChoice) {
						System.out.println("메뉴 번호 : " + my.getMenu_id());
						System.out.println("메뉴 이름 : " + my.getMenu_name());
						System.out.println("메뉴 가격 : " + my.getPrice());
						System.out.println("메뉴 설명 : " + my.getDescription());
						totalPrice += my.getPrice();
						System.out.println("===========================");
					}
					System.out.println("총 가격 : " + totalPrice);
					break;
				}
				
				
				stm.setInt(1, choice);
				System.out.println("주문이 추가되었습니다.");
				System.out.println("주문을 종료하시려면 [99]번을 입력하세요.");
				System.out.println();
				
				ResultSet rs = stm.executeQuery();
				while(rs.next()) {
					int menuID = rs.getInt("MENU_ID");
					int cafeID = rs.getInt("CAFE_ID");
					String menuName = rs.getString("MENU_NAME");
					double price = rs.getDouble("PRICE");
					String description = rs.getString("description");
					
					MyMenuDTO mydto = new MyMenuDTO(menuID, cafeID, menuName, price, description);
					userChoice.add(mydto);
				}
			}
			PreparedStatement stb = connection.prepareStatement("UPDATE BANK SET BALANCE = BALANCE - ? WHERE ACCOUNT_NUMBER = ?");
			
			stb.setDouble(1, totalPrice);
			stb.setString(2, bankNumber);
			if(bankBalance < totalPrice) {
				System.out.println("잔액이 부족합니다.");
			}else {
				stb.executeUpdate();
				connection.commit();
				System.out.println("결제가 완료되었습니다.");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/*
	public List<MyMenuDTO> choiceMenus(int usermenu){
		List<MyMenuDTO> menus = new ArrayList<>();
		
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM OLDMENU WHERE MENU_ID = ?");
			
			st.setInt(1, usermenu);
			ResultSet rs = st.executeQuery(); 
			
			while(rs.next()) {
				int menuID = rs.getInt("MENU_ID");
				int cafeID = rs.getInt("CAFE_ID");
				String menuName = rs.getString("MENU_NAME");
				double price = rs.getDouble("PRICE");
				String description = rs.getString("description");
				
				MyMenuDTO mydto = new MyMenuDTO(menuID, cafeID, menuName, price, description);
				menus.add(mydto);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menus;
	} 
	*/	// 폐기
	
}
