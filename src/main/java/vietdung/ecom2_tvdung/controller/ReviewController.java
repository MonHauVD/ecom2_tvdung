/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.ItemDetailDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDtoListWrapper;
import vietdung.ecom2_tvdung.controller.dto.OrderDto;
import vietdung.ecom2_tvdung.controller.dto.ReviewDto;
import vietdung.ecom2_tvdung.model.PaymentMethod;
import vietdung.ecom2_tvdung.model.ShipmentMethod;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.OrderDAOImpl;
import vietdung.ecom2_tvdung.service.PaymentDAOImpl;
import vietdung.ecom2_tvdung.service.ReviewDAOImpl;
import vietdung.ecom2_tvdung.service.ShipmentDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

/**
 *
 * @author TranVietDung
 */
@Controller
@RequestMapping("/review")
@Slf4j
public class ReviewController
{
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    private final PaymentDAOImpl paymentService;
    private final ShipmentDAOImpl shipmentService;
    private final ReviewDAOImpl reviewService;
    
    @Autowired
    public ReviewController(ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderService, PaymentDAOImpl paymentService, ShipmentDAOImpl shipmentService, ReviewDAOImpl reviewService)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.shipmentService = shipmentService;
        this.reviewService = reviewService;
    }
    
    
    @GetMapping("/{orderId}/{itemId}")
    public ModelAndView review(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId)
    {
        ModelAndView mView = new ModelAndView("reviewItem");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        ReviewDto reviewDto = new ReviewDto(orderId, itemId);
        mView.addObject("reviewDto", reviewDto);
        
        ItemDetailDto itemDetailDtos = itemService.getItemDetailDtoByItemId(itemId);
        mView.addObject("item", itemDetailDtos);
        
        
        return mView;
    }

    

    @RequestMapping(value = "send_review", method = RequestMethod.POST)
//    public String sendReview(@ModelAttribute("reviewDto") ReviewDto reviewDto)
    public String sendReview(@ModelAttribute("abcorderId") Long abcorderId,     @ModelAttribute("abcitemId") Long abcitemId,
            @ModelAttribute("abcrating") String abcrating,
            @ModelAttribute("abccomment") String abccomment)
    {
//        log.info(abcorderId.toString());
//        log.info(abcorderId.getClass().getName());
//        log.info(abcitemId.toString());
//        log.info(abcitemId.getClass().getName());
//        log.info(abcrating.toString());
//        log.info(abcrating.getClass().getName());
//        log.info(abccomment.toString());
//        log.info(abccomment.getClass().getName());
        
        Rating rating = Rating.five;
        if(abcrating.compareTo("0") == 0) rating = Rating.one;
        else if (abcrating.compareTo("1") == 0) rating = Rating.two;
        else if (abcrating.compareTo("2") == 0) rating = Rating.three;
        else if (abcrating.compareTo("3") == 0) rating = Rating.four;
        else if (abcrating.compareTo("4") == 0) rating = Rating.five;
        ReviewDto reviewDto = new ReviewDto(abccomment, rating, abcorderId, abcitemId);
        log.info(reviewDto.toString());
        reviewService.saveFromReviewDto(reviewDto);               
        return "redirect:/detail_order/"+reviewDto.getOrderId();
//        return "redirect:/detail_order/0";
    }
   
    
   
}
