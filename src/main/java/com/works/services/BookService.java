package com.works.services;

import com.works.entities.Books;
import com.works.repositories.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface  BookService {
    Books findOne(String productId);

    // All products for sale
    Page<Books> findUpAll(Pageable pageable);

    // All products
    Page<Books> findAll(Pageable pageable);

    // All products in selected category
    Page<Books> findAllInCategory(Integer categoryType, Pageable pageable);

    // Increase stock
    void increaseStock(String bookId, int amount);

    // Decrease stock
    void decreaseStock(String bookId, int amount);

    Books offSale(String bookInfo);
    Books onSale(String bookInfo);
    Books update(Books bookInfo);
    Books save(Books bookInfo);

    void delete(String productId);
}
