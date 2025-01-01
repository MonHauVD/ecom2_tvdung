package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vietdung.ecom2_tvdung.model.Order;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
@EnableJpaRepositories(basePackages = "vietdung.ecom2_tvdung.dao")
public interface OrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findAllByCustomerId(Long customerId);
//    List<Order> findAllByShipmentStatus(String shipmentStatus);
//    List<Order> findAllByPaymentMethod(String paymentMethod);
//    Order findById(long id);

    @Transactional
    @Query(value = "SELECT od.* FROM `order` od\n" +
                "WHERE od.is_current_order = TRUE and od.customer_id = :cusID", nativeQuery = true)
    public Order getCurrentOrderByCustomerId(@Param("cusID")Long cusId);

    @Transactional
    @Query(value = "SELECT od.* FROM `order` od\n" +
                    "WHERE od.customer_id = :cusID\n" +
                    "ORDER BY od.id DESC\n" +
                    "LIMIT 1", nativeQuery = true)
    public Order getLastOrderByCustomerId(@Param("cusID") Long id);
    
    @Transactional
    @Query(value = "SELECT od.* FROM `order` od\n" +
                    "WHERE od.customer_id = :cusID\n" +
                    "ORDER By od.order_date DESC", nativeQuery = true)
    public List<Order> getOrderListByCustomerId(@Param("cusID") Long id);
    
    @Transactional
    @Query(value = "SELECT od.* FROM `order` od\n" +
                    "ORDER By od.order_date DESC", nativeQuery = true)
    public List<Order> getOrderList();
}
