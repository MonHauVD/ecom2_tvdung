package vietdung.ecom2_tvdung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.model.Shipment;
import vietdung.ecom2_tvdung.repository.ShipmentRepository;

@Service
public class ShipmentDAOImpl {
    @Autowired
    private ShipmentRepository shipmentDAO;

    public Shipment getShipmentById(Long id) {
        return shipmentDAO.findShipmentById(id);
    }
}