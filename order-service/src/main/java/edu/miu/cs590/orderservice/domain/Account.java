package edu.miu.cs590.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private ShippingAddress shippingAddress;
    private PaymentType paymentType;

}
