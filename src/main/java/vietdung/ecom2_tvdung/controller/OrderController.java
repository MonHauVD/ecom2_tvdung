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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDtoListWrapper;
import vietdung.ecom2_tvdung.controller.dto.OrderDto;
import vietdung.ecom2_tvdung.model.PaymentMethod;
import vietdung.ecom2_tvdung.model.ShipmentMethod;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.OrderDAOImpl;
import vietdung.ecom2_tvdung.service.PaymentDAOImpl;
import vietdung.ecom2_tvdung.service.ShipmentDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

/**
 *
 * @author TranVietDung
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController
{
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    private final PaymentDAOImpl paymentService;
    private final ShipmentDAOImpl shipmentService;
    
    @Autowired
    public OrderController(ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderDAOImpl, PaymentDAOImpl paymentDAOImpl, ShipmentDAOImpl shipmentDAOImpl)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderDAOImpl;
        this.paymentService = paymentDAOImpl;
        this.shipmentService = shipmentDAOImpl;
    }
    
    
    @GetMapping("/")
    public ModelAndView order()
    {
        ModelAndView mView = new ModelAndView("order");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
//        mView.addObject("currentUserId", UserId);
        List<ItemDto> itemDtos = cartService.getListItemDtoFromCartByUserId(UserId);

        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        mView.addObject("paymentMethods", PaymentMethod.values());
        mView.addObject("shipmentMethods", ShipmentMethod.values());
        OrderDto orderDto = orderService.getCurrentOrderDtoByUserId(UserId);
        if(orderDto != null)
        {
            mView.addObject("orderDto", orderDto);
        }
        
        return mView;
    }

    @RequestMapping(value = "/select_payment_method", method = RequestMethod.POST)
    public String selectPaymentMethod(@ModelAttribute("paymentMethod") PaymentMethod choosedPaymentMethod)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long userId = currentUser.getId();
        orderService.choosingPaymentMethod(userId, choosedPaymentMethod);        
        return "redirect:/order/";
    }
   
    @RequestMapping(value = "/select_shipment_method", method = RequestMethod.POST)
    public String selectShipmentMethod(@ModelAttribute("shipmentMethod") ShipmentMethod choosedShipmentMethod)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long userId = currentUser.getId();
        orderService.choosingShipmentMethod(userId, choosedShipmentMethod);        
        return "redirect:/order/";
    }
    
    @RequestMapping(value = "check_out", method = RequestMethod.POST)
    public String checkOut()
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long userId = currentUser.getId();    
        OrderDto orderDto =  orderService.getCurrentOrderDtoByUserId(userId);
        if (orderDto.getSelectedPaymentMethod() == null)
        {
            orderService.choosingPaymentMethod(userId, PaymentMethod.CoD);   
        }
        if (orderDto.getSelectedShipmentMethod() == null)
        {
            orderService.choosingShipmentMethod(userId, ShipmentMethod.Motorcycle);
        }
        return "redirect:/payment/";
    }
}
