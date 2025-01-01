/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

/**
 *
 * @author TranVietDung
 */
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.controller.dto.DetailBookDto;
import vietdung.ecom2_tvdung.model.Book;
import vietdung.ecom2_tvdung.model.Catalog;
import vietdung.ecom2_tvdung.model.Item;
import vietdung.ecom2_tvdung.repository.BookRepository;
import vietdung.ecom2_tvdung.repository.ItemRepository;

@Service
@Slf4j
public class BookDAOImpl implements BookDAO{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Book addNewBook(DetailBookDto detailBookDto) {
        String Image = detailBookDto.getImage();
        if(Image.length() == 0)
        {
            Image = "../images/origin/book.png";
        }
        Item item = new Item(detailBookDto.getName(), detailBookDto.getPrice(), detailBookDto.getQuantity(), detailBookDto.getProducer(), Catalog.book, Image, detailBookDto.getDescription());
        item = itemRepository.save(item); 
        
        Book book = new Book(detailBookDto.getAuthor(), detailBookDto.getIsbn(), detailBookDto.getNumberPage());
        book.setItem(item);

        return bookRepository.save(book);
    }
    
    @Override
    public Book getBookByBookId(Long BookId)
    {
//        Long item_id = itemRepository.getItemIdByBookID(BookId);
//        Item item = itemRepository.findItemById(item_id);
        
        Book book = bookRepository.getById(BookId);
        return book;
    }
    
    @Override
    public DetailBookDto getDetailBookDtoByBookId(Long BookId)
    {
        Long item_id = itemRepository.getItemIdByBookID(BookId);
        Item item = itemRepository.getById(item_id);
        Book book = bookRepository.getById(BookId);
        return new DetailBookDto(book, item);
    }
    
    @Override
    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }
    
    @Override
    public List<DetailBookDto> getAllDetailBookDto()
    {
        List<DetailBookDto> ls = new ArrayList<>();
        List <Book> lsBook = bookRepository.findAll();
        for(Book thisBook : lsBook)
        {
            Long item_id = itemRepository.getItemIdByBookID(thisBook.getId());
            Item thisItem = itemRepository.getById(item_id);
            ls.add(new DetailBookDto(thisBook, thisItem));
        }
        
        return ls;
    }

    @Override
    public void updateBook(DetailBookDto detailBookDto)
    {
        Long item_id = itemRepository.getItemIdByBookID(detailBookDto.getBookId());
        Item oldItem = itemRepository.getById(item_id);
        Item item = new Item(detailBookDto.getName(), detailBookDto.getPrice(), detailBookDto.getQuantity(), detailBookDto.getProducer(), Catalog.book, detailBookDto.getImage(), detailBookDto.getDescription());
        item.setId(item_id);
        item.setCatalog(Catalog.book);
        if(detailBookDto.getImage().length() == 0)
        {
            item.setImage(oldItem.getImage());
        }
        
        itemRepository.save(item);
        
        Book book = new Book(detailBookDto.getBookId(), detailBookDto.getAuthor(), detailBookDto.getIsbn(), detailBookDto.getNumberPage(), item);
        
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long BookId)
    {
        Long item_id = itemRepository.getItemIdByBookID(BookId);
        Book book = bookRepository.getById(BookId);
        Item item = itemRepository.getById(item_id);
        bookRepository.delete(book);
        itemRepository.delete(item);
    }
    
    
    
}