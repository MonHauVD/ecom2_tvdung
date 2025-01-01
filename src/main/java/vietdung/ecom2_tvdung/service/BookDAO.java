/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.DetailBookDto;
import vietdung.ecom2_tvdung.model.Book;

/**
 *
 * @author TranVietDung
 */
public interface BookDAO {
    Book addNewBook(DetailBookDto detailBookDto);
    Book getBookByBookId(Long BookId);
    DetailBookDto getDetailBookDtoByBookId(Long BookId);
    List<DetailBookDto> getAllDetailBookDto();
    void updateBook(DetailBookDto detailBookDto);
    void deleteBook(Long BookId);
    List<Book> getAllBooks();
}

