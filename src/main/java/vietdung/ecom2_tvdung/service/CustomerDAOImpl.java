package vietdung.ecom2_tvdung.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vietdung.ecom2_tvdung.model.Customer;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.CustomerRegistrationDto;
import vietdung.ecom2_tvdung.controller.dto.UserRegistrationDto;
import vietdung.ecom2_tvdung.model.Address;
import vietdung.ecom2_tvdung.model.Role;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.repository.AddressRepository;
import vietdung.ecom2_tvdung.repository.CustomerRepository;

@Service
public class CustomerDAOImpl implements CustomerDAO{
    @Autowired
    private CustomerRepository customerDAO;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressDAO;
    
//    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerDAOImpl()
    {
        passwordEncoder = new BCryptPasswordEncoder();
    }


    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

//    public Customer findByName(Fullname name) {return customerDAO.findByFullname(name);};

    
    public Customer save(CustomerRegistrationDto registrationDto) {
        try
        {
            User user = new User(registrationDto.getFirstName(), 
                            registrationDto.getLastName(), registrationDto.getEmail(),
                            passwordEncoder.encode(registrationDto.getPassword()), 
                            Role.USER);

            userRepository.save(user);
            
            Address address = new Address();
            address.setNumber(registrationDto.getNumber());
            address.setStreet(registrationDto.getStreet());
            address.setWard(registrationDto.getWard());
            address.setProvince(registrationDto.getProvince());
            address.setDistrict(registrationDto.getDistrict());
            address.setCountry(registrationDto.getCountry());
            addressDAO.save(address);
            
            Customer customer = new Customer();
            customer.setUser(user);
            customer.setAddress(address);
            customer.setPhoneNumber(registrationDto.getPhoneNumber());
            String Image = registrationDto.getImage();
            if(Image.length() == 0)
            {
                Image = "../images/origin/person.png";
            }
            customer.setImage(Image);
            return customerDAO.save(customer);
        } catch (Exception e)
        {
            System.out.println("Loi CustomerDAOImpl.save" + e);
        }
        return  null;
    }

    public void update(Customer customer) {customerDAO.save(customer);};
    public void delete(Customer customer) {customerDAO.deleteCustomerById(customer.getId());};
    

    // Additional methods can be added
}