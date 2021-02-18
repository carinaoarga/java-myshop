package com.myshop.ninashop.controller;

import com.myshop.ninashop.model.Product;
import com.myshop.ninashop.model.ProductCategory;
import com.myshop.ninashop.reporitories.ProductCategoryRepository;
import com.myshop.ninashop.reporitories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @GetMapping
    public String listProducts(Model model){
        model.addAttribute("productsList", productRepository.findAll());
        return "Product/productsList";
    }

    @GetMapping("/show/{categoryId}")
    public String listByCategory(@PathVariable("categoryId") Long categoryId, Model model){
        model.addAttribute("productsList", productRepository.findAllByCategory(categoryId));
        return "Product/productsList";
    }

    @GetMapping("/add")
    public String renderProductForm(Model model){

        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("productCategories", productCategories);
        return "Product/productForm";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product prd, BindingResult bindingResult, Model model){
        System.out.println("INTRA");
        if (bindingResult.hasErrors()){
            System.out.println("SI AICI");
            return "Product/productForm";
        }
        productRepository.save(prd);
        model.addAttribute("productsList", productRepository.findAll());
        return  "redirect:/products";
    }

    @GetMapping("/{id}")
    public ModelAndView getProductById(@PathVariable Long id) {
        return new ModelAndView("productDetails", "product", productRepository.findById(id));
    }

}
