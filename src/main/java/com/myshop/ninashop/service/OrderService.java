package com.myshop.ninashop.service;

import com.myshop.ninashop.model.Order;
import com.myshop.ninashop.model.Product;
import com.myshop.ninashop.model.User;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {

    Order getCurrentOrder(User u);

    Order getAllUserOrders(User u);

    void addProductToOrder(User u, Product p, int quantity, HttpServletRequest request);
}
