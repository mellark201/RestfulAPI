package com.greyfox.GreyFox.repositories;

import com.greyfox.GreyFox.domain.User;
import com.greyfox.GreyFox.exceptions.ETAuthException;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws ETAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ETAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
