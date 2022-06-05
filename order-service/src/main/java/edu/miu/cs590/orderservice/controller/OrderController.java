package edu.miu.cs590.orderservice.controller;

import edu.miu.cs590.orderservice.domain.Order;
import edu.miu.cs590.orderservice.domain.Product;
import edu.miu.cs590.orderservice.domain.Status;
import edu.miu.cs590.orderservice.service.OrderService;
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

    @PostMapping()
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        Order order1 = orderService.save(order);
        return new ResponseEntity<>(order1, HttpStatus.OK);
    }

    @PostMapping("/addProductToOrder")
    public ResponseEntity<?> addProductToOrder(@RequestBody Product product, @RequestBody String acctId) {
        Order order=orderService.addProduct(product,acctId);
        if(order == null) return new ResponseEntity<>("Order create failed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/addProductToOrder/{orderId}")
    public ResponseEntity<?> addProductToOrderById(@RequestBody Product product, @PathVariable Long orderId) {
        Order order=orderService.addProductById(orderId,product);
        if(order == null) return new ResponseEntity<>("Order create failed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

        @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        return new ResponseEntity<Object>(orderService.updateStatus(id, status), HttpStatus.OK);
    }


    @PostMapping("/{id}/pay")
    public ResponseEntity<?> pay(@RequestBody Order order) {
        orderService.pay(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
