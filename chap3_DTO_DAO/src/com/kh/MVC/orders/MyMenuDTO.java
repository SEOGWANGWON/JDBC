package com.kh.MVC.orders;

import java.sql.Connection;

public class MyMenuDTO {
	private int menu_id;
	private int cafe_id;
	private String menu_name;
	private double price;
	private String description;
	
	
	
	public MyMenuDTO(int menu_id,int cafe_id, String menu_name, double price, String description) {
		this.menu_id = menu_id;
		this.cafe_id = cafe_id;
		this.menu_name = menu_name;
		this.price = price;
		this.description = description;
	}
	
	

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
