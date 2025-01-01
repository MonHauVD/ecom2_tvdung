/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller;

/**
 *
 * @author TranVietDung
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.PaymentDto;
import vietdung.ecom2_tvdung.model.PaymentMethod;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.OrderDAOImpl;
import vietdung.ecom2_tvdung.service.PaymentDAOImpl;
import vietdung.ecom2_tvdung.service.ShipmentDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

@Controller
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    private final PaymentDAOImpl paymentService;
    private final ShipmentDAOImpl shipmentService;

    public PaymentController(ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderService, PaymentDAOImpl paymentService, ShipmentDAOImpl shipmentService)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.shipmentService = shipmentService;
    }

    
    
    @GetMapping("/")
    public ModelAndView showPaymentPage() {
        // Add the selected payment method to the model
        ModelAndView mView = new ModelAndView("payment");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long userId = currentUser.getId();
        PaymentDto paymentDto = paymentService.getPaymentDtoFromCurrentOrderByUserId(userId);
        mView.addObject("paymentDto", paymentDto);
        if (paymentDto.getSelectedPaymentMethod().equals(PaymentMethod.CoD))
        {
            String message = "Cash on delivery!";
            mView.addObject("message", message);
            orderService.checkOut(userId);   
        }
        return mView; // Render payment.jsp
    }

    @PostMapping("/process")
    public ModelAndView processPayment() {
        // Simulate payment processing
        ModelAndView mView = new ModelAndView("payment");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long userId = currentUser.getId();
        PaymentDto paymentDto = paymentService.getPaymentDtoFromLastOrderByUserId(userId);
        String message = "Payment processed successfully using " + paymentDto.getSelectedPaymentMethod() + "!";
        mView.addObject("message", message);
        mView.addObject("paymentDto", paymentDto);
        paymentService.setStatusCompleted(userId);
        
        orderService.checkOut(userId);   
        return mView; // Redirect to the same page with a success message
    }
}
