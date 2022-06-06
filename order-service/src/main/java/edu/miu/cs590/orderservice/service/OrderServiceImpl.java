package edu.miu.cs590.orderservice.service;
import edu.miu.cs590.orderservice.domain.*;
import edu.miu.cs590.orderservice.dto.ProductDTO;
import edu.miu.cs590.orderservice.repositories.OrderRepository;
import edu.miu.cs590.orderservice.repositories.ProductRepository;
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
    private ProductRepository productRepository;
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
    public Order addProduct(ProductDTO product, String userId) {
        ProductDTO product1 = restTemplate.getForObject("http://localhost:9021/products/" + product.getProductId(), ProductDTO.class);
        Account account = restTemplate.getForObject("http://localhost:9001/accounts/" + userId, Account.class);
        Order order = new Order();
        if(product1 != null){
            if(product1.isInStock()) {
                Product product2 = new Product();
                product2.setProductId(product.getProductId());
                order.addToProductList(product2);
                order.setStatus(Status.DRAFT);
                order.setUserId(account.getId().toString());
                return orderRepository.save(order);
            }
            else {
                System.out.println("Product out of stock");
                return null;
            }
        }
        else {
            System.out.println("Product Not found");
            return null;
        }
    }

    @Override
    public Order addProductById(Long orderId , ProductDTO product) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent()){
            System.out.println("No Order Found by id: "+ orderId );
            return null;
        }
        Order order = optionalOrder.get();
        if(order.getStatus().equals(Status.DRAFT)){
            ProductDTO product1 = restTemplate.getForObject("http://localhost:9021/products/" + product.getProductId(), ProductDTO.class);

            if(product1.isInStock()) {
                Product product2 = new Product();
                product2.setProductId(product.getProductId());
                productRepository.save(product2);
                order.addToProductList(product2);
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
    public Order addPaymentType(Long orderId, PaymentType paymentType) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setPaymentType(paymentType);
            return orderRepository.save(order);
        }
        System.out.println("No Order Found by id: "+ orderId );
        return null;
    }

    @Override
    public Order updateStatus(Long orderId, Status status){
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        return order;
    }

    @Override
    public Order deleteOrder(Long orderId){
        Order order=orderRepository.findById(orderId).get();
        orderRepository.delete(order);
        return order;
    }


    @Override
    public String pay(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent()){
            System.out.println("No Order Found by id: "+ orderId );
            return "Order is Not Successful";
        }
        Order order = optionalOrder.get();
        order.setStatus(Status.PENDING);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentType(order.getPaymentType());
        paymentRequest.setOrderId(order.getId().toString());
        paymentRequest.setUserId(order.getUserId());
        restTemplate.postForObject("http://localhost:9031/payments/" + order.getPaymentType(),paymentRequest, String.class);
        order.setStatus(Status.SHIPPED);
        orderRepository.save(order);
        return "Order is Successful!!";
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
