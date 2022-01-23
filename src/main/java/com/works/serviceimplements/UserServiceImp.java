package com.works.serviceimplements;

import com.works.entities.Cart;
import com.works.entities.Users;
import com.works.enums.ResultEnum;
import com.works.exceptions.OurException;
import com.works.repositories.CartRepository;
import com.works.repositories.UserRepository;
import com.works.services.UserService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImp implements UserService {

    final UserRepository userRepository;
    final CartRepository cartRepository;
    final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<Users> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public Users save(Users user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            Users savedUser = userRepository.save(user);

            // initial Cart
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new OurException(ResultEnum.VALID_ERROR);

        }

    }

    @Override
    @Transactional
    public Users update(Users user) {
        Users oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }
}
