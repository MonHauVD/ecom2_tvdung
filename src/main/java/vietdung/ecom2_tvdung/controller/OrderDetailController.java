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
import vietdung.ecom2_tvdung.model.*;
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
@RequestMapping("/detail_order")
@Slf4j
public class OrderDetailController
{
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    
    @Autowired
    public OrderDetailController(ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderService)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }
    
    @GetMapping("/{orderID}")
    public ModelAndView orderList(@PathVariable("orderID") Long orderID)
    {
        ModelAndView mView = new ModelAndView("orderDetail");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
//        mView.addObject("currentUserId", UserId);
        //Can check them order nay co thuoc nguoi dung nay không?
        //Se bo sung sau!!!!
        OrderDto orderDto = orderService.getOrderDtoByOrderId(orderID);
        if(orderDto != null)
        {
            mView.addObject("order", orderDto);
            if((orderDto.getState().compareTo(OrderState.Received) != 0) && (orderDto.getState().compareTo(OrderState.Completed) != 0))
            {
                mView.addObject("isNotReceived", true);
            }
        }
        
        if(currentUser.getRole().compareTo(Role.ADMIN) == 0)
        {
            mView.addObject("isNotReceived", true);
        }
        
        List<ItemDto> itemDtos = cartService.getListItemDtoByOrderId(orderID);

        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        
        return mView;
    }

    
    @GetMapping(value = "/review/{orderId}/{itemId}")
    public String reviewItemByOrderIdAndItemId(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId)
    {
        
        return "redirect:/review/"+orderId +"/"+itemId;
    }
    
    @GetMapping(value = "/detail_item/{itemId}")
    public String getDetailedItemByItemId(@PathVariable("itemId") Long itemId)
    {
        
        return "redirect:/detail_item/" + itemId;
    }
}
