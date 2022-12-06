package com.sneakerhead.storeapp.repo;


import com.sneakerhead.storeapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findById(long id);
    List<Product> findByNameContaining(String name);
    @Query("SELECT p FROM Product p WHERE p.type=:type")
    List<Product> findByType(String type);

    @Query("SELECT p FROM Product p")
    List<Product> findAll();
}

