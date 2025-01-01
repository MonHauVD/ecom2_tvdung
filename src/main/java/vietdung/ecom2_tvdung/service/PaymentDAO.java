/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import vietdung.ecom2_tvdung.controller.dto.PaymentDto;
import vietdung.ecom2_tvdung.model.Payment;

/**
 *
 * @author TranVietDung
 */
public interface PaymentDAO
{
    Payment getPaymentFromCurrentOrderByUserId(Long userId);
    PaymentDto getPaymentDtoFromCurrentOrderByUserId(Long userId);
    PaymentDto getPaymentDtoFromLastOrderByUserId(Long userId);
    void setStatusCompleted(Long userId);
}
