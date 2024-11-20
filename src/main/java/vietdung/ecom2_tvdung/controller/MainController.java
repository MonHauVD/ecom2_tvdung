package vietdung.ecom2_tvdung.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {
        
	@GetMapping("/login")
	public String login() {
//            log.debug("Hello index");
//            log.info("Hello index");
//            log.trace("Hello index");
//            log.warn("Hello!!!");
		return "thymeleaf/login";
	}
        
        @GetMapping("/userloginvalidate")
        public String viewUserLoginPage() {
            return "/userloginvalidate";
        }
	
	@GetMapping("/")
	public String home() {
		return "thymeleaf/index";
	}
}
