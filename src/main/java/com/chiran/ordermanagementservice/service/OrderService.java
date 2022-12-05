package com.chiran.ordermanagementservice.service;

import com.chiran.ordermanagementservice.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
