package edu.miu.cs590.orderservice.repositories;

import edu.miu.cs590.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
