/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Book;
/**
 *
 * @author TranVietDung
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
        
    @Transactional
    @Query(value = "SELECT * FROM book\n" +
                "WHERE book.item_id = :myItemId", nativeQuery = true)
    Book getBookByItemID(@Param("myItemId") Long itemId);
}