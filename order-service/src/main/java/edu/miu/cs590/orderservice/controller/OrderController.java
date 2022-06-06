package edu.miu.cs590.orderservice.controller;

import edu.miu.cs590.orderservice.domain.Order;
import edu.miu.cs590.orderservice.domain.PaymentType;
import edu.miu.cs590.orderservice.domain.Status;
import edu.miu.cs590.orderservice.dto.ProductDTO;
import edu.miu.cs590.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @PostMapping("/{acctId}/addProductToOrder")
    public ResponseEntity<?> addProductToOrder(@PathVariable String acctId, @RequestBody ProductDTO product) {
        Order order=orderService.addProduct(product,acctId);
        if(order == null) return new ResponseEntity<>("Order create failed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/addProductToOrder/{orderId}")
    public ResponseEntity<?> addProductToOrderById(@RequestBody ProductDTO product, @PathVariable Long orderId) {
        Order order=orderService.addProductById(orderId,product);
        if(order == null) return new ResponseEntity<>("Order Not Found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/addPaymentType/{orderId}")
    public ResponseEntity<?> addPaymentTypeToOrder(@RequestParam PaymentType paymentType, @PathVariable Long orderId) {
        Order order=orderService.addPaymentType(orderId,paymentType);
        if(order == null) return new ResponseEntity<>("Order Not Found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

        @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        return new ResponseEntity<Object>(orderService.updateStatus(id, status), HttpStatus.OK);
    }


    @PostMapping("/{orderId}/pay")
    public ResponseEntity<?> pay(@PathVariable Long orderId) {
        String result = orderService.pay(orderId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{orderid}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderid){
        return new ResponseEntity<Object>(orderService.deleteOrder(orderid), HttpStatus.OK);
    }

}
