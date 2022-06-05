package edu.miu.cs590.orderservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequest {
    private String userId;
    private PaymentType paymentType;
    private String orderId;
    private String status;

}
