package com.cdac.caneadviser.service;

import com.cdac.caneadviser.entity.User;

public interface UserService {
    User loginUser(String email, String password);
}