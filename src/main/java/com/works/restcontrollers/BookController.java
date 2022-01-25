package com.works.restcontrollers;

import com.works.entities.Books;
import com.works.repositories.BooksRepository;
import com.works.services.BookService;
import com.works.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping("/book")
public class BookController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BookService bookService;

    @GetMapping("")
    public Page<Books> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return bookService.findAll(request);
    }

    @GetMapping("/{bookId}")
    public Books showOne(@PathVariable("bookId") String bookId) {

        return bookService.findOne(bookId);
    }

    @PostMapping("/seller/new")
    public ResponseEntity create(@Valid @RequestBody Books book,
                                 BindingResult bindingResult) {
        Books bookIdExists = bookService.findOne(book.getBookId());
        if (bookIdExists != null) {
            bindingResult
                    .rejectValue("bookId", "error.book",
                            "There is already a book with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("/seller/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String bookId,
                               @Valid @RequestBody Books book,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!bookId.equals(book.getBookId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(bookService.update(book));
    }

    @DeleteMapping("/seller/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") String bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }
}
