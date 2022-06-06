package edu.miu.cs590.productservice.controller;

import edu.miu.cs590.productservice.model.Product;
import edu.miu.cs590.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products); //loc.../products
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<String>("Empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.removeProduct(id));
    }

}