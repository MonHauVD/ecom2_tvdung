/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vietdung.ecom2_tvdung.model.CartItem;

/**
 *
 * @author TranVietDung
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    
}
