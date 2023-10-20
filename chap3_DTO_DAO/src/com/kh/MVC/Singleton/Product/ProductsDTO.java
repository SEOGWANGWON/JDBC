package com.kh.MVC.Singleton.Product;

//insert문
// 오늘 수업목적 : 싱글톤 패턴을 활용한 JDBC 를 MVC로 나타내기

// 데이터를 저장하고 내보내는 공간 (데이터 전송 객체) DTO 생성
public class ProductsDTO {
	// 멤버 변수 (필드)
	private int productId;
	private String ProductName;
	private String category;
	private double price;
	private int stockQuantity;
	
	// 파라미터를 넣지않아도 아래 생성자에 넣어진 파라미터를 쉽게 저장할수 있게
	// 만든 빈(깡통)생성자
	public ProductsDTO() {
		
	}
	
	// 생성자
	public ProductsDTO(int productId, String ProductName, String category, double price, int stockQuantity) {
		this.productId = productId;
		this.ProductName = ProductName;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
		}
	
	// 변경하고 싶거나 추가하고싶거나 제거하고싶은 멤버변수 겟셋 추가 

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
