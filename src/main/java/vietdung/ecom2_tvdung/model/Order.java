package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.*;
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String date;
    private Calendar orderDate;
    
    private Calendar receiveDate;
    
    private OrderState state;
    
    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
    
    private boolean isCurrentOrder;

    public Order()
    {
    }

    public Order(Customer customer, Shipment shipment, Payment payment)
    {
        this.customer = customer;
        this.shipment = shipment;
        this.payment = payment;
        this.isCurrentOrder = true;
        this.orderDate = new GregorianCalendar();
        this.state = OrderState.WaitingForConfirm;
    }

    
    
    public Order(Cart cart, Customer customer, Shipment shipment, Payment payment)
    {
        this.cart = cart;
        this.customer = customer;
        this.shipment = shipment;
        this.payment = payment;
        this.isCurrentOrder = true;
        this.orderDate = new GregorianCalendar();
        this.state = OrderState.WaitingForConfirm;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean isIsCurrentOrder()
    {
        return isCurrentOrder;
    }

    public void setIsCurrentOrder(boolean isCurrentOrder)
    {
        this.isCurrentOrder = isCurrentOrder;
    }

    public Calendar getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate)
    {
        this.orderDate = orderDate;
    }

    public Calendar getReceiveDate()
    {
        return receiveDate;
    }

    public void setReceiveDate(Calendar receiveDate)
    {
        this.receiveDate = receiveDate;
    }

    public OrderState getState()
    {
        return state;
    }

    public void setState(OrderState state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", receiveDate=" + receiveDate + ", state=" + state + ", cart=" + cart + ", customer=" + customer + ", shipment=" + shipment + ", payment=" + payment + ", isCurrentOrder=" + isCurrentOrder + '}';
    }

    
    
    
    
}