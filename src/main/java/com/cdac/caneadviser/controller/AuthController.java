// package com.cdac.caneadviser.controller;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import com.cdac.caneadviser.entity.User;
// import com.cdac.caneadviser.service.UserService;

// import java.util.Date;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     private final String secretKey = "yourSecretKey"; // Replace with a strong secret key
//     private final long expirationTime = 864_000_000; // 10 days

//     private final UserService userService;

//     @Autowired
//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//         String userId = loginRequest.getUserId();
//         String password = loginRequest.getPassword();

//         // Fetch user from the database based on userId
//         User user = userService.loginUser(userId, password);

//         if (user != null) {
//             String token = generateToken(String.valueOf(user.getRoleId()));
//             return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
//         } else {
//             return ResponseEntity.status(401).body("Invalid credentials");
//         }
//     }

//     private String generateToken(String role) {
//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + expirationTime);

//         return Jwts.builder()
//                 .setSubject(role)
//                 .setIssuedAt(now)
//                 .setExpiration(expiryDate)
//                 .signWith(SignatureAlgorithm.HS256, secretKey)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }
