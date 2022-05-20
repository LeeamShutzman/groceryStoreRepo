package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /***************************************************************/
    //Constructors, Getters, and Setters
    public OrderService(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /***************************************************************/
    //Repository Method Calls

    //Add an Order
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    //View all Orders
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    //View Order by ID
    public Optional<Order> findByOrderID(long orderId) {
        return orderRepository.findById(orderId);
    }

    //View Orders by Customer
    public List<Order> findOrdersByCustomerID(String customerID) {
        return orderRepository.findByCustomerID(customerID);
    }

    //Delete an Order
    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

    //Update an Order
    public Order updateOrder(long orderID, Order order) {
        try {
            Order temp = orderRepository.findById(orderID).get();
            if (Objects.nonNull(order.getCustomerID()) && !"".equalsIgnoreCase(order.getCustomerID())) {
                temp.setCustomerID(order.getCustomerID());
            }
            if (Objects.nonNull(order.getOrderDate())) {
                temp.setDate(order.getOrderDate());
            }
            return orderRepository.save(temp);
        } catch (NoSuchElementException e) {
            System.out.println("No order with that ID was found");
            return null;
        }
    }

}

