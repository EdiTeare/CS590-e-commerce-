package edu.miu.cs590.orderservice.service;
import edu.miu.cs590.orderservice.domain.*;
import edu.miu.cs590.orderservice.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order get(Long id){
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

//    @Override
//    public Order save(Order order){
//
//        order.setStatus(Status.DRAFT);
//        for (Orderline orderline : order.getOrderlineList()) {
//            //Product product=restTemplate.getForObject(productService.getProductUri()+orderline.getProductList().forEach(p->p.getProductId(),Product.class));
//            for(Product p: orderline.getProductList()){
//                if(orderline.getQuantity() < p.getQuantity()) {
//                    System.out.println("Product [" + p.getProductId() + "] stock is below the requested quantity.");
//                    return null;
//                }
//            }
//        }
//        //productService.saveAll(order.getOrderlineList().forEach(orderline -> orderline.getProductList()));
//        orderRepository.save(order);
//        return order;
//    }


    @Override
    public Order addProduct(Product product, String userId) {
        Product product1 = restTemplate.getForObject("http://PRODUCT-SERVICE/products" + product.getProductId(), Product.class);
        Account account = restTemplate.getForObject("http://ACCOUNT-SERVICE/accounts" + userId, Account.class);
        Order order = new Order();
        if(product1.isInStock()) {
            order.addToProductList(product1);
            order.setStatus(Status.DRAFT);
            order.setUserId(account.getId().toString());
            order.setPaymentType(account.getPaymentType());
            return orderRepository.save(order);
        } else {
            System.out.println("Product out of stock");
            return null;
        }
    }

    @Override
    public Order addProductById(Long orderId ,Product product) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent()){
            System.out.println("No Order Found by id: "+ orderId );
            return null;
        }
        Order order = optionalOrder.get();
        if(order.getStatus().equals(Status.DRAFT)){
            //        Product product1 = restTemplate.getForObject("http://PRODUCT-SERVICE/products" + product.getProductId(), Product.class);
            Product product1 = restTemplate.getForObject("http://PRODUCT-SERVICE/products" + product.getProductId(), Product.class);

            if(product1.isInStock()) {
                order.addToProductList(product1);
                return orderRepository.save(order);
            } else {
                System.out.println("Product out of stock");
                return null;
            }
        }else {
            System.out.println("Order status already changed");
            return null;
        }
    }

    @Override
    public Order save(Order order){
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateStatus(Long orderId, Status status){
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        return order;
    }

    @Override
    public void pay(Order order){
        order.setStatus(Status.PENDING);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentType(order.getPaymentType());
        paymentRequest.setOrderId(order.getId().toString());
        paymentRequest.setUserId(order.getUserId());
        restTemplate.getForObject("http://PAYMENT-SERVICE/payments/" + paymentRequest.getPaymentType(), PaymentRequest.class);
        order.setStatus(Status.SHIPPED);
//        Order order=orderRepository.findById(orderId).get();
//        PaymentRequest request = new PaymentRequest();
////        request.setUserId(order.getUserId());
////        request.setOrderId(orderId.toString());

//        request.setPaymentType(PaymentType.valueOf(paymentType));

//        Double totalPrice = 0.0;
//        log.info(productService.getProductUri());
//        for (Orderline orderline : order.getOrderlineList() ) {
//            Product product = restTemplate.getForObject(productService.getProductUri()+p.getProductId(), Product.class);
//            totalPrice += product.getPrice() * .getQuantity();
//        }
//        request.setBalance(totalPrice);




//        return order;
    }

}
