/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vietdung.ecom2_tvdung.controller.dto.CustomerRegistrationDto;
import vietdung.ecom2_tvdung.service.CustomerDAOImpl;

/**
 *
 * @author TranVietDung
 */
@Controller
@RequestMapping("/registration")
public class CustomerRegistrationController
{
    private CustomerDAOImpl customerDAOImpl;

    public CustomerRegistrationController(CustomerDAOImpl customerDAOImpl)
    {
        super();
        this.customerDAOImpl = customerDAOImpl;
    }
    
    @ModelAttribute("user")
    public CustomerRegistrationDto customerRegistrationDto() {
        return new CustomerRegistrationDto();
    }
	
    @GetMapping
    public String showRegistrationForm() {
            return "thymeleaf/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") CustomerRegistrationDto registrationDto) {
            customerDAOImpl.save(registrationDto);
            return "redirect:/registration?success";
    }
}
