package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<CartItem> cartItems;

    private double totalPrice;
    private boolean isCurrentCart;

    public Cart()
    {
    }
    
    

    public Cart(Customer customer)
    {
        this.customer = customer;
        isCurrentCart = true;
    }

    
    
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public List<CartItem> getCartItems()
    {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems)
    {
        this.cartItems = cartItems;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public boolean isIsCurrentCart()
    {
        return isCurrentCart;
    }

    public void setIsCurrentCart(boolean isCurrentCart)
    {
        this.isCurrentCart = isCurrentCart;
    }

    @Override
    public String toString()
    {
        return "Cart{" + "id=" + id + ", customer=" + customer + ", cartItems=" + cartItems + ", totalPrice=" + totalPrice + ", isCurrentCart=" + isCurrentCart + '}';
    }

    
    
    
}
