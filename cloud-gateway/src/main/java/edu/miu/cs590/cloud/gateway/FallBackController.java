package edu.miu.cs590.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping
    public String accountServiceFallBackMethod() {
        return "The Service is taking longer than Expected." +
                " Please try again later";
    }
//
//    @GetMapping("/orderService")
//    public String orderServiceFallBackMethod() {
//        return "Order Service is taking longer than Expected." +
//                " Please try again later";
//    }
//
//    @GetMapping("/paymentService")
//    public String paymentServiceFallBackMethod() {
//        return "Payment Service is taking longer than Expected." +
//                " Please try again later";
//    }
//
//    @GetMapping("/productService")
//    public String productServiceFallBackMethod() {
//        return "Product Service is taking longer than Expected." +
//                " Please try again later";
//    }
}
