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

    @PostMapping(value = "/BANK")
    public String processBankPayment(@RequestBody OrderPayment orderPayment) {
        return paymentService.makePayment(orderPayment);
    }

    @GetMapping(value = "/CC")
    public String processCCPayment(@RequestBody OrderPayment orderPayment) {
        return paymentService.makePayment(orderPayment);
    }

    @GetMapping(value = "/PAYPAL")
    public String processPayPalPayment(@RequestBody OrderPayment orderPayment) {
        return paymentService.makePayment(orderPayment);
    }
}
