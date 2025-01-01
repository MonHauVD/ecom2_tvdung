/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller;

/**
 *
 * @author TranVietDung
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.DetailCustomerDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDtoListWrapper;
import vietdung.ecom2_tvdung.model.Address;
import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.BookDAOImpl;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

@Controller
@Slf4j
public class CartController {
    
    private final BookDAOImpl bookService;
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userServiceImpl;
    private final CartDAOImpl cartService;

    @Autowired
    public CartController(BookDAOImpl bookService, ItemDAOImpl itemService, UserServiceImpl userServiceImpl, CartDAOImpl cartService)
    {
        this.bookService = bookService;
        this.itemService = itemService;
        this.userServiceImpl = userServiceImpl;
        this.cartService = cartService;
    }
    
    @GetMapping("/addToCart/{itemId}")
    public String addToCart(@PathVariable("itemId") Long itemId)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userServiceImpl.getUserByEmail(email);
        Long UserId = currentUser.getId();
        cartService.addItemToCustomerCart(UserId, itemId, 1);
        
        
        return "redirect:/";
    }
    

    @GetMapping("/cart/{currentUserId}")
    public ModelAndView getCustomerCart(@PathVariable("currentUserId") Long currentUserId)
    {
        ModelAndView mView = new ModelAndView("cart");
        List<ItemDto> lsItemDto = cartService.getListItemDtoFromCartByUserId(currentUserId);
        ItemDtoListWrapper itemDtoListWrapper = new ItemDtoListWrapper(lsItemDto);
        mView.addObject("items", itemDtoListWrapper);
        
        if(lsItemDto.size() == 0)
        {
            mView.addObject("listSize", "NoItem");
        }
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userServiceImpl.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        return mView;
    }
    
    @GetMapping("/cart/")
    public ModelAndView getCustomerCart2()
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userServiceImpl.getUserByEmail(email);
        Long userId = currentUser.getId();
        ModelAndView mView = new ModelAndView("cart");
        List<ItemDto> lsItemDto = cartService.getListItemDtoFromCartByUserId(userId);
        ItemDtoListWrapper itemDtoListWrapper = new ItemDtoListWrapper(lsItemDto);
        mView.addObject("items", itemDtoListWrapper);
//        mView.addObject("items", lsItemDto);
        if(lsItemDto.size() == 0)
        {
            mView.addObject("listSize", "NoItem");
        }
        
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        mView.addObject("username", name);
        return mView;
    }
    
    @GetMapping(value = "/cart/delete_cartItem/{itemId}")
    public String deleteCartItemByItemId(@PathVariable("itemId") Long itemId)
    {
        log.info("deleteCartItemByItemId||||||||||||||||||||||||||");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userServiceImpl.getUserByEmail(email);
        Long userId = currentUser.getId();
        cartService.deleteCartItemByItemId(userId, itemId);
        return "redirect:/cart/";
    }
    
    @RequestMapping(value = "/cart/done", method = RequestMethod.POST)
    public String checkOut(@ModelAttribute("items") ItemDtoListWrapper itemDtoList)
    {
        
//        log.info("OK 1");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userServiceImpl.getUserByEmail(email);
        Long userId = currentUser.getId();
//        log.info("OK 2");
        List<ItemDto> ls_itemDto = itemDtoList.getLsItem();
//        log.info("OK 3");
        try
        {
            log.info("ItemDtoListWrapper received: {}", ls_itemDto);
            int i = 0;
            for(ItemDto thisItemDto : ls_itemDto)
            {
                log.info("thisItemDto " + i + ": " + thisItemDto);
                i++;
            }
        } catch (Exception e)
        {
            log.info(" " + e);
        }
        cartService.finishSelectedCartByUserId(userId, ls_itemDto);
//        return "redirect:/";
        return "redirect:/order/";
    }
}
