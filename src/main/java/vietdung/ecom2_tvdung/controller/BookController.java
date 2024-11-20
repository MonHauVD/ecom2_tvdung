/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller;

/**
 *
 * @author TranVietDung
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vietdung.ecom2_tvdung.model.Book;
import vietdung.ecom2_tvdung.service.BookDAOImpl;
import vietdung.ecom2_tvdung.service.CartDAOImpl;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDAOImpl bookService;
    @Autowired
    private CartDAOImpl cartService;
    // Display the list of available books
    
    @GetMapping
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "bookList"; // Refers to bookList.jsp
    }
    
    
    @PostMapping("/addToCart")
    public String addToCart(
            @RequestParam Long customerId,
            @RequestParam Long bookId,
            @RequestParam int quantity,
            Model model) {
        try {
            cartService.addBookToCustomerCart(customerId, bookId, quantity);
            model.addAttribute("message", "Book added to cart successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error adding book to cart: " + e.getMessage());
        }
        return "redirect:/books";
    }
    
    @PostMapping("/add")
    public Book addNewBook(@RequestParam String name,
                           @RequestParam double price,
                           @RequestParam int quantity,
                           @RequestParam String producer,
                           @RequestParam String description,
                           @RequestParam String author,
                           @RequestParam int numberPage) {
        return bookService.addNewBook(name, price, quantity, producer, description, author, numberPage);
    }
}
