package com.works.services;

import com.works.entities.BooksInOrders;
import com.works.entities.Users;

public interface  BooksInOrderService {
    void update(String itemId, Integer quantity, Users user);

    BooksInOrders findOne(String itemId, Users user);
}
