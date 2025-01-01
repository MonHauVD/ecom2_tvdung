/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.OrderDto;
import vietdung.ecom2_tvdung.controller.dto.WrapperOrderListDto;
import vietdung.ecom2_tvdung.model.Order;
import vietdung.ecom2_tvdung.model.PaymentMethod;
import vietdung.ecom2_tvdung.model.ShipmentMethod;

/**
 *
 * @author TranVietDung
 */
public interface OrderDAO
{
    Order findById(Long id);
    Order createOrderFromCurrentCart(Long userId);
    OrderDto getCurrentOrderDtoByUserId(Long userId);
    void choosingPaymentMethod(Long userId, PaymentMethod choosedPaymentMethod);
    void choosingShipmentMethod(Long userId, ShipmentMethod choosedShipmentMethod);
    void checkOut(Long userId);
    List<Order> getOrderListByCustomerID(Long cusID);
    WrapperOrderListDto getWrapperOrderListDtoByUserID (Long userID);
    void setReceivedOrderByOrderId (Long orderID);
    OrderDto getOrderDtoByOrderId(Long orderID);
    WrapperOrderListDto getAllWrapperOrderListDto();
    void setProcessedOrderByOrderId(Long orderId);
    void setPaidOrderByOrderId(Long orderId);
}
