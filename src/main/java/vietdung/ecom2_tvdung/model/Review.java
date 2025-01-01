/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.model;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author TranVietDung
 */
@Entity
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String comment;
    
    private Rating rating;
    
    private Calendar reviewDate;
    
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public Review()
    {
    }

    public Review(String comment, Rating rating, Calendar reviewDate, Order order, Item item)
    {
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.order = order;
        this.item = item;
    }
    
    public Review(Long id, String comment, Rating rating, Calendar reviewDate, Order order, Item item)
    {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.order = order;
        this.item = item;
    }
    
    

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Rating getRating()
    {
        return rating;
    }

    public void setRating(Rating rating)
    {
        this.rating = rating;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Calendar getReviewDate()
    {
        return reviewDate;
    }

    public void setReviewDate(Calendar reviewDate)
    {
        this.reviewDate = reviewDate;
    }

    
    
    @Override
    public String toString()
    {
        return "Review{" + "id=" + id + ", comment=" + comment + ", rating=" + rating + ", order=" + order.getId() + ", item=" + item + '}';
    }
    
    
}
