package com.greyfox.GreyFox.services;

import com.greyfox.GreyFox.domain.User;
import com.greyfox.GreyFox.exceptions.ETAuthException;
public interface UserService {

    User validateUser(String email, String password) throws ETAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws ETAuthException;
}
