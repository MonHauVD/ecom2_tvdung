package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import vietdung.ecom2_tvdung.model.*;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    @Transactional
    @Query(value = "SELECT rv.* FROM review rv\n" +
                    "WHERE rv.order_id = :orderID\n"+
                    "ORDER By rv.review_date DESC", nativeQuery = true)
    public List<Review> getReviewListByOrderId(@Param("orderID") Long orderID);
    
    @Transactional
    @Query(value = "SELECT rv.* FROM review rv\n" +
                    "WHERE rv.item_id = :itemID\n"+
                    "ORDER By rv.review_date DESC" , nativeQuery = true)
    public List<Review> getReviewListByItemId(@Param("itemID") Long itemID);
  
    @Transactional
    @Query(value = "SELECT rv.* FROM review rv\n" +
                    "WHERE rv.order_id = :orderID\n"+
                    "AND rv.item_id = :itemID\n" , nativeQuery = true)
    public Review getReviewByOrderIdAndItemId(@Param("orderID") Long orderID, @Param("itemID") Long itemID);
}
