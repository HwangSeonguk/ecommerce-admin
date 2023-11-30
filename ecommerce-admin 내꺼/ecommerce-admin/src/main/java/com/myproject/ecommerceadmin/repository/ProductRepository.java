package com.myproject.ecommerceadmin.repository;

import com.myproject.ecommerceadmin.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndNameContainingAndProductDescContaining(Long id, String name, String productDesc);
}
