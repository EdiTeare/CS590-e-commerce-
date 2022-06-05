package edu.miu.cs590.productservice.service;

import edu.miu.cs590.productservice.model.Product;

import java.util.List;

public interface ProductService  {

    Product findProductById(Long id);

    List<Product> findAllProducts();

    Product addProduct(Product product);

    void removeProduct(Long productId);


}
