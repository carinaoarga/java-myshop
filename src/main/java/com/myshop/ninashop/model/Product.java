package com.myshop.ninashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="shop_products")
public class Product {
    @Id
    @Column(name = "prd_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "prd_name", nullable = false)
    @NotEmpty(message = "Name is mandatory!")
    private String name;

    @Column(name = "prd_price", nullable = false)
    private float price;

    @Column(name = "prd_description")
    private String description;

    @ManyToOne
    private ProductCategory category;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() { return price; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) { this.price = price; }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
