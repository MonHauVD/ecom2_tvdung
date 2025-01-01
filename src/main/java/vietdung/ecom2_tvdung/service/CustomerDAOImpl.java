package vietdung.ecom2_tvdung.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vietdung.ecom2_tvdung.model.Customer;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CustomerDAOImpl implements CustomerDAO{
    @Autowired
    private CustomerRepository customerDAO;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
//    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerDAOImpl()
    {
        passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

//    public Customer findByName(Fullname name) {return customerDAO.findByFullname(name);};

    
    @Override
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
            addressRepository.save(address);
            
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
    
    @Override
    public Customer save(User user, Address address, Customer customer) {
        try
        {
            Long user_id = userRepository.getUserIdByCustomerID(customer.getId());
            Role user_role = userRepository.getRoleByUserId(user_id);
            user.setId(user_id);
            user.setRole(user_role);
            
            String newPassword = user.getPassword();
            if (newPassword.length() == 0)
            {
                user.setPassword(userRepository.getOldPassword(user_id));
            }
            userRepository.save(user);
            
            Long address_id = addressRepository.getAddressIdByCustomerID(customer.getId());
            address.setId(address_id);
            addressRepository.save(address);
            
            customer.setUser(user);
            customer.setAddress(address);
            if(customer.getImage().length() == 0)
            {
                customer.setImage(customerDAO.getOldImage(customer.getId()));
            }
            
            return customerDAO.save(customer);
        } catch (Exception e)
        {
            System.out.println("Loi CustomerDAOImpl.save " + e);
        }
        return  null;
    }

    @Override
    public void update(Customer customer) {customerDAO.save(customer);};
    @Override
    public void delete(Customer customer) {customerDAO.deleteCustomerById(customer.getId());};
    

    // Additional methods can be added
}