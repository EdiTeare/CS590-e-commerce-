package edu.miu.cs590.account.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bank-Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends PaymentMethod{
    private Integer bankAccountId;
    private String accountName;

}
