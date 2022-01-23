package com.works.services;

import com.works.entities.BookCategories;
import com.works.repositories.BookCategoriesRepository;
import com.works.repositories.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface  CategoryService {
    List<BookCategories> findAll();

    BookCategories findByCategoryType(Integer bookType);

    List<BookCategories> findByCategoryTypeIn(List<Integer> bookTypeList);

    BookCategories save(BookCategories bookCategory);
}
