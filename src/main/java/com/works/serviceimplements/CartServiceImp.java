package com.works.serviceimplements;

import com.works.entities.BooksInOrders;
import com.works.entities.Cart;
import com.works.entities.Order;
import com.works.entities.Users;
import com.works.repositories.BooksInOrdersRepository;
import com.works.repositories.CartRepository;
import com.works.repositories.OrderRepository;
import com.works.repositories.UserRepository;
import com.works.services.BookService;
import com.works.services.CartService;
import com.works.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImp implements CartService {

    final BookService bookService;
    final OrderRepository orderRepository;
    final UserRepository userRepository;
    final BooksInOrdersRepository booksInOrderRepository;
    final CartRepository cartRepository;
    final UserService userService;

    public CartServiceImp(BookService bookService, OrderRepository orderRepository, UserRepository userRepository, BooksInOrdersRepository booksInOrderRepository, CartRepository cartRepository, UserService userService) {
        this.bookService = bookService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.booksInOrderRepository = booksInOrderRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public Cart getCart(Users user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<BooksInOrders> booksInOrders, Users user) {
        Cart finalCart = user.getCart();
        booksInOrders.forEach(bookInOrder -> {
            Set<BooksInOrders> set = finalCart.getBooks();
            Optional<BooksInOrders> old = set.stream().filter(e -> e.getBookId().equals(bookInOrder.getBookId())).findFirst();
            BooksInOrders prod;
            if (old.isPresent()) {
                prod = old.get();
                prod.setCount(bookInOrder.getCount() + prod.getCount());
            } else {
                prod = bookInOrder;
                prod.setCart(finalCart);
                finalCart.getBooks().add(prod);
            }
            booksInOrderRepository.save(prod);
        });
        cartRepository.save(finalCart);

    }

    @Override
    @Transactional
    public void delete(String itemId, Users user) {
        var op = user.getCart().getBooks().stream().filter(e -> itemId.equals(e.getBookId())).findFirst();
        op.ifPresent(bookInOrder -> {
            bookInOrder.setCart(null);
            booksInOrderRepository.deleteById(bookInOrder.getBookInOrderId());
        });
    }



    @Override
    @Transactional
    public void checkout(Users user) {
        Order order = new Order(user);
        orderRepository.save(order);

        user.getCart().getBooks().forEach(bookInOrder -> {
            bookInOrder.setCart(null);
            bookInOrder.setOrders(order);
            bookService.decreaseStock(bookInOrder.getBookId(), bookInOrder.getCount());
            booksInOrderRepository.save(bookInOrder);
        });

    }
}
