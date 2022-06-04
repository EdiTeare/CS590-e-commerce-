package edu.miu.cs590.payment.service;

import edu.miu.cs590.payment.domain.OrderPayment;
import edu.miu.cs590.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public String makePayment(OrderPayment orderPayment) {
        orderPayment.setStatus("SUCCESSFUL");
        orderPayment.setTransactionId(UUID.randomUUID().toString());

        paymentRepository.save(orderPayment);

        return "SUCCESSFUL";
    }
}
