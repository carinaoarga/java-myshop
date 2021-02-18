package com.myshop.ninashop.controller;

import com.myshop.ninashop.model.Product;
import com.myshop.ninashop.reporitories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String homePage(Model model){
        List<Product> productsList = productRepository.findAll();
        model.addAttribute("productsList", productsList);
        return "index";
    }
}
