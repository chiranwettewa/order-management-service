package com.chiran.ordermanagementservice.service;

import com.chiran.ordermanagementservice.entity.Order;
import com.chiran.ordermanagementservice.external.client.ProductService;
import com.chiran.ordermanagementservice.model.OrderRequest;
import com.chiran.ordermanagementservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    @Override
    public long placeOrder(OrderRequest orderRequest) {

        log.info("Placing order {}",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Creating order with status CREATED");

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .orderStatus("NEW")
                .build();
        order = orderRepository.save(order);
        log.info("Order place successfully {}",order);
        return order.getId();
    }
}
