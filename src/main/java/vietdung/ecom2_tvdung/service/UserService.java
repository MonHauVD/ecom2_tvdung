package vietdung.ecom2_tvdung.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import vietdung.ecom2_tvdung.controller.dto.ChangePasswordDto;
import vietdung.ecom2_tvdung.controller.dto.ForgetPasswordDto;
import vietdung.ecom2_tvdung.controller.dto.UserRegistrationDto;
import vietdung.ecom2_tvdung.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	String changeTmpPass(ForgetPasswordDto forgetPasswordDto);
	String changePass(ChangePasswordDto changePasswordDto);
}
