package com.works.serviceimplements;

import com.works.entities.Books;
import com.works.enums.BookStatusEnum;
import com.works.enums.ResultEnum;
import com.works.exceptions.OurException;
import com.works.repositories.BooksRepository;
import com.works.services.BookService;
import com.works.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImp implements BookService {


    final BooksRepository booksRepository;
    final CategoryService categoryService;

    public BookServiceImp(BooksRepository booksRepository, CategoryService categoryService) {
        this.booksRepository = booksRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Books findOne(String bookId) {

        return booksRepository.findByBookId(bookId);
    }

    @Override
    public Page<Books> findUpAll(Pageable pageable) {
        return booksRepository.findAllByBookStatusOrderByBookIdAsc(BookStatusEnum.UP.getCode(),pageable);
    }

    @Override
    public Page<Books> findAll(Pageable pageable) {
        return booksRepository.findAllByOrderByBookId(pageable);
    }

    @Override
    public Page<Books> findAllInCategory(Integer categoryType, Pageable pageable) {
        return booksRepository.findAllByCategoryTypeOrderByBookIdAsc(categoryType, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(String bookId, int amount) {
        Books books = findOne(bookId);
        if (books == null) throw new OurException(ResultEnum.BOOK_NOT_EXIST);

        int update = books.getBookStock() + amount;
        books.setBookStock(update);
        booksRepository.save(books);
    }

    @Override
    @Transactional
    public void decreaseStock(String bookId, int amount) {
        Books books = findOne(bookId);
        if (books == null) throw new OurException(ResultEnum.BOOK_NOT_EXIST);

        int update = books.getBookStock() - amount;
        if(update <= 0) throw new OurException(ResultEnum.BOOK_NOT_EXIST );

        books.setBookStock(update);
        booksRepository.save(books);
    }

    @Override
    @Transactional
    public Books offSale(String bookId) {
        Books books = findOne(bookId);
        if (books == null) throw new OurException(ResultEnum.BOOK_NOT_EXIST);

        if (books.getBookStatus().equals(BookStatusEnum.DOWN.getCode())) {
            throw new OurException(ResultEnum.BOOK_STATUS_ERROR);
        }

        books.setBookStatus(BookStatusEnum.DOWN.getCode());
        return booksRepository.save(books);
    }

    @Override
    @Transactional
    public Books onSale(String bookId) {
        Books books = findOne(bookId);
        if (books == null) throw new OurException(ResultEnum.BOOK_NOT_EXIST);

        if (books.getBookStatus().equals(BookStatusEnum.UP.getCode())) {
            throw new OurException(ResultEnum.BOOK_STATUS_ERROR);
        }

        books.setBookStatus(BookStatusEnum.UP.getCode());
        return booksRepository.save(books);
    }

    @Override
    public Books update(Books books) {

        // if null throw exception
        categoryService.findByCategoryType(books.getCategoryType());
        if(books.getBookStatus() > 1) {
            throw new OurException(ResultEnum.BOOK_STATUS_ERROR);
        }


        return booksRepository.save(books);
    }

    @Override
    public Books save(Books books) {
        return update(books);
    }

    @Override
    public void delete(String bookId) {
        Books books = findOne(bookId);
        if (books == null) throw new OurException(ResultEnum.BOOK_NOT_EXIST);
        booksRepository.delete(books);

    }
}
