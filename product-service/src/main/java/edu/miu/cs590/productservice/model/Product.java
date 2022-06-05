package edu.miu.cs590.productservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private boolean inStock;
    private Long price;
    private Long thresholdQuantity;
}
