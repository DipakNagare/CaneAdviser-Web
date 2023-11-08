// package com.cdac.caneadviser.mail;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .authorizeRequests()
//                 .antMatchers("/login").permitAll()
//                 .anyRequest().authenticated()
//                 .and()
//             .formLogin()
//                 .loginPage("/login")
//                 .permitAll()
//                 .and()
//             .logout()
//                 .permitAll();
//     }
// }