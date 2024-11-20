/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.model.Book;

/**
 *
 * @author TranVietDung
 */
public interface BookDAO {
    Book addNewBook(String name, double price, int quantity, String producer, String description, String author, int numberPage);
    List<Book> getAllBooks();
}

