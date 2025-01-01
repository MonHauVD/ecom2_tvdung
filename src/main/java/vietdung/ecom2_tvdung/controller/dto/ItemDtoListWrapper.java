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
public class ItemDtoListWrapper {
    private List<ItemDto> lsItem;

    public ItemDtoListWrapper()
    {
    }

    public ItemDtoListWrapper(List<ItemDto> lsItem)
    {
        this.lsItem = lsItem;
    }

    public List<ItemDto> getLsItem()
    {
        return lsItem;
    }

    public void setLsItem(List<ItemDto> lsItem)
    {
        this.lsItem = lsItem;
    }

    @Override
    public String toString()
    {
        return "ItemDtoListWrapper{" + "lsItem=" + lsItem.toString() + '}';
    }

    
}
