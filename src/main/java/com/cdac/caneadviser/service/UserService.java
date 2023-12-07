// package com.cdac.caneadviser.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.cdac.caneadviser.repository.UserRepository;

//  @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final BCryptPasswordEncoder passwordEncoder;

//     @Autowired
//     public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public User loginUser(String userId, String password) {
//         User user = userRepository.findByUserId(userId);

//         if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//             return user;
//         } else {
//             return null;
//         }
//     }

//     public void saveUser(User user) {
//         // Hash the user's password before saving it to the database
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         userRepository.save(user);
//     }
// }
