/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import vietdung.ecom2_tvdung.model.Book;
import vietdung.ecom2_tvdung.model.Catalog;
import vietdung.ecom2_tvdung.model.Item;

/**
 *
 * @author TranVietDung
 */
public class DetailBookDto
{
    private Long bookId;
    private String name;
    private Double price;
    private Integer quantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    
    private String author;
    private String isbn;
    private Integer numberPage;

    public DetailBookDto()
    {
    }

    public DetailBookDto(String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String author, String isbn, Integer numberPage)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.numberPage = numberPage;
    }

    public DetailBookDto(String name, Double price, Integer quantity, String producer, String image, String description, String author, String isbn, Integer numberPage)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.image = image;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.numberPage = numberPage;
    }

    public DetailBookDto(Book book, Item item)
    {
        this.bookId = book.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.numberPage = book.getNumberPage();
    }

    public DetailBookDto(Long bookId, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String author, String isbn, Integer numberPage)
    {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.numberPage = numberPage;
    }
   

    public Long getBookId()
    {
        return bookId;
    }

    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

   

    public String getProducer()
    {
        return producer;
    }

    public void setProducer(String producer)
    {
        this.producer = producer;
    }

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public Integer getNumberPage()
    {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage)
    {
        this.numberPage = numberPage;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
    
    
    
}