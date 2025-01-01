/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.model.CartItem;

/**
 *
 * @author TranVietDung
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    @Transactional
    @Query(value = "SELECT ci.* \n" +
                    "FROM cart_item ci\n" +
                    "JOIN cart c ON ci.cart_id = c.id\n" +
                    "JOIN customer cu ON c.customer_id = cu.id\n" +
                    "WHERE ci.item_id = ? AND cu.id = ? AND c.is_current_cart = TRUE;", nativeQuery = true)
    CartItem getCartItemByItemIdAndCustomerId (@Param("itemId")Long itemId, @Param("cusId")Long cusId);
    
    @Transactional
    @Query(value = "SELECT ci.* FROM cart_item ci " +
               "JOIN cart c ON ci.cart_id = c.id " +
               "JOIN customer cu ON c.customer_id = cu.id " +
               "JOIN user u ON cu.user_id = u.id " +
               "WHERE u.id = :userId AND c.is_current_cart = TRUE", nativeQuery = true)
    List<CartItem> getListCartItemByUserIdWithCurrentCart  (@Param("userId") Long userId);
    
    @Transactional
    @Query(value = "SELECT ci.* FROM cart_item ci " +
                    "JOIN cart c ON ci.cart_id = c.id " +
                    "JOIN customer cu ON c.customer_id = cu.id " +
                    "WHERE cu.id = :customerId AND c.is_current_cart = TRUE", nativeQuery = true)
    List<CartItem> getListCartItemByCustomerIdWithCurrentCart  (@Param("customerId") Long customerId);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE ci\n" +
                    "FROM cart_item ci\n" +
                    "JOIN cart ct ON ci.cart_id = ct.id\n" +
                    "JOIN customer c ON ct.customer_id = c.id\n" +
                    "JOIN user u ON c.user_id = u.id\n" +
                    "WHERE u.id = :userId AND ci.item_id = :itemId", nativeQuery = true)
    void deleteCartItemByItemId(@Param("userId") Long userId, @Param("itemId") Long itemId);
    
}
