package com.works.validation.response;

import com.works.entities.Books;
import org.springframework.data.domain.Page;

public class CategoryPage {

    private String category;
    private Page<Books> page;

    public CategoryPage(String category, Page<Books> page) {
        this.category = category;
        this.page = page;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Page<Books> getPage() {
        return page;
    }

    public void setPage(Page<Books> page) {
        this.page = page;
    }
}
