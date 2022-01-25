package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class BooksInOrders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookInOrderId;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Order orders;

    @NotEmpty
    private String bookId;

    @NotEmpty
    private String bookName;

    @NotNull
    private String bookDescription;

    @NotNull
    private BigDecimal bookPrice;

    @NotNull
    private Integer bookCategoryType;

    @Min(0)
    private Integer bookStock;

    @Min(1)
    private Integer count;

    public BooksInOrders(Books books, Integer orderCount) {
        this.bookId = books.getBookId();
        this.bookName = books.getBookName();
        this.bookDescription = books.getBookDescription();
        this.bookPrice = books.getBookPrice();
        this.bookCategoryType = books.getCategoryType();
        this.bookStock = books.getBookStock();
        this.count = orderCount;
    }

    @Override
    public String toString() {
        return "BooksInOrders{" +
                "bookInOrderId=" + bookInOrderId +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookCategoryType=" + bookCategoryType +
                ", bookStock=" + bookStock +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksInOrders that = (BooksInOrders) o;
        return Objects.equals(bookInOrderId,that.bookInOrderId) &&
                Objects.equals(bookId,that.bookId) &&
                Objects.equals(bookName,that.bookName) &&
                Objects.equals(bookDescription,that.bookDescription) &&
                Objects.equals(bookPrice,that.bookPrice) &&
                Objects.equals(bookCategoryType,that.bookCategoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookInOrderId, bookId, bookName, bookDescription, bookPrice, bookCategoryType);
    }
}
