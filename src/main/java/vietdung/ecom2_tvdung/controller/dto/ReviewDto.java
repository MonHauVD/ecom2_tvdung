/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import java.util.Calendar;
import vietdung.ecom2_tvdung.model.Rating;
import vietdung.ecom2_tvdung.model.Review;

/**
 *
 * @author TranVietDung
 */
public class ReviewDto
{
    private Long id;
    private String comment;
    private String ratingString;
    private Rating rating;
    private String reviewDate;
    private Long orderId;
    private Long itemId;
    private String customerName;

    public ReviewDto()
    {
    }

    public ReviewDto(Long orderId, Long itemId)
    {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public ReviewDto(Long id, String comment, Rating rating, String reviewDate, String customerName)
    {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.customerName = customerName;
    }

    public ReviewDto(String comment, Rating rating, Long orderId, Long itemId)
    {
        this.comment = comment;
        this.rating = rating;
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public String getRatingString()
    {
        return ratingString;
    }

    public void setRatingString(String ratingString)
    {
        this.ratingString = ratingString;
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

    public String getReviewDate()
    {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate)
    {
        this.reviewDate = reviewDate;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getItemId()
    {
        return itemId;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    @Override
    public String toString()
    {
        return "ReviewDto{" + "id=" + id + ", comment=" + comment + ", rating=" + rating + ", reviewDate=" + reviewDate + ", orderId=" + orderId + ", itemId=" + itemId + '}';
    }

    
    
    
}
