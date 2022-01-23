package com.works.serviceimplements;

import com.works.entities.Books;
import com.works.entities.BooksInOrders;
import com.works.entities.Order;
import com.works.enums.OrderStatusEnum;
import com.works.enums.ResultEnum;
import com.works.exceptions.OurException;
import com.works.repositories.BooksInOrdersRepository;
import com.works.repositories.BooksRepository;
import com.works.repositories.OrderRepository;
import com.works.repositories.UserRepository;
import com.works.services.BookService;
import com.works.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImp implements OrderService {

    final OrderRepository orderRepository;
    final UserRepository userRepository;
    final BooksInOrdersRepository booksInOrdersRepository;
    final BooksRepository booksRepository;
    final BookService bookService;

    public OrderServiceImp(OrderRepository orderRepository, UserRepository userRepository, BooksInOrdersRepository booksInOrdersRepository, BooksRepository booksRepository, BookService bookService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.booksInOrdersRepository = booksInOrdersRepository;
        this.booksRepository = booksRepository;
        this.bookService = bookService;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
    }

    @Override
    public Page<Order> findByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
    }

    @Override
    public Page<Order> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }

    @Override
    public Page<Order> findByBuyerPhone(String phone, Pageable pageable) {
        return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
    }

    @Override
    public Order findOne(Long orderId) {
        Order orderMain = orderRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new OurException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public Order finish(Long orderId) {
        Order orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new OurException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(orderMain);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public Order cancel(Long orderId) {
        Order orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new OurException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(orderMain);

        // Restore Stock
        Iterable<BooksInOrders> products = orderMain.getBooks();
        for(BooksInOrders booksInOrder : products) {
            Books books = booksRepository.findByBookId(booksInOrder.getBookId());
            if(books != null) {
                bookService.increaseStock(booksInOrder.getBookId(), booksInOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);

    }
}
