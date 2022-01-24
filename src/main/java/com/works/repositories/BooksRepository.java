package com.works.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.works.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, String> {
    Books findByBookId(String id);

    // On-Sale product
    Page<Books> findAllByBookStatusOrderByBookIdAsc(Integer bookStatus, Pageable pageable);

    // Product in one category findAllByCategoryTypeOrderByBookIdAsc
    Page<Books> findAllByCategoryTypeOrderByBookIdAsc(Integer categoryType, Pageable pageable);

    Page<Books> findAllByOrderByBookId(Pageable pageable);
}
