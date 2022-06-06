package edu.miu.cs590.productservice.service;

import edu.miu.cs590.productservice.model.Product;
import edu.miu.cs590.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product addProduct(Product product) {

        product.setInStock(true);
        return productRepository.save(product);
    }

//    @Override
//    public void removeProduct(Long productId) {
//        productRepository.deleteById(productId);
//    }
    @Override
    public boolean removeProduct(Long productId) {
        Optional<Product> productOptional =productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Long productId, Product productBody) {
        Optional<Product> productOptional= productRepository.findById(productId);
        if(productOptional.isPresent()){
            productBody.setId(productId);
            return productRepository.save(productBody);
        }
        return productRepository.save(productBody);
    }

}
