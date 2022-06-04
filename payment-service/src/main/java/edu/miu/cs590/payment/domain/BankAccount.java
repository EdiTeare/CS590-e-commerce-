package edu.miu.cs590.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount implements IPaymentMethod{
    private Integer bankAccountId;
    private String accountNumber;

}
