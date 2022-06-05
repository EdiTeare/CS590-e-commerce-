package edu.miu.cs590.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private Long productId;
    private Long quantity;
    private boolean inStock;

}
