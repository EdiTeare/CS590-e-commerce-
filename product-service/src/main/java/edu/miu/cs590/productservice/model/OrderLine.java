package edu.miu.cs590.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    private Long quantity;
    private Long totalPrice;
    private String status;
    private List<Product> productList;

}
