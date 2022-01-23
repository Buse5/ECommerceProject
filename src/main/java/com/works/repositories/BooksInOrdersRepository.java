package com.works.repositories;

import com.works.entities.BooksInOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksInOrdersRepository extends JpaRepository<BooksInOrders, Long> {
}
