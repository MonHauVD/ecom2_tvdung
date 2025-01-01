/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.ItemDetailDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.model.Item;

/**
 *
 * @author TranVietDung
 */
public interface ItemDAO
{
    Item getItem(Long id);
    public List<Item> getAllItems();
    public List<ItemDto> getAllItemDtos();
    public List<ItemDto> getAllItemDtosWithBook();
    public List<ItemDto> getAllItemDtosWithClothes();
    public List<ItemDto> getAllItemDtosWithElectronic();
    public List<ItemDto> getAllItemDtosWithLaptop();
    public List<ItemDto> getAllItemDtosWithMobilephone();
    public List<ItemDto> getAllItemDtosWithShoes();
    public ItemDetailDto getItemDetailDtoByItemId(Long ItemID);
    
}
