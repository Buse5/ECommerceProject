package com.works.services;

import com.works.entities.Order;
import com.works.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface  OrderService {
    Page<Order> findAll(Pageable pageable);

    Page<Order> findByStatus(Integer status, Pageable pageable);

    Page<Order> findByBuyerEmail(String email, Pageable pageable);

    Page<Order> findByBuyerPhone(String phone, Pageable pageable);

    Order findOne(Long orderId);

    Order finish(Long orderId);

    Order cancel(Long orderId);
}
