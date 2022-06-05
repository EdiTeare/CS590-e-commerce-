package edu.miu.cs590.payment.service;

import edu.miu.cs590.payment.domain.OrderPayment;

public interface PaymentService {
    String makePayment(OrderPayment orderPayment);
}
