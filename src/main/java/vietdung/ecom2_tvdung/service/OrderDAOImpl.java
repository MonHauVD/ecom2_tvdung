package vietdung.ecom2_tvdung.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.OrderDto;
import vietdung.ecom2_tvdung.controller.dto.OrderListDto;
import vietdung.ecom2_tvdung.controller.dto.WrapperOrderListDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.CartRepository;
import vietdung.ecom2_tvdung.repository.CustomerRepository;
import vietdung.ecom2_tvdung.repository.ItemRepository;
import vietdung.ecom2_tvdung.repository.OrderRepository;
import vietdung.ecom2_tvdung.repository.PaymentRepository;
import vietdung.ecom2_tvdung.repository.ReviewRepository;
import vietdung.ecom2_tvdung.repository.ShipmentRepository;

@Service
@Slf4j
public class OrderDAOImpl implements OrderDAO{
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
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ItemRepository itemRepository;
    
    @Override
    public Order findById(Long id) {
        return orderRepository.getById(id);
    }
    
    @Override
    public Order createOrderFromCurrentCart(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
//        Cart cart = cartRepository.findCurrentCartByCustomerId(customer.getId());
        Payment payment = new Payment();
        paymentRepository.save(payment);
        String customerAddress = customer.getAddress().toString();
        Shipment shipment = new Shipment(customerAddress);
        shipmentRepository.save(shipment);
        Order order = new Order(customer, shipment, payment);
        orderRepository.save(order);
        return order;
    }
    
    @Override
    public OrderDto getCurrentOrderDtoByUserId(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        log.info("Current order :" + order);
        if (order == null)
        {
            order = createOrderFromCurrentCart(userId);
            log.info("Order == null, new order :" + order);
        }
        ShipmentMethod selectedShipmentMethod = order.getShipment().getMethod();
        PaymentMethod selectedPaymentMethod = order.getPayment().getMethod();
        Double shipmentFee = order.getShipment().calculateFeeShip();
        Double preTax = order.getPayment().getPretax();
        Double tax = order.getPayment().getTax();
        Double total = order.getPayment().getTotal();
        Double totalPrice = null;
        if(preTax != null)
        {
            totalPrice = preTax - shipmentFee;
        }
        OrderDto orderDto = new OrderDto(selectedShipmentMethod, selectedPaymentMethod, shipmentFee, preTax, tax, total, totalPrice);
        return orderDto;
    }

    @Override
    public void choosingPaymentMethod(Long userId, PaymentMethod choosedPaymentMethod)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        Payment payment = order.getPayment();
        payment.setMethod(choosedPaymentMethod);
        order.setPayment(payment);
        orderRepository.save(order);
    }

    @Override
    public void choosingShipmentMethod(Long userId, ShipmentMethod choosedShipmentMethod)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        Shipment shipment = order.getShipment();
        shipment.setMethod(choosedShipmentMethod);
        
        Cart cart = cartRepository.findCurrentCartByCustomerId(customer.getId());
        Payment payment = order.getPayment();
        payment.setPretax(shipment.calculateFeeShip() + cart.getTotalPrice());
        payment.calculatePayment();
        
        order.setShipment(shipment);
        order.setPayment(payment);
        orderRepository.save(order);
    }

    @Override
    public void checkOut(Long userId)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Order order = orderRepository.getCurrentOrderByCustomerId(customer.getId());
        Cart cart = cartRepository.findCurrentCartByCustomerId(customer.getId());
        cart.setIsCurrentCart(false);
        order.setCart(cart);
        order.setIsCurrentOrder(false);
        
        List<CartItem> lsCartItem = cart.getCartItems();
        for(CartItem thisCartItem : lsCartItem)
        {
            Item item = thisCartItem.getItem();
            int subItem = item.getQuantity() - thisCartItem.getQuantity();
            if (subItem > 0)
            {
                item.setQuantity(subItem);
            }
            else
            {
                item.setQuantity(0);
            }  
            
            itemRepository.save(item);
            
        }
        
        orderRepository.save(order);
    }
    @Override
    public List<Order> getOrderListByCustomerID(Long cusID)
    {
        return orderRepository.getOrderListByCustomerId(cusID);
    }
    
    @Override
    public WrapperOrderListDto getWrapperOrderListDtoByUserID (Long userID)
    {
        Customer customer = customerRepository.getCustomerByUserId(userID);
        List<Order> lsOrder = orderRepository.getOrderListByCustomerId(customer.getId());
        List<OrderListDto> lsOrderList = new ArrayList<>();
        for(Order od : lsOrder)
        {
            lsOrderList.add(new OrderListDto(od));
        }
        WrapperOrderListDto wrapperOrderListDto = new WrapperOrderListDto(lsOrderList);
        return  wrapperOrderListDto;
    }
    
    @Override
    public void setReceivedOrderByOrderId (Long orderID)
    {
        Order order = orderRepository.getById(orderID);
        order.setState(OrderState.Received);
        Payment payment = order.getPayment();
        if(payment.getStatus().compareTo(PaymentStatus.completed) == 0)
        {
            order.setState(OrderState.Completed);
        }
        order.setReceiveDate(new GregorianCalendar());
        orderRepository.save(order);
    }
    @Override
    public OrderDto getOrderDtoByOrderId(Long orderID)
    {
        Order order = orderRepository.getById(orderID);
        OrderDto orderDto = new OrderDto(order);
        return orderDto;
    }

    @Override
    public WrapperOrderListDto getAllWrapperOrderListDto()
    {
        List<Order> lsOrder = orderRepository.getOrderList();
        List<OrderListDto> lsOrderList = new ArrayList<>();
        for(Order od : lsOrder)
        {
            lsOrderList.add(new OrderListDto(od));
        }
        WrapperOrderListDto wrapperOrderListDto = new WrapperOrderListDto(lsOrderList);
        return  wrapperOrderListDto;
    }
    @Override
    public void setProcessedOrderByOrderId(Long orderId)
    {
        Order order = orderRepository.getById(orderId);
        order.setState(OrderState.InTransit);
        order.setReceiveDate(new GregorianCalendar());
        orderRepository.save(order);
    }
    @Override
    public void setPaidOrderByOrderId(Long orderId)
    {
        Order order = orderRepository.getById(orderId);
        order.setState(OrderState.Completed);
        order.setReceiveDate(new GregorianCalendar());
        orderRepository.save(order);
    }
}
