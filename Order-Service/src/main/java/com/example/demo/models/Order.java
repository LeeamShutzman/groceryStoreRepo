package com.example.demo.models;

import java.sql.Date;


import javax.persistence.*;

@Entity
@Table(name="ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name="order_id", referencedColumnName="ORDERID")
	private long orderID;
	@Column(name="CUSTOMERID")
	private String customerID;
	@Column(name="ORDERDATE")
	private Date orderDate;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(long orderID, String customerID, Date orderDate) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.orderDate = orderDate;
	}

	
	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customerID=" + customerID + ", date=" + orderDate + "]";
	}

}
