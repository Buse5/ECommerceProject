package com.works.serviceimplements;

import com.works.entities.BooksInOrders;
import com.works.entities.Users;
import com.works.repositories.BooksInOrdersRepository;
import com.works.services.BooksInOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class BooksInOrderServiceImp implements BooksInOrderService {


    final BooksInOrdersRepository booksInOrdersRepository;

    public BooksInOrderServiceImp(BooksInOrdersRepository booksInOrdersRepository) {
        this.booksInOrdersRepository = booksInOrdersRepository;
    }

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, Users user) {
        var op = user.getCart().getBooks().stream().filter(e -> itemId.equals(e.getBookId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            booksInOrdersRepository.save(productInOrder);
        });
    }

    @Override
    public BooksInOrders findOne(String itemId, Users user) {
        var op = user.getCart().getBooks().stream().filter(e -> itemId.equals(e.getBookId())).findFirst();
        AtomicReference<BooksInOrders> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}
