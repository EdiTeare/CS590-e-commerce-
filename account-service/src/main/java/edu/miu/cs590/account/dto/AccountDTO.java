package edu.miu.cs590.account.dto;

import edu.miu.cs590.account.domain.Address;
import edu.miu.cs590.account.domain.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private  String username;
    private Address shippingAddress;
    private List<PaymentMethod> paymentMethods;
}
