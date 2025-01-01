/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

/**
 *
 * @author TranVietDung
 */
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.controller.dto.DetailClothesDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.*;

@Service
@Slf4j
public class ClothesDAOImpl implements  ClothesDAO{

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private ItemRepository itemRepository;

    
    @Override
    public Clothes addNewClothes(DetailClothesDto detailClothesDto) {
        String Image = detailClothesDto.getImage();
        if(Image.length() == 0)
        {
            Image = "../images/origin/clothes.png";
        }
        Item item = new Item(detailClothesDto.getName(), detailClothesDto.getPrice(), detailClothesDto.getQuantity(), detailClothesDto.getProducer(), Catalog.clothes, Image, detailClothesDto.getDescription());
        item = itemRepository.save(item); 
        
        Clothes clothes = new Clothes(detailClothesDto.getSize(), detailClothesDto.getColor(), detailClothesDto.getMaterial(), detailClothesDto.getGender());
        clothes.setItem(item);

        return clothesRepository.save(clothes);
    }
    
    
    @Override
    public Clothes getClothesByClothesId(Long ClothesId)
    {
//        Long item_id = itemRepository.getItemIdByClothesID(ClothesId);
//        Item item = itemRepository.findItemById(item_id);
        
        Clothes clothes = clothesRepository.getById(ClothesId);
        return clothes;
    }
    
    
    @Override
    public DetailClothesDto getDetailClothesDtoByClothesId(Long ClothesId)
    {
        Long item_id = itemRepository.getItemIdByClothesID(ClothesId);
        Item item = itemRepository.getById(item_id);
        Clothes clothes = clothesRepository.getById(ClothesId);
        return new DetailClothesDto(clothes, item);
    }
    
    
    @Override
    public List<Clothes> getAllClothess()
    {
        return clothesRepository.findAll();
    }
    
    
    @Override
    public List<DetailClothesDto> getAllDetailClothesDto()
    {
        List<DetailClothesDto> ls = new ArrayList<>();
        List <Clothes> lsClothes = clothesRepository.findAll();
        for(Clothes thisClothes : lsClothes)
        {
            Long item_id = itemRepository.getItemIdByClothesID(thisClothes.getId());
            Item thisItem = itemRepository.getById(item_id);
            ls.add(new DetailClothesDto(thisClothes, thisItem));
        }
        
        return ls;
    }

    
    @Override
    public void updateClothes(DetailClothesDto detailClothesDto)
    {
        Long item_id = itemRepository.getItemIdByClothesID(detailClothesDto.getClothesId());
        Item oldItem = itemRepository.getById(item_id);
        Item item = new Item(detailClothesDto.getName(), detailClothesDto.getPrice(), detailClothesDto.getQuantity(), detailClothesDto.getProducer(), Catalog.clothes, detailClothesDto.getImage(), detailClothesDto.getDescription());
        item.setId(item_id);
        item.setCatalog(Catalog.clothes);
        if(detailClothesDto.getImage().length() == 0)
        {
            item.setImage(oldItem.getImage());
        }
        
        itemRepository.save(item);
        
        Clothes clothes = new Clothes(detailClothesDto.getClothesId(), detailClothesDto.getSize(), detailClothesDto.getColor(), detailClothesDto.getMaterial(), detailClothesDto.getGender(), item);
        
        clothesRepository.save(clothes);
    }

    
    @Override
    public void deleteClothes(Long ClothesId)
    {
        Long item_id = itemRepository.getItemIdByClothesID(ClothesId);
        Clothes clothes = clothesRepository.getById(ClothesId);
        Item item = itemRepository.getById(item_id);
        clothesRepository.delete(clothes);
        itemRepository.delete(item);
    }
    
    
    
}