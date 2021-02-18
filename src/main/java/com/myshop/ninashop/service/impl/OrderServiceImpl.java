package com.myshop.ninashop.service.impl;

import com.myshop.ninashop.model.*;
import com.myshop.ninashop.reporitories.OrderRepository;
import com.myshop.ninashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static com.myshop.ninashop.model.OrderStatus.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order getCurrentOrder(User u) {
        return orderRepository.findByUserAndStatus(u.getId(), NOT_SENT.name());
    }

    @Override
    public Order getAllUserOrders(User u) {
        return orderRepository.findAllByUser(u.getId());
    }

    @Override
    public void addProductToOrder(User u, Product p, int quantity, HttpServletRequest request) {
        boolean productExists = false;


        Order order = getCurrentOrder(u);

        if (order == null){
            order = new Order(u);
            order.setStatus(NOT_SENT);
        }
        Set<OrderProduct> productsInOrder = order.getProductsInOrder();

        for (OrderProduct productInOrder : productsInOrder){
            if (productInOrder.getProduct() == p){
                productInOrder.setQuantity(productInOrder.getQuantity() + quantity);
                productExists = true;
                break;
            }
        }

        if (!productExists){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(p);
            orderProduct.setQuantity(quantity);
            productsInOrder.add(orderProduct);
        }

        order.setProductsInOrder(productsInOrder);
        orderRepository.save(order);
        request.getSession().setAttribute("currentOrder", order);
    }
}
