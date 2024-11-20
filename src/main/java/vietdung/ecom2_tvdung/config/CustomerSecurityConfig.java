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
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;
import vietdung.ecom2_tvdung.service.CustomUserDetailsService;
 
@Configuration
@Order(2)
@Slf4j
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService customerUserDetailsService() {
        return new CustomUserDetailsService();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder2());
 
        return authProvider;
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider2());
 
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/login", "/registration", "/userloginvalidate", "/newuserregister", "/forgetPassword", "/test2", "/images/**").permitAll()
            .anyRequest().hasAuthority("USER")
            .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .loginProcessingUrl("/userloginvalidate")
                
                    .successHandler((request, response, authentication) ->
                    {
                        log.info("successHandler: Hello||||||||||||||||||||||||||||||||||");
                        response.sendRedirect("/"); // Redirect on success
                    })
                    .failureHandler((request, response, exception) ->
                    {
                        log.info("failureHandler: Hello||||||||||||||||||||||||||||||||||");
                        response.sendRedirect("/login?error=true"); // Redirect on failure
                    })
//                    .defaultSuccessUrl("/")
//                    .failureForwardUrl("/login?error=true")
                .permitAll()
            .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/");
                .logout(logout -> logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
//                    .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exception -> exception
                .accessDeniedPage("/403") // Custom 403 page
                );
    }
}