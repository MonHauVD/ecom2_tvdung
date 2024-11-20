/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

/**
 *
 * @author TranVietDung
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.ChangePasswordDto;
import vietdung.ecom2_tvdung.controller.dto.ForgetPasswordDto;
import vietdung.ecom2_tvdung.controller.dto.UserRegistrationDto;
import vietdung.ecom2_tvdung.model.Role;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService, UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        log.info("userDetailsService: ");
        User user = userRepository.findByEmail(username);

        
        if (user == null)
        {
            log.info("User with username " + username + " not found.");
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        log.info("userDetailsService: " + user.toString());
        
        return new CustomUserDetails(user);

    }
    
    @Override
    public User save(UserRegistrationDto registrationDto)
    {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Role.USER);

        return userRepository.save(user);
    }
    
    @Override
    public String changeTmpPass(ForgetPasswordDto forgetPasswordDto)
    {
        
        try
        {
            String email = forgetPasswordDto.getEmail();
            User user = userRepository.findByEmail(email);
            if (user == null)
            {
                // throw new UsernameNotFoundException("Invalid username or password.");
                throw new UsernameNotFoundException("notHaveEmail");
                // return "notHaveEmail";
            }
            String newTmpPass = generateResetToken();
            emailService.sendResetPassword(email, newTmpPass, email);
            userRepository.setNewPassword(email, passwordEncoder.encode(newTmpPass));
            return "OK";
        } catch (Exception e)
        {
            return "" + e;
        }
    }
    @Override
    public String changePass(ChangePasswordDto changePasswordDto)
    {
        // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String email2 = "";
        // if (principal instanceof User) {
        // 	email2 = ((User)principal).getEmail();
        // 	} else {
        // 		email2 = principal.toString();
        // 	}

        try
        {
            String email = changePasswordDto.getEmail();
            User user = userRepository.findByEmail(email);
            if (user == null)
            {
                // throw new UsernameNotFoundException("Invalid username or password.");
                throw new UsernameNotFoundException("notHaveEmail");
                // return "notHaveEmail";
            }
            String realOldPass = user.getPassword();
            if (!passwordEncoder.matches(changePasswordDto.getCurrPassword(), user.getPassword()))
            {
                // throw new Exception(realOldPass + "|+|" + passwordEncoder.encode(changePasswordDto.getCurrPassword()));
                throw new Exception("wrongOldPass");
            }
            if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword()))
            {
                throw new Exception("mistakeInNewPass");
            }
            userRepository.setNewPassword(email, passwordEncoder.encode(changePasswordDto.getNewPassword()));
            return "OK";
        } catch (Exception e)
        {
            return "" + e;
        }
    }

    private String generateResetToken()
    {
        // Generate a unique token for password reset (can be UUID or other method)
        return java.util.UUID.randomUUID().toString();
    }
}
