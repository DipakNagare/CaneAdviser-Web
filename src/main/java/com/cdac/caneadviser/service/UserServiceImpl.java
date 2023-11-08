package com.cdac.caneadviser.service;
import org.springframework.stereotype.Service;

import com.cdac.caneadviser.entity.User;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User loginUser(String email, String password) {
        // Replace the test credentials with your desired email and password
        String testEmail = "dipakN@gmail.com";
        String testPassword = "Dipak@123";

        if (email.equals(testEmail) && password.equals(testPassword)) {
            // Successful login
            return new User();
        }
        return null; // Authentication failed
    }
}
