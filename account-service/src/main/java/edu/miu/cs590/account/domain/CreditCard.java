package edu.miu.cs590.account.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
@Entity
@DiscriminatorValue("CreditCard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends PaymentMethod{
    private Integer creditCardId;
    private String cardNumber;
    private LocalDate expirationDate;
    private Integer ccv;

}
