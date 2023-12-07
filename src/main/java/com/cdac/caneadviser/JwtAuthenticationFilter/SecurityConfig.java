// package com.cdac.caneadviser.JwtAuthenticationFilter;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.env.Environment;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private Environment env;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable().authorizeRequests()
//             .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
//             .anyRequest().authenticated()
//             .and()
//             .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//     }

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
