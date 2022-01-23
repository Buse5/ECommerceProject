package com.works.services;

import com.works.entities.BooksInOrders;
import com.works.entities.Cart;
import com.works.entities.Users;
import com.works.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface  CartService  {
    Cart getCart(Users user);

    void mergeLocalCart(Collection<BooksInOrders> bookInOrders, Users user);

    void delete(String itemId, Users user);

    void checkout(Users user);
}
