package com.myshop.ninashop.model;


import javax.persistence.*;

@Entity
@Table(name = "shop_order_products")
public class OrderProduct {

    @Id
    @Column(name = "op_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prd_id")
    private Product product;

    @Column(name = "prd_quantity")
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
