/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.ReviewDto;
import vietdung.ecom2_tvdung.repository.*;

import vietdung.ecom2_tvdung.model.*;

/**
 *
 * @author TranVietDung
 */
@Service
@Slf4j
public class ReviewDAOImpl implements ReviewDAO
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Override
    public List<Review> getReviewListByOrderId(Long orderID)
    {
        return reviewRepository.getReviewListByOrderId(orderID);
    }
    
    @Override
    public List<Review> getReviewListByItemId(Long itemID)
    {
        return reviewRepository.getReviewListByItemId(itemID);
    }
    
    @Override
    public Review getReviewByOrderIdAndItemId(Long orderID, Long itemID)
    {
        return reviewRepository.getReviewByOrderIdAndItemId(orderID, itemID);
    }
    
    @Override
    public boolean isReviewedByOrderIdAndItemId(Long orderID, Long itemID)
    {
        Review review = reviewRepository.getReviewByOrderIdAndItemId(orderID, itemID);
        if(review == null)
        {
            return false;
        }else
        {
            return true;
        }
    }
    
    @Override
    public List<ReviewDto> getListReviewDtoByItemId(Long itemID)
    {
        List<Review> lsReview = reviewRepository.getReviewListByItemId(itemID);
        List<ReviewDto> lsReviewDto = new ArrayList<>();
        
        for(Review review : lsReview)
        {
            Customer customer = review.getOrder().getCustomer();
            String customerName = customer.getUser().getFirstName() + " " + customer.getUser().getLastName();
            ReviewDto tmp = new ReviewDto(review.getId(), review.getComment(), review.getRating(), review.getReviewDate().getTime().toString(), customerName);
            lsReviewDto.add(tmp);
        }
        return lsReviewDto;
    }
    
    @Override
    public void saveFromReviewDto (ReviewDto reviewDto)
    {
        Order order = orderRepository.getById(reviewDto.getOrderId());
        Item item = itemRepository.getById(reviewDto.getItemId());
        if(!isReviewedByOrderIdAndItemId(order.getId(), item.getId()))
        {
            Review review = new Review(reviewDto.getComment(), reviewDto.getRating(), new GregorianCalendar(), order, item);
            reviewRepository.save(review);
        }
    }
}
