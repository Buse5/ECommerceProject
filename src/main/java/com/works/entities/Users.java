package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = 4887904943282174032L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotEmpty
    private boolean active;

    @NotEmpty
    private String address;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 8, message = "Length must be more than 8")
    private String password;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String role="ROLE_CUSTOMER";

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + userId +
                ", active=" + active +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
