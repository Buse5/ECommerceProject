package com.works.repositories;
import org.springframework.data.domain.Page;
import com.works.entities.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderId(Long orderId);

    Page<Order> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);

    Page<Order> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail, Pageable pageable);

    Page<Order> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

    Page<Order> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(String buyerPhone, Pageable pageable);
}