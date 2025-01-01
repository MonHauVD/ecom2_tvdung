/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.model.Address;
import vietdung.ecom2_tvdung.model.Book;
import vietdung.ecom2_tvdung.model.Catalog;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.Item;
import vietdung.ecom2_tvdung.model.Role;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.AddressRepository;
import vietdung.ecom2_tvdung.repository.BookRepository;
import vietdung.ecom2_tvdung.repository.CustomerRepository;
import vietdung.ecom2_tvdung.repository.ItemRepository;
import vietdung.ecom2_tvdung.repository.UserRepository;

/**
 *
 * @author TranVietDung
 */
@Service
@Slf4j
public class AddDataWhenStart
{
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final BookRepository bookRepository;
    private final ItemRepository itemRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AddDataWhenStart(UserRepository userRepository, CustomerRepository customerRepository, AddressRepository addressRepository, BookRepository bookRepository, ItemRepository itemRepository)
    {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.bookRepository = bookRepository;
        this.itemRepository = itemRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }
    
    
    
    @PostConstruct
    public void initUsers() {
        if (userRepository.countByEmail("admin@gmail.com") == 0) {
            User admin = new User("Admin", "1", "admin@gmail.com", passwordEncoder.encode("123"), Role.ADMIN);
            userRepository.save(admin);
        }
        if (userRepository.countByEmail("khachhang1@gmail.com") == 0) {
            User user1 = new User("Khach", "Hang 1", "khachhang1@gmail.com", passwordEncoder.encode("123"), Role.USER);
            userRepository.save(user1);
            Address address1 = new Address("1", "Trần Phú", "Mộ Lao", "Hà Đông", "Hà Nội", "Việt Nam");
            addressRepository.save(address1);
            Customer customer1 = new Customer(user1, address1, "0987654321", "");
            customerRepository.save(customer1);
        }
        if (userRepository.countByEmail("khachhang2@gmail.com") == 0) {
            User user2 = new User("Khach", "Hang 2", "khachhang2@gmail.com", passwordEncoder.encode("123"), Role.USER);
            userRepository.save(user2);
            Address address2 = new Address("2", "An Đà", "Đằng Giang", "Ngô Quyền", "Hải Phòng", "Việt Nam");
            addressRepository.save(address2);
            Customer customer2 = new Customer(user2, address2, "0987654322", "");
            customerRepository.save(customer2);
        }
        log.info("Users initialized in the database!");
    }
    
    @PostConstruct
    public void initBooks() {
        if (itemRepository.countBookByBookName("Book 1") == 0) {
            Item item1 = new Item("Book 1", 100000.0, 200, "Nhà xuất bản Trẻ", Catalog.book, "../images/origin/book.png", "Quyển sách 1");
            itemRepository.save(item1);
            Book book1 = new Book("Việt Dũng", "1234567890", 240, item1);
            bookRepository.save(book1);
        }
        if (itemRepository.countBookByBookName("Book 2") == 0) {
            Item item2 = new Item("Book 2", 50000.0, 160, "Nhà xuất bản Su That", Catalog.book, "../images/origin/book.png", "Quyển sách 2");
            itemRepository.save(item2);
            Book book2 = new Book("Việt Dũng", "1234567891", 240, item2);
            bookRepository.save(book2);
        }
        if (itemRepository.countBookByBookName("Book 3") == 0) {
            Item item3 = new Item("Book 3", 500000.0, 20, "Bộ giáo dục và đào ", Catalog.book, "../images/origin/book.png", "Quyển sách 3");
            itemRepository.save(item3);
            Book book3 = new Book("Việt Dũng", "1234567892", 240, item3);
            bookRepository.save(book3);
        }
        log.info("Books initialized in the database!");
    }
    
}
