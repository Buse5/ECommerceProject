package com.works.serviceimplements;

import com.works.entities.BookCategories;
import com.works.enums.ResultEnum;
import com.works.exceptions.OurException;
import com.works.repositories.BookCategoriesRepository;
import com.works.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    final BookCategoriesRepository bookCategoriesRepository;

    public CategoryServiceImp(BookCategoriesRepository bookCategoriesRepository) {
        this.bookCategoriesRepository = bookCategoriesRepository;
    }

    @Override
    public List<BookCategories> findAll() {
        List<BookCategories> res = bookCategoriesRepository.findAllByOrderByCategoryType();
        return res;
    }

    @Override
    public BookCategories findByCategoryType(Integer categoryType) {
        BookCategories res = bookCategoriesRepository.findByCategoryType(categoryType);
        if(res == null) throw new OurException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    public List<BookCategories> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<BookCategories> res = bookCategoriesRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }

    @Override
    @Transactional
    public BookCategories save(BookCategories productCategory) {
        return bookCategoriesRepository.save(productCategory);
    }
}
