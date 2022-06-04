package edu.miu.cs590.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayPal implements IPaymentMethod{
    private Integer payPalId;
    private String userName;

}
