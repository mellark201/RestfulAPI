package com.greyfox.GreyFox.repositories;

import com.greyfox.GreyFox.domain.User;
import com.greyfox.GreyFox.exceptions.ETAuthException;

public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws ETAuthException;
    User findByEmailAndPassword(String email, String password) throws ETAuthException;
    Integer getCountByEmail(String email);
    User findById(Integer userId);
}
