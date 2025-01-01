package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.model.Customer;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    @Transactional
    @Query(value = "SELECT c.* FROM Cart c WHERE c.customer_id = :myCustomerId and c.is_current_cart = TRUE", nativeQuery = true)
    Cart findCurrentCartByCustomerId(@Param("myCustomerId") Long customerId);
    
  
}
