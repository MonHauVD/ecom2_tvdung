package vietdung.ecom2_tvdung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.model.Order;
import vietdung.ecom2_tvdung.repository.OrderRepository;

@Service
public class OrderDAOImpl {
    @Autowired
    private OrderRepository orderDAO;

    public Order findById(int id) {
        return orderDAO.findById(id);
    }
}
