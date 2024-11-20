package vietdung.ecom2_tvdung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.model.Payment;
import vietdung.ecom2_tvdung.repository.PaymentRepository;

@Service
public class PaymentDAOImpl {
    @Autowired
    private PaymentRepository paymentDAO;

    public Payment getPayment(Long id) {
        return paymentDAO.findPaymentById(id);
    }
}
