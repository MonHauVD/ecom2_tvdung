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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.controller.dto.OrderDto;
import vietdung.ecom2_tvdung.controller.dto.WrapperOrderListDto;
import vietdung.ecom2_tvdung.model.PaymentMethod;
import vietdung.ecom2_tvdung.model.ShipmentMethod;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.BookDAOImpl;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.OrderDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

/**
 *
 * @author TranVietDung
 */
@Controller
@RequestMapping("/orderlist")
@Slf4j
public class OrderListController
{
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    
    @Autowired
    public OrderListController(ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderService)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }
    
    @GetMapping("/")
    public ModelAndView orderList()
    {
        ModelAndView mView = new ModelAndView("orderList");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
//        mView.addObject("currentUserId", UserId);
        
        WrapperOrderListDto wrapper = orderService.getWrapperOrderListDtoByUserID(UserId);
        
        mView.addObject("orders", wrapper);
        
        return mView;
    }

    
    @GetMapping(value = "/received_order/{orderId}")
    public String confirmReceivedOrderByOrderId(@PathVariable("orderId") Long orderId)
    {
        orderService.setReceivedOrderByOrderId(orderId);
        return "redirect:/orderlist/";
    }
    
    @GetMapping(value = "/detail_order/{orderId}")
    public String getDetailedOrderByOrderId(@PathVariable("orderId") Long orderId)
    {
        
        return "redirect:/detail_order/" + orderId;
    }
}
