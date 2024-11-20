package vietdung.ecom2_tvdung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vietdung.ecom2_tvdung.service.CustomerDAOImpl;

@Controller

public class CustomerController {
    @Autowired
    private CustomerDAOImpl customerDAOImpl;

    @GetMapping("/customers")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerDAOImpl.findAll());
        return "customer"; // Points to customer.jsp
    }
}

