package com.kh.MVC.orders;

import java.util.List;

public class OrderController { // View 클래스의 출력을 돕기위한 Controller
	private OrderDAO dao;
	
	public OrderController(OrderDAO dao) {
		this.dao = dao;
	}
	
	// 하루 매출 메소드
	public double dayTotalPrice(List<OrderDTO> orders) {
		double daytotalprice = 0;
		for(OrderDTO daytotal : orders) {
			daytotalprice += daytotal.getTotal_price();
		}
		return daytotalprice;
	}
	
	
	// 모든 제품 리스트
	public List<OrderDTO> getAllOrders(){
		return dao.getAllOrders();
	}

}
