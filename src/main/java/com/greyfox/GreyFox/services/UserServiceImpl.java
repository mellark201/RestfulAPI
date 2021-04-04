package com.greyfox.GreyFox.services;

import com.greyfox.GreyFox.domain.User;
import com.greyfox.GreyFox.exceptions.ETAuthException;
import com.greyfox.GreyFox.repositories.UserRepository;
import com.greyfox.GreyFox.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.greyfox.GreyFox.exceptions.ETAuthException;

import java.util.Locale;
import java.util.regex.Pattern;

@Service

@Transactional
//This means that for each method, a database transaction will start and will commit only on on successful completion
// of the method

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws ETAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws ETAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email!=null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new ETAuthException("Invalid Email Format");
        Integer count = userRepository.getCountByEmail(email);
        if(count>0)
            throw new ETAuthException("Email Already Used");
        System.out.println(firstName+lastName+email+password);
        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }
}
