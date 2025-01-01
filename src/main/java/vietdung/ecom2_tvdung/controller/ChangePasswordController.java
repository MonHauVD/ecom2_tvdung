package vietdung.ecom2_tvdung.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vietdung.ecom2_tvdung.controller.dto.ChangePasswordDto;
import vietdung.ecom2_tvdung.service.UserService;

@Controller
@RequestMapping("/change_password")
public class ChangePasswordController {
    private UserService userService;

	public ChangePasswordController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public ChangePasswordDto changePasswordDto() {
        return new ChangePasswordDto();
    }
	
	@GetMapping
	public String showForgetPasswordForm() {
		return "thymeleaf/changePassword";
	}
	
	@PostMapping
	public String success(@ModelAttribute("user") ChangePasswordDto changePasswordDto) {
		String isOk = userService.changePass(changePasswordDto);
        if(isOk.matches("OK"))
        {
            return "redirect:/changePassword?success";
            
        }
        else if (isOk.matches("org.springframework.security.core.userdetails.UsernameNotFoundException: notHaveEmail"))
        {
            return "redirect:/changePassword?notHaveEmail";
            
        }
        else if (isOk.matches("java.lang.Exception: wrongOldPass"))
        {
            return "redirect:/changePassword?wrongOldPass";
            
        }
        else if (isOk.matches("java.lang.Exception: mistakeInNewPass"))
        {
            return "redirect:/changePassword?mistakeInNewPass";
            
        }
		return "redirect:/changePassword?" + isOk;
	}
}
