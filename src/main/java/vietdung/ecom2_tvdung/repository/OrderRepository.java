package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vietdung.ecom2_tvdung.model.Order;

import java.util.List;
@EnableJpaRepositories(basePackages = "vietdung.ecom2_tvdung.dao")
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long customerId);
    List<Order> findAllByShipmentStatus(String shipmentStatus);
    List<Order> findAllByPaymentMethod(String paymentMethod);
    Order findById(long id);
}
