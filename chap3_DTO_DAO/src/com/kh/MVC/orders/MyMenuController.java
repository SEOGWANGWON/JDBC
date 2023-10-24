package com.kh.MVC.orders;

import java.util.List;

public class MyMenuController {
	private MyMenuDAO dao;
	
	public MyMenuController() {
		this.dao = dao;
	}
	
	public double userTotalPrice(List<MyMenuDTO> menus) {
		double usertotalprice = 0;
		for(MyMenuDTO m : menus) {
			usertotalprice += m.getPrice();
		}
		return usertotalprice;
		
	}
	
	public double menuTotalPrice(List<MyMenuDTO> menus) {
		double totalprice = 0;
		for(MyMenuDTO m : menus) {
			totalprice += m.getPrice();
		}
		return totalprice;
		
	}
	
	public List<MyMenuDTO> getAllMenus(){
		return dao.getAllMenus();
	}
	
	
	
	
}
