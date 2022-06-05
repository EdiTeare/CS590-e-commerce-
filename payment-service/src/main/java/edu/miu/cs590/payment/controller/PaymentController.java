package edu.miu.cs590.payment.controller;

import edu.miu.cs590.payment.domain.OrderPayment;
import edu.miu.cs590.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/bank-account")
    public String processBankPayment(@RequestBody OrderPayment orderPayment) {
        return paymentService.makePayment(orderPayment);
    }

    @GetMapping(value = "/cc")
    public String processCCPayment(@RequestBody OrderPayment orderPayment) {
        return paymentService.makePayment(orderPayment);
    }

    @GetMapping(value = "/paypal")
    public String processPayPalPayment(@RequestBody OrderPayment orderPayment) {
        return paymentService.makePayment(orderPayment);
    }
}