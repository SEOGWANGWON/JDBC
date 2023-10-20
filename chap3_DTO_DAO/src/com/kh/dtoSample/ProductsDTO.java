package com.kh.dtoSample;

// ���� �������� : �̱��� ������ Ȱ���� JDBC �� MVC�� ��Ÿ����
//select��

// �����͸� �����ϰ� �������� ���� (������ ���� ��ü) DTO ����
public class ProductsDTO {
	// ��� ���� (�ʵ�)
	private int productId;
	private String ProductName;
	private String category;
	private double price;
	private int stockQuantity;
	
	// �Ķ���͸� �����ʾƵ� �Ʒ� �����ڿ� �־��� �Ķ���͸� ���� �����Ҽ� �ְ�
	// ���� ��(����)������
	public ProductsDTO() {
		
	}
	
	// ������
	public ProductsDTO(int productId, String ProductName, String category, double price, int stockQuantity) {
		this.productId = productId;
		this.ProductName = ProductName;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
		}
	
	// �����ϰ� �Ͱų� �߰��ϰ��Ͱų� �����ϰ����� ������� �ټ� �߰� 

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