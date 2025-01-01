/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

/**
 *
 * @author TranVietDung
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Book;
import vietdung.ecom2_tvdung.model.Item;
import vietdung.ecom2_tvdung.repository.BookRepository;
import vietdung.ecom2_tvdung.repository.ItemRepository;

@Service
public class BookDAOImpl implements BookDAO{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Book addNewBook(String name, double price, int quantity, String producer, String description, String author, int numberPage) {
        // Step 1: Create and save a new Item
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setProducer(producer);
        item.setDescription(description);

        item = itemRepository.save(item); // Save item to generate id and idItem

        // Step 2: Create and save the new Book with the idItem from the saved Item
        Book book = new Book();
        book.setIdItem(item.getId()); // Save Item's id as idItem in Book
        book.setName(name);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setProducer(producer);
        book.setDescription(description);
        book.setAuthor(author);
        book.setNumberPage(numberPage);

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }
    
    
}