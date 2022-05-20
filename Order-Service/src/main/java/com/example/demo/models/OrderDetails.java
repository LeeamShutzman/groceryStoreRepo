package com.example.demo.models;

import javax.persistence.*;

@Entity
@IdClass(OrderDetailsPK.class)
@Table(name="ORDERDETAILS")
public class OrderDetails {

	@Id
	@JoinColumn(name="order_id", referencedColumnName="ORDERID")
	private long orderID;
	
	@Id
	@JoinColumn(name="product_id", referencedColumnName="PRODUCTID")
	private long productID;

	@Column(name="QUANTITY")
	private int quantity;

	
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetails(long orderID, long productID, int quantity) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
	}

	
	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + "]";
	}

}
