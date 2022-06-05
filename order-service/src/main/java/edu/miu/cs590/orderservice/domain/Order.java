package edu.miu.cs590.orderservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    List<Product> products = new ArrayList<>();
//    @OneToMany
//    private List<Product> products;

//    @OneToMany
//    @JoinTable(name = "order_orderline",
//            joinColumns={@JoinColumn(name = "order_id")},
//            inverseJoinColumns = {@JoinColumn(name="orderline_id")})
//    private List<Orderline> orderlineList;

    @Embedded
    private ShippingAddress shippingAddress;

    public void addToProductList(Product product) {
        products.add(product);
    }

}
