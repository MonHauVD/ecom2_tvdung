/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.config;

/**
 *
 * @author TranVietDung
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;
import vietdung.ecom2_tvdung.service.CustomUserDetailsService;
 
 
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
 
@Configuration
@Order(1)
@Slf4j
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
 
        return authProvider;
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider1());
 
        http.authorizeRequests().antMatchers("/", "/images/**").permitAll();
 
        http.antMatcher("/admin/**")
            .authorizeRequests().anyRequest().hasAuthority("ADMIN")
            .and()
            .formLogin()
                .loginPage("/admin/login")
                    .usernameParameter("username")
                    .loginProcessingUrl("/admin/loginvalidate")
                    .successHandler((request, response, authentication) ->
                    {
                        log.info("successHandler: admin||||||||||||||||||||||||||||||||||");
                        response.sendRedirect("/admin/"); // Redirect on success
                    })
                    .failureHandler((request, response, exception) ->
                    {
                        log.info("failureHandler: admin||||||||||||||||||||||||||||||||||");
                        response.sendRedirect("/admin/login?error=true"); // Redirect on failure
                    })
//                    .defaultSuccessUrl("/admin/")
                    .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login");
 
    }
 
}