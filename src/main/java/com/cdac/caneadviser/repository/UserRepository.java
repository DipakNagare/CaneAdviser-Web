package com.cdac.caneadviser.repository;
import org.springframework.data.repository.CrudRepository;

import com.cdac.caneadviser.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    
}

