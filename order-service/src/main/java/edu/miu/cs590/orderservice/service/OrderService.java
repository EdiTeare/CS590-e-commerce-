package edu.miu.cs590.orderservice.service;

import edu.miu.cs590.orderservice.domain.Order;
import edu.miu.cs590.orderservice.domain.Product;
import edu.miu.cs590.orderservice.domain.Status;
import edu.miu.cs590.orderservice.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface OrderService {
    Order get(Long id);
    List<Order> getAll();
    Order save(Order order);
    Order updateStatus(Long orderId, Status status);
    void pay(Order order);
    Order addProduct(ProductDTO product, String userId);
    Order addProductById(Long orderId, ProductDTO product);

}
