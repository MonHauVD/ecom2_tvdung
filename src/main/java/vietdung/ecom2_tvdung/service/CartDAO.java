/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.model.CartItem;

/**
 *
 * @author TranVietDung
 */
public interface CartDAO
{
    Cart getCurrentCartByCustomerId(Long customerId);
    Cart getCurrentCartByUserId(Long userId);
    List<CartItem> getListCartItemByUserId (Long userId);
    List<CartItem> getListCartItemByCustomerId (Long cusId);
    void addItemToCustomerCart(Long userId, Long itemId, int quantity);
    List<ItemDto> getListItemDtoFromCartByUserId (Long userId);
    void deleteCartItemByItemId(Long userId, Long itemId);
    void finishSelectedCartByUserId(Long userId, List<ItemDto> ls_itemDto);
    void checkOutCartByUserId(Long userId, List<ItemDto> ls_itemDto);
    List<ItemDto> getListItemDtoByOrderId (Long OrderId);
    
    
    
}
