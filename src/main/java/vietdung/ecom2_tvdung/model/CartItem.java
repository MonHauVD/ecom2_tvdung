/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.model;

/**
 *
 * @author TranVietDung
 */
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "cart_id")
//    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;

    public CartItem()
    {
    }

    public CartItem(Cart cart, Item item)
    {
        this.cart = cart;
        this.item = item;
        this.quantity = 1;
    }

    
    
    public CartItem(Cart cart, Item item, Integer quantity)
    {
        this.cart = cart;
        this.item = item;
        this.quantity = quantity;
    }

    
    
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "CartItem{" + "id=" + id + ", cartid=" + cart.getId() + ", item=" + item + ", quantity=" + quantity + '}';
    }

    
}

