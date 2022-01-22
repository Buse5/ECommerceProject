package com.works.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.works.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, String> {
    Books findByProductId(String id);

    // On-Sale product
    Page<Books> findAllByProductStatusOrderByProductIdAsc(Integer bookStatus, Pageable pageable);

    // Product in one category
    Page<Books> findAllByCategoryTypeOrderByProductIdAsc(Integer bookCategoryType, Pageable pageable);

    Page<Books> findAllByOrderByProductId(Pageable pageable);
}
