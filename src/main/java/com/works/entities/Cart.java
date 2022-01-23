package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Users user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "cart")
    private Set<BooksInOrders> books = new HashSet<>();

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", books=" + books + '}';
    }

    public Cart(Users user) {
        this.user = user;
    }
}
