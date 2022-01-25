package com.works.restcontrollers;

import com.works.entities.BookCategories;
import com.works.entities.Books;
import com.works.services.BookService;
import com.works.services.CategoryService;
import com.works.validation.response.CategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BookService productService;

    /**
     * Show books in category
     *
     * @param categoryType
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/category/{type}")
    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size) {

        BookCategories cat = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Books> bookInCategory = productService.findAllInCategory(categoryType, request);
        var tmp = new CategoryPage("", bookInCategory);
        tmp.setCategory(cat.getCategoryName());
        return tmp;
    }
}
