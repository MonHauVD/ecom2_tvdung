package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vietdung.ecom2_tvdung.model.Payment;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    List<Payment> findAllByOrderId(Long orderId);
//    List<Payment> findAllByMethod(String method);
//    List<Payment> findAllByStatus(String status);
    Payment findPaymentById(Long id);
}
