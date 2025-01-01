package vietdung.ecom2_tvdung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.ChangePasswordDto;
import vietdung.ecom2_tvdung.controller.dto.ForgetPasswordDto;
import vietdung.ecom2_tvdung.controller.dto.UserRegistrationDto;
import vietdung.ecom2_tvdung.model.Role;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl //implements UserService
{

    private UserRepository userRepository;

//	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    
    public UserServiceImpl(UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

//    @Override
    public User save(UserRegistrationDto registrationDto)
    {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Role.USER);

        return userRepository.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//    {
//
//        User user = userRepository.findByEmail(username);
//        if (user == null)
//        {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole().toString()));
//    }
////
////    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
////    {
////        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
////    }

//    @Override
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

//    @Override
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

    public User getUserByUsername(String username)
    {
        User user = userRepository.findByEmail(username);
        return user;
    }
    
    public User getUserByEmail(String email)
    {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public Role getRolesByEmailUser(String email)
    {
        return userRepository.getRoleByEmail(email);
    }

    public User checkLogin(String username, String password)
    {

        try
        {
            User user = userRepository.findByEmail(username);
            System.out.println(user.getPassword());
            if (passwordEncoder.encode(password).equals(user.getPassword()))
            {
                return user;
            } else
            {
                return new User();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            User user = new User();
            return user;
        }

    }

    public boolean checkUserExists(String username)
    {
        User user = userRepository.findByEmail(username);
        if(user == null)
        {
            return false;
        }
        else
            return true;
    }

    public Long getUserIdByCustomerID(Long customerId)
    {
        return userRepository.getUserIdByCustomerID(customerId);  
    }
    
    
}
