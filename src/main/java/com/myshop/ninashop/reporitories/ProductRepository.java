package com.myshop.ninashop.reporitories;

import com.myshop.ninashop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT DISTINCT prd_category FROM shop_products", nativeQuery = true)
    Set<String> findAllDistinctCategories();

    @Query(value="SELECT * FROM shop_products WHERE category_id = ?1", nativeQuery = true)
    List<Product> findAllByCategory(Long category);
}
