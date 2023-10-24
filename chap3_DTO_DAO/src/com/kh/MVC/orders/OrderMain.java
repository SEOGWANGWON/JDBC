package com.kh.MVC.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderMain {

	public static void main(String[] args) {
		OrderDAO dao = new OrderDAO();
		OrderController controller = new OrderController(dao);
		OrderView view = new OrderView();
		
		List<OrderDTO> orders = controller.getAllOrders();
		view.showAllOrders(orders);
		
		double dayTotalPrice = controller.dayTotalPrice(orders);
		view.showTodayTotalPrice(dayTotalPrice);
		
		
		
	}

}
