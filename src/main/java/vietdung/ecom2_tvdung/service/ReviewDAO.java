/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.ReviewDto;
import vietdung.ecom2_tvdung.model.Review;

/**
 *
 * @author TranVietDung
 */
public interface ReviewDAO
{
    List<Review> getReviewListByOrderId(Long orderID);
    List<Review> getReviewListByItemId(Long itemID);
    Review getReviewByOrderIdAndItemId(Long orderID, Long itemID);
    boolean isReviewedByOrderIdAndItemId(Long orderID, Long itemID);
    List<ReviewDto> getListReviewDtoByItemId(Long itemID);
    void saveFromReviewDto (ReviewDto reviewDto);
}
