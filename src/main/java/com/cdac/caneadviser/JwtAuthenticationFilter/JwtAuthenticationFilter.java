// package com.cdac.caneadviser.JwtAuthenticationFilter;

// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;


// import java.io.IOException;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${security.jwt.secret-key}")
//     private String secretKey;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {

//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);

//             try {
//                 if (validateToken(token)) {
//                     String role = extractRoleFromToken(token);

//                     UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(null, null, null);

//                     SecurityContextHolder.getContext().setAuthentication(authentication);
//                 }
//             } catch (JwtException e) {
//                 // Handle invalid or expired token
//             }
//         }

//         filterChain.doFilter(request, response);
//     }

//     private boolean validateToken(String token) {
//         // Use the same validation logic as in AuthController
//         return new AuthController(new BCryptPasswordEncoder()).validateToken(token);
//     }

//     private String extractRoleFromToken(String token) {
//         return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//     }
// }
