package com.works.repositories;

import com.works.entities.BooksInOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksInOrdersRepository extends JpaRepository<BooksInOrders, Long> {
}
