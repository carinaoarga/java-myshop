package com.myshop.ninashop.reporitories;

import com.myshop.ninashop.model.Order;
import com.myshop.ninashop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM SHOP_ORDERS WHERE U_ID = ?1 AND STATUS = ?2", nativeQuery = true)
    Order findByUserAndStatus(Long userId, String status);

    @Query(value = "SELECT * FROM SHOP_ORDERS WHERE U_ID = ?1", nativeQuery = true)
    Order findAllByUser(Long userId);
}

