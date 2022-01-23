package com.works.repositories;

import com.works.entities.BookCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoriesRepository extends JpaRepository<BookCategories, Integer> {
    // Some category
    List<BookCategories> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    // All category
    List<BookCategories> findAllByOrderByCategoryType();
    // One category
    BookCategories findByCategoryType(Integer categoryType);
}