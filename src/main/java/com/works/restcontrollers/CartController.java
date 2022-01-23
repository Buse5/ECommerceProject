package com.works.restcontrollers;

import com.works.entities.BooksInOrders;
import com.works.entities.Cart;
import com.works.entities.Users;
import com.works.form.ItemForm;
import com.works.services.*;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/cart")
public class CartController {

    final CartService cartService;
    final UserService userService;
    final BookService bookService;
    final BooksInOrderService booksInOrderService;
    final OrderService orderService;

    public CartController(CartService cartService, UserService userService, BookService bookService, BooksInOrderService booksInOrderService, OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.bookService = bookService;
        this.booksInOrderService = booksInOrderService;
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<BooksInOrders> booksInOrders, Principal principal) {
        Users user = userService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(booksInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        Users user = userService.findOne(principal.getName());
        return cartService.getCart(user);
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var booksInfo = bookService.findOne(form.getBookId());
        try {
            mergeCart(Collections.singleton(new BooksInOrders(booksInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/itemId")
    public BooksInOrders modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        Users user = userService.findOne(principal.getName());
        booksInOrderService.update(itemId, quantity, user);
        return booksInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/itemId")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        Users user = userService.findOne(principal.getName());
        cartService.delete(itemId, user);
        //flush memory into database
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal) {
        Users user = userService.findOne(principal.getName()); //Email as username
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }
}
