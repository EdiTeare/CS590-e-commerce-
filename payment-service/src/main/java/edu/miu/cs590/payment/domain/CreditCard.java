package edu.miu.cs590.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard implements IPaymentMethod{
    private Integer creditCardId;
    private String cardNumber;
    private LocalDate expirationDate;
    private Integer ccv;
}
