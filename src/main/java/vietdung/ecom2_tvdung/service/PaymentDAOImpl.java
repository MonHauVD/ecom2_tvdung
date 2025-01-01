package vietdung.ecom2_tvdung.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.OrderDto;
import vietdung.ecom2_tvdung.controller.dto.PaymentDto;
import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.Order;
import vietdung.ecom2_tvdung.model.Payment;
import vietdung.ecom2_tvdung.model.PaymentStatus;
import vietdung.ecom2_tvdung.model.Shipment;
import vietdung.ecom2_tvdung.model.ShipmentMethod;
import vietdung.ecom2_tvdung.repository.CartRepository;
import vietdung.ecom2_tvdung.repository.CustomerRepository;
import vietdung.ecom2_tvdung.repository.OrderRepository;
import vietdung.ecom2_tvdung.repository.PaymentRepository;
import vietdung.ecom2_tvdung.repository.ShipmentRepository;

@Service
@Slf4j
public class PaymentDAOImpl implements PaymentDAO{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    

    public Payment getPayment(Long id) {
        return paymentRepository.findPaymentById(id);
    }
    
    @Override
    public Payment getPaymentFromCurrentOrderByUserId(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        if (order == null)
        {
            return new Payment();
        }
        Payment payment = order.getPayment();
        return payment;
    }
    
    @Override
    public PaymentDto getPaymentDtoFromCurrentOrderByUserId(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        if (order == null)
        {
            log.info("order == null");
            return new PaymentDto();
        }
        Payment payment = order.getPayment();
        log.info("Current payment" + payment);
        PaymentDto paymentDto = new PaymentDto(payment.getMethod(), payment.getPretax(), payment.getTax(), payment.getTotal());
        return paymentDto;
    }
    
    @Override
    public PaymentDto getPaymentDtoFromLastOrderByUserId(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getLastOrderByCustomerId(customer.getId());
        if (order == null)
        {
            log.info("order == null");
            return new PaymentDto();
        }
        Payment payment = order.getPayment();
        log.info("Current payment" + payment);
        PaymentDto paymentDto = new PaymentDto(payment.getMethod(), payment.getPretax(), payment.getTax(), payment.getTotal());
        return paymentDto;
    }
    
    @Override
    public void setStatusCompleted(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        
        Payment payment = order.getPayment();
        payment.setStatus(PaymentStatus.completed);
        order.setPayment(payment);
        orderRepository.save(order);
    }
}
