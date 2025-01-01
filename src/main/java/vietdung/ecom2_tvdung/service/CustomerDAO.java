/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.CustomerRegistrationDto;
import vietdung.ecom2_tvdung.model.Address;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.User;

/**
 *
 * @author TranVietDung
 */
public interface CustomerDAO
{
    Customer save(CustomerRegistrationDto registrationDto);
    public Customer save(User user, Address address, Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    public List<Customer> findAll();
}
