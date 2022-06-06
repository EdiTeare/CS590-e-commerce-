package edu.miu.cs590.productservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String vendor;
    private Catagory catagory;
    private Long quantity;
    private Long price;

    @Transient
    private Long thresholdQuantity = 10L;

    @Transient
    private boolean inStock;


     public boolean isInStock() {
        inStock = quantity > thresholdQuantity;
        return inStock;
    }
}
