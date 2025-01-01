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
import vietdung.ecom2_tvdung.model.Laptop;
/**
 *
 * @author TranVietDung
 */
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    
    // Custom queries for Book if needed
//    Book findBookById(Long id);
//            
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM book WHERE id = :myBookId", nativeQuery = true)
//    void deleteBookById(@Param("myBookId") Long id);
    @Transactional
    @Query(value = "SELECT * FROM laptop\n" +
                "WHERE laptop.item_id = :myItemId", nativeQuery = true)
    Laptop getLaptopByItemID(@Param("myItemId") Long itemId);
   
}