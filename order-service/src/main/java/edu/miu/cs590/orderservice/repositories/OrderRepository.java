package edu.miu.cs590.orderservice.repositories;

import edu.miu.cs590.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
