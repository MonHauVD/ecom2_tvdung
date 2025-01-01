/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vietdung.ecom2_tvdung.model.Address;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.User;

/**
 *
 * @author TranVietDung
 */
public class DetailCustomerDto
{

    private Long cusId;
    private String image, firstName, lastName, email, phoneNumber, strAddress;
    private String password;
    private String number;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String country;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public DetailCustomerDto()
    {
    }

    public DetailCustomerDto(Long cusId, String image, String firstName, String lastName, String email, String phoneNumber, String strAddress)
    {
        this.cusId = cusId;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.strAddress = strAddress;
    }

   

     public DetailCustomerDto(Long cusId, String image, String firstName, String lastName, String email, String phoneNumber,  String number, String street, String ward, String district, String province, String country)
    {
        this.cusId = cusId;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.country = country;
    }


    public DetailCustomerDto(Long cusId, String image, String firstName, String lastName, String email, String phoneNumber, String password, String number, String street, String ward, String district, String province, String country)
    {
        this.cusId = cusId;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.country = country;
    }

   
    
    public Address getAddress()
    {
        Address address = new Address(number, street, ward, district, province, country);
        return address;
    }
    
    public User getUser()
    {
        String newPassword = "";
        if (password.length() != 0)
        {
            newPassword = passwordEncoder.encode(password);
        }
        User user = new User(firstName, lastName, email, newPassword);
        return user;
    }
    
    public Customer getCustomer()
    {
        Customer customer = new Customer(cusId, phoneNumber, image);
        return customer;
    }

    public Long getCusId()
    {
        return cusId;
    }

    public void setCusId(Long cusId)
    {
        this.cusId = cusId;
    }

    

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getStrAddress()
    {
        return strAddress;
    }

    public void setStrAddress(String strAddress)
    {
        this.strAddress = strAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

   

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getWard()
    {
        return ward;
    }

    public void setWard(String ward)
    {
        this.ward = ward;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "DetailCustomer{" + "cusId=" + cusId + ", image=" + image + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", strAddress=" + strAddress + ", password=" + password + ", number=" + number + ", street=" + street + ", ward=" + ward + ", district=" + district + ", province=" + province + ", country=" + country + ", passwordEncoder=" + passwordEncoder + '}';
    }

    
    
    

}
