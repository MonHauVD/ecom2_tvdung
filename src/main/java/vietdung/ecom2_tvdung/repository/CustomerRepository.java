package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.User;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom queries can be defined here
//    void updateCustomer(Customer customer);
    void deleteCustomerById(Long id);
    Customer findCustomerById(Long id);
//    Customer findCustomerByEmail(String email);
    Customer findCustomerByPhoneNumber(String phone);
//    void createCustomer(Customer customer);
//    void addCustomer(Customer customer);
//    List<Customer> findAllCustomers();
    

}