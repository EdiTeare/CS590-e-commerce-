package edu.miu.cs590.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PayPal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayPal extends PaymentMethod{
    private Integer payPalId;
    private String userName;
}
