package vietdung.ecom2_tvdung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping(value={"/error","/403"})
    public String accessDenied() {
        return "403"; 
    }
}
