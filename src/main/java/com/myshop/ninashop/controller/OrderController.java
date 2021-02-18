package com.myshop.ninashop.controller;

import com.myshop.ninashop.model.*;
import com.myshop.ninashop.reporitories.OrderRepository;
import com.myshop.ninashop.reporitories.ProductRepository;
import com.myshop.ninashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/orders/add/{id}")
    public String addProduct(@PathVariable("id") Long productID, HttpServletRequest request) {
        if (request.getSession().getAttribute("currentUser") == null){
            return "redirect:/users/login?required";
        }
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        Product product = productRepository.getOne(productID);
        orderService.addProductToOrder(currentUser, product, 1, request);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("orders/orderDetails")
    public String showOrderDetails(){
        return "Order/displayOrder";
    }

    @GetMapping("/orders/submitOrder")
    public String submitOrder( HttpServletRequest request){
        Order currentOrder = (Order) request.getSession().getAttribute("currentOrder");
        currentOrder.setStatus(OrderStatus.SENT);
        orderRepository.save(currentOrder);
        request.getSession().setAttribute("currentOrder", new Order());
        return "redirect:/?successfulOrder";
    }

}
