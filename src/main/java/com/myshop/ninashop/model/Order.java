package com.myshop.ninashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shop_orders")
public class Order {

    @Id
    @NotNull
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "shop_order_product_link")
    private Set<OrderProduct> productsInOrder = new HashSet<OrderProduct>();


    public Order(){}

    public Order(User user) {
        this.user = user;
    }

    public int getTotalNumberOfProducts(){
        int count = 0;
        for (OrderProduct orderProduct: this.productsInOrder){
            count += orderProduct.getQuantity();
        }
        return count;
    }

    public float getTotalOrderValue(){
        float value = 0;
        for (OrderProduct orderProduct : this.productsInOrder){
            value += (orderProduct.getProduct().getPrice() * orderProduct.getQuantity());
        }
        return value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderProduct> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(Set<OrderProduct> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }

    public void addProductInOrder(Product p, int quantity){
        this.productsInOrder.add(new OrderProduct());
    }
}
