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
import vietdung.ecom2_tvdung.model.User;
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
@RequestMapping("/detail_item")
@Slf4j
public class ItemDetailController
{
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    private final PaymentDAOImpl paymentService;
    private final ShipmentDAOImpl shipmentService;
    private final ReviewDAOImpl reviewService;
    
    @Autowired
    public ItemDetailController(ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderService, PaymentDAOImpl paymentService, ShipmentDAOImpl shipmentService, ReviewDAOImpl reviewService)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.shipmentService = shipmentService;
        this.reviewService = reviewService;
    }
    
    
    @GetMapping("/{itemId}")
    public ModelAndView detail(@PathVariable("itemId") Long itemId)
    {
        ModelAndView mView = new ModelAndView("itemDetail");
        
        ItemDetailDto itemDetailDtos = itemService.getItemDetailDtoByItemId(itemId);
        mView.addObject("item", itemDetailDtos);
        
        List<ReviewDto> lsReviewDto = reviewService.getListReviewDtoByItemId(itemId);
        
        mView.addObject("reviews",  lsReviewDto);
        
        return mView;
    }

    
   
}
