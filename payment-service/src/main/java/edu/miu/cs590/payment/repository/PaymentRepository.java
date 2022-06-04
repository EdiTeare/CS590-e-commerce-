package edu.miu.cs590.payment.repository;

import edu.miu.cs590.payment.domain.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<OrderPayment, Integer> {
}
