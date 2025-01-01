package vietdung.ecom2_tvdung.service;

import java.util.ArrayList;
import vietdung.ecom2_tvdung.repository.CartItemRepository;
import vietdung.ecom2_tvdung.repository.BookRepository;
import vietdung.ecom2_tvdung.repository.CustomerRepository;
import vietdung.ecom2_tvdung.repository.ItemRepository;
import vietdung.ecom2_tvdung.repository.CartRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.repository.OrderRepository;
import vietdung.ecom2_tvdung.repository.ReviewRepository;
import vietdung.ecom2_tvdung.repository.UserRepository;

@Service
@Slf4j
public class CartDAOImpl implements CartDAO{
     @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;

     @Override
    public Cart getCurrentCartByCustomerId(Long customerId) {
        return cartRepository.findCurrentCartByCustomerId(customerId);
    }
    @Override
    public Cart getCurrentCartByUserId(Long userId) {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        return cartRepository.findCurrentCartByCustomerId(customer.getId());
    }
    @Override
    public List<CartItem> getListCartItemByUserId (Long userId)
    {
        return cartItemRepository.getListCartItemByUserIdWithCurrentCart(userId);
    }
    @Override
    public List<CartItem> getListCartItemByCustomerId (Long cusId)
    {
        return cartItemRepository.getListCartItemByCustomerIdWithCurrentCart(cusId);
    }
    @Override
    public void addItemToCustomerCart(Long userId, Long itemId, int quantity)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        log.info("addItemToCustomerCart " + customer);
        Cart cart = cartRepository.findCurrentCartByCustomerId(customer.getId());
        if (cart == null)
        {
            log.info("addItemToCustomerCart " + "cart null");
            cart = new Cart(customer);
            cartRepository.save(cart);
        }
        Item item = itemRepository.getById(itemId);
        CartItem cartItem = cartItemRepository.getCartItemByItemIdAndCustomerId(itemId, customer.getId());
        if (cartItem == null)
        {
            log.info("addItemToCustomerCart " + "cartItem null");
            cartItem = new CartItem(cart, item, quantity);
        }
        else
        {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        log.info("addItemToCustomerCart " + cartItem);
        cartItemRepository.save(cartItem);
//        Tinh total price
        Double totalPrice = 0.0;
        List<CartItem> lsCartItems = cartItemRepository.getListCartItemByUserIdWithCurrentCart(userId);
        for(CartItem thisCartItem : lsCartItems)
        {
            Double pricePerItem = thisCartItem.getItem().getPrice();
            Integer pickingQuantity = thisCartItem.getQuantity();
            totalPrice += pricePerItem*pickingQuantity;
        }
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
        log.info("addItemToCustomerCart cart after add item" + cart);
    }
    @Override
    public List<ItemDto> getListItemDtoFromCartByUserId (Long userId)
    {
        List<ItemDto> lsItemDto = new ArrayList<>();
        List<CartItem> lsCartItems = cartItemRepository.getListCartItemByUserIdWithCurrentCart(userId);
        for(CartItem thisCartItem : lsCartItems)
        {
//            ItemDto tmpItemDto = thisCartItem.getItem().getItemDto();
//            tmpItemDto.setQuantity(thisCartItem.getQuantity());
//            tmpItemDto.setMaxQuantity(thisCartItem.getItem().getQuantity());
//            if(thisCartItem.getQuantity() != null)
//                tmpItemDto.setTotalPrice(thisCartItem.getQuantity()*thisCartItem.getItem().getPrice());
            ItemDto tmpItemDto = new ItemDto(thisCartItem);
            lsItemDto.add(tmpItemDto);
        }
        return lsItemDto;
    }
    @Override
    public void deleteCartItemByItemId(Long userId, Long itemId)
    {
        cartItemRepository.deleteCartItemByItemId(userId, itemId);
    }
    @Override
    public void finishSelectedCartByUserId(Long userId, List<ItemDto> ls_itemDto)
    {
        log.info("finishSelectedCartByUserId ||||||||||||||||");
        Customer customer = customerRepository.getCustomerByUserId(userId);
        try{log.info("Customer: " + customer);} catch (Exception e){log.error("" + e);}
        Cart cart = cartRepository.findCurrentCartByCustomerId(customer.getId());
        try{log.info("Cart: " + cart);} catch (Exception e){log.error("" + e);}
        if(cart != null)
        {
            log.info("Cart != null");
            List<CartItem> lsCartItems = cart.getCartItems();
            for(CartItem thisCartItem : lsCartItems)
            {
                cartItemRepository.delete(thisCartItem);
            }
            cartRepository.delete(cart);
        }
        
        cart = new Cart(customer);
        cartRepository.save(cart);
        log.info("Create new cart");
        
        Double totalPrice = 0.0;
        List<CartItem> newListCartItems = new ArrayList<>();
        for(ItemDto thisItemDto : ls_itemDto)
        {
            Item thisItem = itemRepository.getById(thisItemDto.getId());
            CartItem newCartItem = new CartItem(cart, thisItem, thisItemDto.getQuantity());
            newListCartItems.add(newCartItem);
            totalPrice += thisItemDto.getQuantity() * thisItem.getPrice();
            
            
            cartItemRepository.save(newCartItem);
        }
        log.info("newListCartItems: " + newListCartItems);
        cart.setTotalPrice(totalPrice);
        cart.setCartItems(newListCartItems);
        cartRepository.save(cart);
        
    }
    @Override
    public void checkOutCartByUserId(Long userId, List<ItemDto> ls_itemDto)
    {
        Customer customer = customerRepository.getCustomerByUserId(userId);
        Cart cart = cartRepository.findCurrentCartByCustomerId(customer.getId());
        if(cart != null)
        {
            cart.setIsCurrentCart(false);
            cartRepository.save(cart);
        }
        
    }
    @Override
    public List<ItemDto> getListItemDtoByOrderId (Long OrderId)
    {
        List<ItemDto> lsItemDto = new ArrayList<>();
        try
        {
            Order order = orderRepository.getById(OrderId);
            Cart cart = order.getCart();
            List<CartItem> lsCartItems = cart.getCartItems();
            for(CartItem thisCartItem : lsCartItems)
            {
                Review review = reviewRepository.getReviewByOrderIdAndItemId(OrderId, thisCartItem.getItem().getId());
                boolean isReviewed = false;
                if(review != null)
                {
                    isReviewed = true;
                }
                ItemDto tmpItemDto = new ItemDto(thisCartItem, isReviewed);
                lsItemDto.add(tmpItemDto);
            }
        } catch (Exception e)
        {
        }
        
        return lsItemDto;
    }
}
