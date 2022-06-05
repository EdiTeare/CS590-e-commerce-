package edu.miu.cs590.account.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String email;

    @Embedded
    private Address shippingAddress;

    @Transient
    private List<PaymentMethod> paymentMethodList = new ArrayList<>();

//@Enumerated
    private PaymentType paymentType;

    public void addPaymentMethod(PaymentMethod paymentMethod){
        paymentMethodList.add(paymentMethod);
    }
}
