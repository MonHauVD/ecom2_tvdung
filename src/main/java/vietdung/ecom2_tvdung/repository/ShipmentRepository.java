package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vietdung.ecom2_tvdung.model.Shipment;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
//    List<Shipment> findAllByOrderId(long orderId);
//    List<Shipment> findAllByMethod(String method);
//    List<Shipment> findAllByStatus(String status);
    Shipment findShipmentById(long id);
}
