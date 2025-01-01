/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import java.util.List;

/**
 *
 * @author TranVietDung
 */
public class WrapperOrderListDto
{
    private List<OrderListDto> lsOrder;

    public WrapperOrderListDto()
    {
    }

    public WrapperOrderListDto(List<OrderListDto> lsOrder)
    {
        this.lsOrder = lsOrder;
    }

    public List<OrderListDto> getLsOrder()
    {
        return lsOrder;
    }

    public void setLsOrder(List<OrderListDto> lsOrder)
    {
        this.lsOrder = lsOrder;
    }

    @Override
    public String toString()
    {
        return "WrapperOrderListDto{" + "lsOrder=" + lsOrder + '}';
    }
    
    
}
