package vietdung.ecom2_tvdung.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.controller.dto.ItemDetailDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.*;

@Service
public class ItemDAOImpl implements ItemDAO{
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private ElectronicRepository electronicRepository;
    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private MobilePhoneRepository mobilePhoneRepository;
    @Autowired
    private ShoesRepository shoesRepository;

    public Item getItem(Long id) {
        return itemRepository.getById(id);
    }

    @Override
    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }
    
    @Override
    public List<ItemDto> getAllItemDtos()
    {
        List<Item> ls_item =  itemRepository.findAll();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    //    catalog: book, clothes, electronic, laptop, mobilephone, shoes
//              0,      1,      2,          3,      4,          5
   
    @Override
    public List<ItemDto> getAllItemDtosWithBook()
    {
        List<Item> ls_item =  itemRepository.getAllItemWithBook();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    
    @Override
    public List<ItemDto> getAllItemDtosWithClothes()
    {
        List<Item> ls_item =  itemRepository.getAllItemWithClothes();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    
    @Override
    public List<ItemDto> getAllItemDtosWithElectronic()
    {
        List<Item> ls_item =  itemRepository.getAllItemWithElectronic();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    @Override
    public List<ItemDto> getAllItemDtosWithLaptop()
    {
        List<Item> ls_item =  itemRepository.getAllItemWithLaptop();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    @Override
    public List<ItemDto> getAllItemDtosWithMobilephone()
    {
        List<Item> ls_item =  itemRepository.getAllItemWithMobilephone();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    @Override
    public List<ItemDto> getAllItemDtosWithShoes()
    {
        List<Item> ls_item =  itemRepository.getAllItemWithShoes();
        List<ItemDto> ls_itemDto = new ArrayList<>();
        for(Item item : ls_item)
        {
            ls_itemDto.add(item.getItemDto());
        }
        return ls_itemDto;
    }
    
    
    @Override
    public ItemDetailDto getItemDetailDtoByItemId(Long ItemID)
    {
        Item item = itemRepository.getById(ItemID);
        ItemDetailDto itemDetailDto = new ItemDetailDto(item);
        if(item.getCatalog().compareTo(Catalog.book) == 0)
        {
            Book book = bookRepository.getBookByItemID(ItemID);
            itemDetailDto = new ItemDetailDto(item, book);
        }
        else if(item.getCatalog().compareTo(Catalog.clothes) == 0)
        {
            Clothes clothes = clothesRepository.getClothesByItemID(ItemID);
            itemDetailDto = new ItemDetailDto(item, clothes);
        }
        else if(item.getCatalog().compareTo(Catalog.electronic) == 0)
        {
            Electronic electronic = electronicRepository.getElectronicByItemID(ItemID);
            itemDetailDto = new ItemDetailDto(item, electronic);
        }
        else if(item.getCatalog().compareTo(Catalog.laptop) == 0)
        {
            Laptop laptop = laptopRepository.getLaptopByItemID(ItemID);
            itemDetailDto = new ItemDetailDto(item, laptop);
        }
        else if(item.getCatalog().compareTo(Catalog.mobilephone) == 0)
        {
            MobilePhone mobilePhone = mobilePhoneRepository.getMobilePhoneByItemID(ItemID);
            itemDetailDto = new ItemDetailDto(item, mobilePhone);
        }
        else if(item.getCatalog().compareTo(Catalog.shoes) == 0)
        {
            Shoes shoes = shoesRepository.getShoesByItemID(ItemID);
            itemDetailDto = new ItemDetailDto(item, shoes);
        }
        
        
        return itemDetailDto;
    }
}
