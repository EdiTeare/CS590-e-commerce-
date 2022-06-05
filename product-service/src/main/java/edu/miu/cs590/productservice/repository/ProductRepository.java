package edu.miu.cs590.productservice.repository;

import edu.miu.cs590.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductById(Long id);
}
