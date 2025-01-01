package vietdung.ecom2_tvdung.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.DetailCustomerDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.CustomerRepository;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.BookDAOImpl;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.CustomerDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

@Controller
@Slf4j
public class CustomerProfileController
{

    private final BookDAOImpl bookService;
    private final ItemDAOImpl itemService;
    private final CartDAOImpl cartService;
    private final CustomerDAOImpl customerService;
    private final CustomerRepository customerRepository;
    private final UserServiceImpl userService;
    @Autowired
    public CustomerProfileController(BookDAOImpl bookService, ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, CustomerRepository customerRepository, CustomerDAOImpl customerService)
    {
        this.bookService = bookService;
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

@GetMapping("/profileDisplay/{id}")
    public ModelAndView showCustomerProfile()
    {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(currentEmail);
        Customer currentCustomer = customerRepository.getCustomerByUserId(currentUser.getId());
        
        Long customerId = currentCustomer.getId();
        
        ModelAndView mView = new ModelAndView("showProfileCustomer");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_ecom2", "root", "12345678");

            String query
                    = "SELECT   c.id,  c.image,    u.first_name,    u.last_name,    u.email,    c.phone_number,    a.number,    a.street,    a.ward,    a.district,    a.province,    a.country\n"
                    + "FROM     customer c\n"
                    + "JOIN     user u ON c.user_id = u.id\n"
                    + "JOIN     address a ON c.address_id = a.id\n"
                    + "where	c.id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, customerId);
            ResultSet rst = stmt.executeQuery();

            if (rst.next())
            {
                long cusid = rst.getInt(1);
                String Image = rst.getString(2);
                String firstname = rst.getString(3);
                String lastname = rst.getString(4);
                String email = rst.getString(5);
                String phonenumber = rst.getString(6);
                String numberHouse = rst.getString(7);
                String street = rst.getString(8);
                String ward = rst.getString(9);
                String district = rst.getString(10);
                String province = rst.getString(11);
                String country = rst.getString(12);
                DetailCustomerDto tmp = new DetailCustomerDto(cusid, Image, firstname, lastname, email, phonenumber, numberHouse, street, ward, district, province, country);
                mView.addObject("detailCustomer", tmp);
            }
        } catch (Exception e)
        {
            log.warn("Exception:" + e);
            System.out.println("Exception:" + e);
        }
        return mView;
    }
    
    @GetMapping("/update_profile")
    public ModelAndView getUpdateCustomer()
    {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(currentEmail);
        Customer currentCustomer = customerRepository.getCustomerByUserId(currentUser.getId());
        
        Long customerId = currentCustomer.getId();
        ModelAndView mView = new ModelAndView("updateCustomerForCustomer");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_ecom2", "root", "12345678");

            String query
                    = "SELECT   c.id,  c.image,    u.first_name,    u.last_name,    u.email,    c.phone_number,    a.number,    a.street,    a.ward,    a.district,    a.province,    a.country\n"
                    + "FROM     customer c\n"
                    + "JOIN     user u ON c.user_id = u.id\n"
                    + "JOIN     address a ON c.address_id = a.id\n"
                    + "where	c.id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, customerId);
            ResultSet rst = stmt.executeQuery();

            if (rst.next())
            {
                long cusid = rst.getInt(1);
                String Image = rst.getString(2);
                String firstname = rst.getString(3);
                String lastname = rst.getString(4);
                String email = rst.getString(5);
                String phonenumber = rst.getString(6);
                String numberHouse = rst.getString(7);
                String street = rst.getString(8);
                String ward = rst.getString(9);
                String district = rst.getString(10);
                String province = rst.getString(11);
                String country = rst.getString(12);
                DetailCustomerDto tmp = new DetailCustomerDto(cusid, Image, firstname, lastname, email, phonenumber, numberHouse, street, ward, district, province, country);
                mView.addObject("detailCustomer", tmp);
            }
        } catch (Exception e)
        {
            log.warn("Exception:" + e);
            System.out.println("Exception:" + e);
        }
        return mView;
    }
    
    @RequestMapping(value = "/updating_customer/{id}", method = RequestMethod.POST)
    public String updateCustomerProfile(@RequestParam("id") int cusid, @ModelAttribute DetailCustomerDto detailCustomerNew)
    {
        detailCustomerNew.setCusId(cusid + 0L);
        log.info("Customer ID: " + cusid);
        log.info("Updated Details: " + detailCustomerNew);
        User updateUser = detailCustomerNew.getUser();
        Address updateAddress = detailCustomerNew.getAddress();
        Customer updateCustomer = detailCustomerNew.getCustomer();
        customerService.save(updateUser, updateAddress, updateCustomer);
        
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(currentEmail);
        Customer currentCustomer = customerRepository.getCustomerByUserId(currentUser.getId());
        
        Long customerId = currentCustomer.getId();
        
        return "redirect:/profileDisplay/" + customerId;
    }
}
