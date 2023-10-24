package com.kh.MVC.orders;

import java.util.List;

public class OrderController { // View Ŭ������ ����� �������� Controller
	private OrderDAO dao;
	
	public OrderController(OrderDAO dao) {
		this.dao = dao;
	}
	
	// �Ϸ� ���� �޼ҵ�
	public double dayTotalPrice(List<OrderDTO> orders) {
		double daytotalprice = 0;
		for(OrderDTO daytotal : orders) {
			daytotalprice += daytotal.getTotal_price();
		}
		return daytotalprice;
	}
	
	
	// ��� ��ǰ ����Ʈ
	public List<OrderDTO> getAllOrders(){
		return dao.getAllOrders();
	}

}
