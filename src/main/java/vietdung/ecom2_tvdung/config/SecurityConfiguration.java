package vietdung.ecom2_tvdung.config;
//
//
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.service.UserService;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import vietdung.ecom2_tvdung.service.CustomUserDetailsService;

//@Configuration
@Slf4j
public class SecurityConfiguration //extends WebSecurityConfigurerAdapter
{
    
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//    
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        // Set the custom user details service for authentication
////        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
////    }
////    
////    @Override
////    @Bean
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
//    @Configuration
//    @Order(1)
//    public static class AdminConfigurationAdapter
//    {
//
//        @Bean
//        SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception
//        {
//            http.antMatcher("/admin/**")
//                    .authorizeHttpRequests(requests -> requests
//                    .requestMatchers(new AntPathRequestMatcher("/admin/login")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("file:D:/Lap trinh/PhanTichVaThietKeHTTT/Bai mau tren github/E-commerce-project-springBoot-master2/JtProject/image/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                    )
//                    .formLogin(login -> login
//                    .loginPage("/admin/login")
//                    .loginProcessingUrl("/admin/loginvalidate")
//                    .successHandler((request, response, authentication) ->
//                    {
//                        response.sendRedirect("/admin/"); // Redirect on success
//                    })
//                    .failureHandler((request, response, exception) ->
//                    {
//                        response.sendRedirect("/admin/login?error=true"); // Redirect on failure
//                    }))
//                    .logout(logout -> logout.logoutUrl("/admin/logout")
//                    .logoutSuccessUrl("/admin/login")
//                    .deleteCookies("JSESSIONID"))
//                    .exceptionHandling(exception -> exception
//                    .accessDeniedPage("/403") // Custom 403 page
//                    );
//            http.csrf(csrf -> csrf.disable());
//            return http.build();
//        }
//    }
//
//    @Configuration
//    @Order(2)
//    public static class UserConfigurationAdapter
//    {
//
//        @Bean
//        SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception
//        {
//            http.authorizeHttpRequests(requests -> requests
//                    .antMatchers("/login", "/register", "/newuserregister", "/test", "/test2", "/images/**").permitAll()
//                    .antMatchers("file:D:/Lap trinh/PhanTichVaThietKeHTTT/Bai mau tren github/E-commerce-project-springBoot-master2/JtProject/image/**").permitAll()
//                    .antMatchers("/**").hasRole("USER"))
//                    .formLogin(login -> login
//                    .loginPage("/login")
//                    .loginProcessingUrl("/userloginvalidate")
//                    .successHandler((request, response, authentication) ->
//                    {
//                        log.info("successHandler: Hello||||||||||||||||||||||||||||||||||");
//                        response.sendRedirect("/"); // Redirect on success
//                    })
//                    .failureHandler((request, response, exception) ->
//                    {
//                        log.info("failureHandler: Hello||||||||||||||||||||||||||||||||||");
//                        response.sendRedirect("/login?error=true"); // Redirect on failure
//                    }))
//                    .logout(logout -> logout.logoutUrl("/logout")
//                    .logoutSuccessUrl("/login?logout")
//                    .deleteCookies("JSESSIONID"))
//                    .exceptionHandling(exception -> exception
//                    .accessDeniedPage("/403") // Custom 403 page
//                    );
//
//            http.csrf(csrf -> csrf.disable());
//            http.sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//            );
//            return http.build();
//        }
//    }
//    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username ->
//        {
//            return customUserDetailsService.loadUserByUsername(username);
//        };
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

}
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import vietdung.ecom2_tvdung.service.CustomUserDetailsService;
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/admin/login").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/login").permitAll()
//                .antMatchers("/**").hasRole("USER")
//                .anyRequest().authenticated()
//            .and()
//                .formLogin()
//                    .loginProcessingUrl("/admin/loginvalidate")
//                    .loginPage("/admin/login")
////                    .defaultSuccessUrl("/admin/", true)
////                    .failureUrl("/admin/login?error=true")
//                    .successHandler(new AuthenticationSuccessHandler() {
//                        @Override
//                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                            log.info("Admin login success for user: {}", authentication.getName());
//                            response.sendRedirect("/admin/");
//                        }
//                    })
//                    .failureHandler(new AuthenticationFailureHandler() {
//                        @Override
//                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                            log.error("Admin login failed for user: {} with error: {}", request.getParameter("username"), exception.getMessage());
//                            response.sendRedirect("/admin/login?error=true");
//                        }
//                    })
//                    .permitAll()
//            .and()
//                .formLogin()
//                    .loginProcessingUrl("/userloginvalidate")
//                    .loginPage("/login")
////                    .defaultSuccessUrl("/", true)
////                    .failureUrl("/login?error=true")
//                    .successHandler(new AuthenticationSuccessHandler() {
//                        @Override
//                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                            log.info("User login success for user: {}", authentication.getName());
//                            response.sendRedirect("/");
//                        }
//                    })
//                    .failureHandler(new AuthenticationFailureHandler() {
//                        @Override
//                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                            log.error("User login failed for user: {} with error: {}", request.getParameter("username"), exception.getMessage());
//                            response.sendRedirect("/login?error=true");
//                        }
//                    })
//                    .permitAll()
//                .and()
//            .logout()
//                .logoutUrl("/logout")  // Default logout URL
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        // Check the user's role and redirect accordingly
//                        if (authentication != null && authentication.getAuthorities().stream()
//                                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
//                            response.sendRedirect("/admin/logout");  // Redirect to admin logout page
//                        } else {
//                            response.sendRedirect("/login");  // Redirect to login page for normal users
//                        }
//                    }
//                })
//                .permitAll()
//            .and()
//                .exceptionHandling()
//                    .accessDeniedPage("/403");
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import vietdung.ecom2_tvdung.service.CustomUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//@Slf4j
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Set the custom user details service for authentication
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//    }
//    
//     @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Configure HTTP security settings
//        http.csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access /admin/** endpoints
//                .antMatchers("/admin/login", "/admin/loginvalidate").permitAll() // Allow unauthenticated access to admin login
//                .antMatchers("/login", "/login/**").permitAll()  // Everyone can access login page
//                .antMatchers("/userloginvalidate", "/admin/loginvalidate").permitAll()  // Permit login validation
//                .anyRequest().authenticated()  // All other requests require authentication
//            .and()
//            .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/userloginvalidate")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler((request, response, authentication) -> {
//                    log.info("User logged in successfully: {}", authentication.getName());
//                    response.sendRedirect("/");
//                })
//                .failureHandler((request, response, exception) -> {
//                    log.error("Login failed: {}", exception.getMessage());
//                    response.sendRedirect("/login?error=true");
//                })
//            .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    log.info("User logged out successfully: {}", (authentication != null) ? authentication.getName() : "Anonymous");
//                    response.sendRedirect("/login");
//                })
//            .and()
//            .exceptionHandling()
//                .accessDeniedPage("/403")  // Access Denied page
//            .and()
//            .formLogin()
//                .loginPage("/admin/login")
//                .loginProcessingUrl("/admin/loginvalidate")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler((request, response, authentication) -> {
//                    log.info("Admin logged in successfully: {}", authentication.getName());
//                    response.sendRedirect("/admin/");
//                })
//                .failureHandler((request, response, exception) -> {
//                    log.error("Admin login failed: {}", exception.getMessage());
//                    response.sendRedirect("/admin/login?error=true");
//                })
//            .and()
//            .logout()
//                .logoutUrl("/admin/logout")
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    log.info("Admin logged out successfully: {}", (authentication != null) ? authentication.getName() : "Anonymous");
//                    response.sendRedirect("/admin/login");
//                });
//    }
//    
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return customUserDetailsService;
//    }
//}
