package com.myshop.ninashop.service;

import com.myshop.ninashop.dto.UserDTO;
import com.myshop.ninashop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserDTO userDTO);
}
