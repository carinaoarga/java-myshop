package com.myshop.ninashop.controller;

import com.myshop.ninashop.dto.UserDTO;
import com.myshop.ninashop.model.Order;
import com.myshop.ninashop.model.OrderStatus;
import com.myshop.ninashop.model.User;
import com.myshop.ninashop.reporitories.OrderRepository;
import com.myshop.ninashop.reporitories.UserRepository;
import com.myshop.ninashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/users/register")
    public String renderRegistrationForm(Model model) {
        UserDTO u = new UserDTO();
        model.addAttribute("user", u);
        return "User/registration";
    }

    @PostMapping("/users/register")
    public String addUser(@ModelAttribute("userReg") UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/users/register?success";
    }

    @GetMapping("/users/login")
    public String renderLoginForm(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "User/login";
        }
        else {
            User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            session.setAttribute("currentUser", currentUser);
            session.setAttribute("currentOrder", (Order)orderRepository.findByUserAndStatus(currentUser.getId(), OrderStatus.NOT_SENT.toString()));
        }
        return "redirect:/";
    }


    @GetMapping("/users/accountDetails")
    public String getUserDetails(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/";
        }
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "User/accountDetails";
    }
}