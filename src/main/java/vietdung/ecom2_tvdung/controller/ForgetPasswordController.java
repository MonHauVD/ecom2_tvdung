package vietdung.ecom2_tvdung.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vietdung.ecom2_tvdung.controller.dto.ForgetPasswordDto;
import vietdung.ecom2_tvdung.service.UserService;

@Controller
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {
    private UserService userService;

	public ForgetPasswordController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public ForgetPasswordDto forgetPasswordDto() {
        return new ForgetPasswordDto();
    }
	
	@GetMapping
	public String showForgetPasswordForm() {
		return "thymeleaf/forgetPassword";
	}
	
	@PostMapping
	public String success(@ModelAttribute("user") ForgetPasswordDto forgetPasswordDto) {
		String isOk = userService.changeTmpPass(forgetPasswordDto);
        if(isOk.matches("OK"))
        {
            return "redirect:/forgetPassword?success";
            
        }
        else if (isOk.matches("org.springframework.security.core.userdetails.UsernameNotFoundException: notHaveEmail"))
        {
            return "redirect:/forgetPassword?notHaveEmail";
            
        }
		return "redirect:/forgetPassword?" + isOk;
	}
}

