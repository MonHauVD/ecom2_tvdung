/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import vietdung.ecom2_tvdung.controller.dto.CustomerRegistrationDto;
import vietdung.ecom2_tvdung.model.Customer;

/**
 *
 * @author TranVietDung
 */
public interface CustomerDAO
{
    Customer save(CustomerRegistrationDto registrationDto);
    void update(Customer customer);
    void delete(Customer customer);
}
